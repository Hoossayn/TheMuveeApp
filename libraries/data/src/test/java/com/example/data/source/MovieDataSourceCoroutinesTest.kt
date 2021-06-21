package com.example.data.source

import com.example.data.api.MovieApi
import com.example.data.db.dao.MovieDao
import com.example.data.model.movies.Movie
import com.example.data.model.movies.MovieEntity
import com.example.data.source.local.LocalDataSourceImpl
import com.example.data.source.remote.RemoteDataSourceImpl
import com.example.test_util.RESPONSE_JSON_PATH
import com.example.test_util.utils.convertFromJsonToListOf
import com.example.test_util.utils.getResourceAsText
import com.google.common.truth.Truth
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.*

class PostDataSourceCoroutinesTest {

    companion object {
        val postDTOList =
            convertFromJsonToListOf<Movie>(getResourceAsText(RESPONSE_JSON_PATH))!!

        val postEntityList =
            convertFromJsonToListOf<MovieEntity>(getResourceAsText(RESPONSE_JSON_PATH))!!
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class RemoteDataSourceTest {

        private val postApi = mockk<MovieApi>()

        private lateinit var remotePostDataSource: RemoteDataSourceImpl

        @Test
        fun `given network error occurred, should throw Exception`() = runBlockingTest {

            // GIVEN
            coEvery { postApi.getMovies("",1) } throws Exception("Network Exception")

            // WHEN
            val expected = try {
                remotePostDataSource.getMovies("",1)
            } catch (e: Exception) {
                e
            }

            // THEN
            Truth.assertThat(expected).isInstanceOf(Exception::class.java)
            coVerify(exactly = 1) { postApi.getMovies("",1) }
        }

        @Test
        fun `given Http 200, should return DTO list`() = runBlockingTest {

            // GIVEN
            val actual = postDTOList
            coEvery { postApi.getMovies("",1) } returns actual

            // WHEN
            val expected = remotePostDataSource.getMovies("",1)

            // THEN
            Truth.assertThat(expected).isEqualTo(actual)
            coVerify(exactly = 1) { postApi.getMovies("",1) }
        }

        @BeforeEach
        fun setUp() {
            remotePostDataSource = RemoteDataSourceImpl(postApi)
        }

        @AfterEach
        fun tearDown() {
            clearMocks(postApi)
        }
    }

    @Nested
    inner class LocalDataSourceTest {

        private val postDao = mockk<MovieDao>()

        private lateinit var localPostDataSource: LocalDataSourceImpl

        @Test
        fun `given DB is empty should return an empty list`() = runBlockingTest {

            // GIVEN
            val expected = listOf<MovieEntity>()
            coEvery { postDao.getMovieList() } returns expected

            // WHEN
            val actual = localPostDataSource.getMoviesEntities()

            // THEN
            Truth.assertThat(actual).isEmpty()
            coVerify(exactly = 1) { postDao.getMovieList() }
        }

        @Test
        fun `given DB is populated should return data list`() = runBlockingTest {

            // GIVEN
            coEvery { postDao.getMovieList() } returns postEntityList

            // WHEN
            val actual = localPostDataSource.getMoviesEntities()

            // THEN
            Truth.assertThat(actual).containsExactlyElementsIn(postEntityList)
            coVerify(exactly = 1) { postDao.getMovieList() }
        }

        @Test
        fun `given entities, should save entities to DB`() = runBlockingTest {

            // GIVEN
            val idList = postEntityList.map {
                it.id.toLong()
            }

            coEvery { postDao.insert(postEntityList) } returns idList

            // WHEN
            val result = localPostDataSource.saveMoviesEntities(postEntityList)

            // THEN
            Truth.assertThat(result).containsExactlyElementsIn(idList)
            coVerify(exactly = 1) { postDao.insert(postEntityList) }
        }

        @Test
        fun `given no error should delete entities`() = runBlockingTest {

            // GIVEN
            coEvery { postDao.deleteAll() } just runs

            // WHEN
            localPostDataSource.deletePostEntities()

            // THEN
            coVerify(exactly = 1) { postDao.deleteAll() }
        }

        @BeforeEach
        fun setUp() {
            localPostDataSource = LocalDataSourceImpl(postDao)
        }

        @AfterEach
        fun tearDown() {
            clearMocks(postDao)
        }
    }
}
