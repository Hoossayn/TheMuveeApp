package com.example.domain.usecases

import android.database.SQLException
import com.example.data.model.movies.MovieEntity
import com.example.data.repository.movies.MovieRepository
import com.example.domain.dispatcher.UseCaseDispatchers
import com.example.domain.error.EmptyDataException
import com.example.domain.mapper.EntityToMovie
import com.example.domain.model.Movie
import com.example.test_util.RESPONSE_JSON_PATH
import com.example.test_util.extension.TestCoroutineExtension
import com.example.test_util.testObserver.test
import com.example.test_util.utils.convertFromJsonToListOf
import com.example.test_util.utils.getResourceAsText
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.*

import org.junit.jupiter.api.extension.RegisterExtension

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetMoviesUseCaseTest {

    private val repository: MovieRepository = mockk()
    private val entityToPostMapper: EntityToMovie = mockk()

    private val dispatcherProvider: UseCaseDispatchers =
        UseCaseDispatchers(Dispatchers.Main, Dispatchers.Main, Dispatchers.Main)

    companion object {
        @JvmField
        @RegisterExtension
        val testCoroutineExtension = TestCoroutineExtension()

        val testCoroutineScope = testCoroutineExtension.testCoroutineScope

        /*
            Mock Post data
         */
        private val movieList =
            convertFromJsonToListOf<Movie>(getResourceAsText(RESPONSE_JSON_PATH))!!

        /*
            Mock PostEntity data
         */
        private val movieEntityList =
            convertFromJsonToListOf<MovieEntity>(getResourceAsText(RESPONSE_JSON_PATH))!!
    }

    private lateinit var useCase: GetMoviesUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetMoviesUseCase(
            repository,
            entityToPostMapper,
            dispatcherProvider
        )
    }

    @Nested
    @DisplayName("Offline-Last(Refresh) Tests")
    inner class OffLineLastTest {

        @Test
        fun `given data returned from Remote, Local should delete old, save and return data`() =
            testCoroutineExtension.runBlockingTest {

                // GIVEN
                coEvery { repository.getMoviesFromRemote("",1) } returns movieEntityList
                coEvery { repository.deleteMovies() } just runs
                coEvery { repository.saveMovies(moviesEntity = movieEntityList) } just runs
                coEvery { repository.getMoviesFromLocal() } returns movieEntityList
                coEvery { entityToPostMapper.map(movieEntityList) } returns movieList

                // WHEN
                val testObserver = useCase.getMoviesOfflineLast().test(this)

                // THEN
                testObserver
                    .assertComplete()
                    .assertNoErrors()
                    .assertValueAt(0, movieList)
                    .assertValues {
                        it.first().containsAll(movieList)
                    }
                    .dispose()

                coVerifyOrder {
                    repository.getMoviesFromRemote("",1)
                    repository.deleteMovies()
                    repository.saveMovies(movieEntityList)
                    repository.getMoviesFromLocal()
                    entityToPostMapper.map(movieEntityList)
                }
            }

        @Test
        fun `given exception returned from Remote source, should fetch data from Local source`() =
            testCoroutineExtension.runBlockingTest {

                // GIVEN
                coEvery {
                    repository.getMoviesFromRemote("",1)
                } throws Exception("Network Exception")
                coEvery { repository.deleteMovies() } just runs
                coEvery { repository.saveMovies(moviesEntity = movieEntityList) } just runs
                coEvery { repository.getMoviesFromLocal() } returns movieEntityList
                coEvery { entityToPostMapper.map(movieEntityList) } returns movieList

                // WHEN
                val testObserver = useCase.getMoviesOfflineLast().test(this)

                // THEN
                testObserver
                    .assertComplete()
                    .assertNoErrors()
                    .assertValues {
                        it.first().containsAll(movieList)
                    }
                    .dispose()

                coVerify(exactly = 1) { repository.getMoviesFromRemote("",1) }
                coVerify(exactly = 1) { repository.getMoviesFromLocal() }
                coVerify(exactly = 0) { repository.deleteMovies() }
                coVerify(exactly = 0) { repository.saveMovies(movieEntityList) }
                verify(exactly = 1) { entityToPostMapper.map(movieEntityList) }
            }

        @Test
        fun `given empty data or null returned from Remote, should fetch data from Local `() =
            testCoroutineExtension.runBlockingTest {

                // GIVEN
                coEvery { repository.getMoviesFromRemote("",1) } returns listOf()
                coEvery { repository.deleteMovies() } just runs
                coEvery { repository.saveMovies(moviesEntity = movieEntityList) } just runs
                coEvery { repository.getMoviesFromLocal() } returns movieEntityList
                coEvery { entityToPostMapper.map(movieEntityList) } returns movieList

                // WHEN
                val testObserver = useCase.getMoviesOfflineLast().test(this)

                // THEN
                testObserver
                    .assertComplete()
                    .assertNoErrors()
                    .assertValues {
                        it.first().containsAll(movieList)
                    }
                    .dispose()

                coVerify(exactly = 1) { repository.getMoviesFromRemote("",1) }
                coVerify(exactly = 1) { repository.getMoviesFromLocal() }
                coVerify(exactly = 0) { repository.deleteMovies() }
                coVerify(exactly = 0) { repository.saveMovies(movieEntityList) }

                verify(exactly = 1) { entityToPostMapper.map(movieEntityList) }
            }

        @Test
        fun `given exception returned from Local source, should throw DB exception`() =
            testCoroutineExtension.runBlockingTest {

                // GIVEN
                coEvery {
                    repository.getMoviesFromRemote("",1)
                } throws Exception("Network Exception")
                coEvery { repository.deleteMovies() } just runs
                coEvery { repository.saveMovies(moviesEntity = movieEntityList) } just runs
                coEvery {
                    repository.getMoviesFromLocal()
                } throws SQLException("Database Exception")
                coEvery { entityToPostMapper.map(movieEntityList) } returns movieList

                // WHEN
                val testObserver = useCase.getMoviesOfflineLast().test(this)

                // THEN
                testObserver
                    .assertNotComplete()
                    .assertError(SQLException::class.java)
                    .dispose()

                coVerify(exactly = 1) { repository.getMoviesFromRemote("",1) }
                coVerify(exactly = 1) { repository.getMoviesFromLocal() }
                coVerify(exactly = 0) { repository.deleteMovies() }
                coVerify(exactly = 0) { repository.saveMovies(movieEntityList) }

                verify(exactly = 0) { entityToPostMapper.map(movieEntityList) }
            }

        @Test
        fun `given Remote error and Local source is empty, should throw EmptyDataException`() =
            testCoroutineExtension.runBlockingTest {

                // GIVEN
                coEvery {
                    repository.getMoviesFromRemote("",1)
                } throws Exception("Network Exception")

                coEvery { repository.deleteMovies() } just runs
                coEvery { repository.saveMovies(moviesEntity = movieEntityList) } just runs
                coEvery { repository.getMoviesFromLocal() } returns listOf()
                coEvery { entityToPostMapper.map(movieEntityList) } returns movieList

                // WHEN
                val testObserver = useCase.getMoviesOfflineLast().test(this)

                // THEN
                testObserver
                    .assertNotComplete()
                    .assertError(EmptyDataException::class.java)
                    .dispose()

                coVerify(exactly = 1) { repository.getMoviesFromRemote("",1) }
                coVerify(exactly = 1) { repository.getMoviesFromLocal() }
                coVerify(exactly = 0) { repository.deleteMovies() }
                coVerify(exactly = 0) { repository.saveMovies(movieEntityList) }

                verify(exactly = 0) { entityToPostMapper.map(movieEntityList) }
            }
    }


    @AfterEach
    fun tearDown() {
        clearMocks(repository, entityToPostMapper)
    }

    @Test
    fun getMoviesOfflineLast() {
    }

    @Test
    fun getMoviesOfflineFirst() {
    }
}