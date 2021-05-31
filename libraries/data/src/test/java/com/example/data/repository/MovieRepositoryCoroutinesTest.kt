package com.example.data.repository

import com.example.data.mapper.DTOtoEntityMapper
import com.example.data.model.Movie
import com.example.data.model.MovieEntity
import com.example.data.source.local.LocalMoviesDataSource
import com.example.data.source.remote.RemoteDataSource
import com.example.test_util.RESPONSE_JSON_PATH
import com.example.test_util.utils.convertFromJsonToListOf
import com.example.test_util.utils.getResourceAsText
import com.google.common.truth.Truth
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PostRepositoryCoroutinesTest {

    private lateinit var repository: MoviesRepositoryImpl

    private val localPostDataSource: LocalMoviesDataSource = mockk()
    private val remotePostDataSource: RemoteDataSource = mockk()
    private val mapper: DTOtoEntityMapper = mockk()

    companion object {
        val postDTOList =
            convertFromJsonToListOf<Movie>(getResourceAsText(RESPONSE_JSON_PATH))!!

        val postEntityList =
            convertFromJsonToListOf<MovieEntity>(getResourceAsText(RESPONSE_JSON_PATH))!!
    }

    @Test
    fun `given network error occurred, should throw Exception`() = runBlockingTest {

        // GIVEN
        coEvery { remotePostDataSource.getMovies("",1) } throws Exception("Network Exception")
        every { mapper.map(postDTOList) } returns postEntityList

        // WHEN
        val expected = try {
            repository.getMoviesFromRemote("",1)
        } catch (e: Exception) {
            e
        }

        // THEN
        Truth.assertThat(expected).isInstanceOf(Exception::class.java)
        coVerify(exactly = 1) { remotePostDataSource.getMovies("",1) }
        verify(exactly = 0) { mapper.map(postDTOList) }
    }

    @Test
    fun `given remote data source return PostDTO list, should return PostEntity list`() =
        runBlockingTest {

            // GIVEN
            val actual = postEntityList
            coEvery { remotePostDataSource.getMovies("",1) } returns postDTOList
            every { mapper.map(postDTOList) } returns postEntityList

            // WHEN
            val expected = repository.getMoviesFromRemote("",1)

            // THEN
            Truth.assertThat(expected).isEqualTo(actual)
            coVerifyOrder {
                remotePostDataSource.getMovies("",1)
                mapper.map(postDTOList)
            }
        }

    @Test
    fun `given DB is empty should return an empty list`() = runBlockingTest {

        // GIVEN
        val expected = listOf<MovieEntity>()
        coEvery { localPostDataSource.getMoviesEntities() } returns expected

        // WHEN
        val actual = repository.getMoviesFromLocal()

        // THEN
        Truth.assertThat(actual).isEmpty()
        coVerify(exactly = 1) { localPostDataSource.getMoviesEntities() }
    }

    @Test
    fun `given DB is populated should return data list`() = runBlockingTest {

        // GIVEN
        coEvery { localPostDataSource.getMoviesEntities() } returns postEntityList

        // WHEN
        val actual = repository.getMoviesFromLocal()

        // THEN
        Truth.assertThat(actual)
            .containsExactlyElementsIn(postEntityList)
        coVerify(exactly = 1) { localPostDataSource.getMoviesEntities() }
    }

    @Test
    fun `given entities, should save entities`() = runBlockingTest {

        // GIVEN
        val idList = postEntityList.map {
            it.id.toLong()
        }

        coEvery {
            localPostDataSource.saveEntities(postEntityList)
        } returns idList

        // WHEN
        val result = localPostDataSource.saveEntities(postEntityList)

        // THEN
        Truth.assertThat(result).containsExactlyElementsIn(idList)
        coVerify(exactly = 1) { localPostDataSource.saveEntities(postEntityList) }
    }

    @Test
    fun `given no error should delete entities`() = runBlockingTest {

        // GIVEN
        coEvery { localPostDataSource.deletePostEntities() } just runs

        // WHEN
        repository.deleteMovies()

        // THEN
        coVerify(exactly = 1) {
            localPostDataSource.deletePostEntities()
        }
    }

    @BeforeEach
    fun setUp() {
        repository = MoviesRepositoryImpl(localPostDataSource, remotePostDataSource, mapper)
    }

    @AfterEach
    fun tearDown() {
        clearMocks(localPostDataSource, remotePostDataSource, mapper)
    }
}
