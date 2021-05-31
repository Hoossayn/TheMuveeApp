package com.example.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.core.viewstate.Status
import com.example.domain.model.Movie
import com.example.domain.usecases.GetMoviesUseCase
import com.example.test_util.RESPONSE_JSON_PATH
import com.example.test_util.rule.TestCoroutineRule
import com.example.test_util.testObserver.test
import com.example.test_util.utils.convertFromJsonToListOf
import com.example.test_util.utils.getResourceAsText
import com.google.common.truth.Truth
import io.mockk.*
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class MovieListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    /**
     * Rule for testing Coroutines with [TestCoroutineScope] and [TestCoroutineDispatcher]
     */
    @Rule
    @JvmField
    var testCoroutineRule = TestCoroutineRule()

    private val postList =
        convertFromJsonToListOf<Movie>(getResourceAsText(RESPONSE_JSON_PATH))!!

    private val useCase: GetMoviesUseCase = mockk()
    private val savedStateHandle: SavedStateHandle = spyk()

    private lateinit var viewModel: MovieListViewModel

    @Before
    fun setUp() {
        viewModel =
            MovieListViewModel(testCoroutineRule.testCoroutineScope, useCase, savedStateHandle)
    }


    @Test
    fun `given exception returned from useCase, should have ViewState ERROR offlineFirst`() =
        testCoroutineRule.runBlockingTest {

            // GIVEN
            every {
                useCase.getMoviesOfflineFirst()
            } returns flow<List<Movie>> {
                emit(throw Exception("Network Exception"))
            }

            val testObserver = viewModel.moviesViewState.test()

            // WHEN
            viewModel.getMovies()

            // THEN
            testObserver
                .assertValue { states ->
                    (
                            states[0].status == Status.LOADING &&
                                    states[1].status == Status.ERROR
                            )
                }

            val finalState = testObserver.values()[1]
            Truth.assertThat(finalState.error?.message).isEqualTo("Network Exception")
            Truth.assertThat(finalState.error).isInstanceOf(Exception::class.java)
            verify(atMost = 1) { useCase.getMoviesOfflineFirst() }
        }

    @Test
    fun `given useCase fetched data, should have ViewState SUCCESS and data offlineFirst`() =
        testCoroutineRule.runBlockingTest {

            // GIVEN
            every { useCase.getMoviesOfflineFirst() } returns flow<List<Movie>> {
                emit(postList)
            }

            val testObserver = viewModel.moviesViewState.test()

            // WHEN
            viewModel.getMovies()

            // THEN
            val viewStates = testObserver.values()
            Truth.assertThat(viewStates.first().status).isEqualTo(Status.LOADING)

            val actual = viewStates.last().data
            Truth.assertThat(actual?.size).isEqualTo(20)
            verify(exactly = 1) { useCase.getMoviesOfflineFirst() }
            testObserver.dispose()
        }

    @Test
    fun `given exception returned from useCase, should have ViewState ERROR offlineLast`() =
        testCoroutineRule.runBlockingTest {

            // GIVEN
            every {
                useCase.getMoviesOfflineLast()
            } returns flow<List<Movie>> {
                emit(throw Exception("Network Exception"))
            }

            val testObserver = viewModel.moviesViewState.test()

            // WHEN
            viewModel.refreshMovies()


            // THEN
            testObserver
                .assertValue { states ->
                    (
                            states[0].status == Status.LOADING &&
                                    states[1].status == Status.ERROR
                            )
                }
                .dispose()

            val finalState = testObserver.values()[1]
            Truth.assertThat(finalState.error?.message).isEqualTo("Network Exception")
            Truth.assertThat(finalState.error).isInstanceOf(Exception::class.java)
            verify(atMost = 1) { useCase.getMoviesOfflineLast() }
        }

    @Test
    fun `given useCase fetched data, should have ViewState SUCCESS and data offlineLast`() =
        testCoroutineRule.runBlockingTest {

            // GIVEN
            every { useCase.getMoviesOfflineLast() } returns flow<List<Movie>> {
                emit(postList)
            }

            val testObserver = viewModel.moviesViewState.test()

            // WHEN
            viewModel.refreshMovies()

            // THEN
            val viewStates = testObserver.values()
            Truth.assertThat(viewStates.first().status).isEqualTo(Status.LOADING)

            val actual = viewStates.last().data
            Truth.assertThat(actual?.size).isEqualTo(20)
            verify(exactly = 1) { useCase.getMoviesOfflineLast() }
            testObserver.dispose()
        }

    @After
    fun tearDown() {
        clearMocks(useCase, savedStateHandle)
    }

}