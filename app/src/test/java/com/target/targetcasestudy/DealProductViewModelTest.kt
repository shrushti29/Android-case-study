package com.target.targetcasestudy

import com.target.targetcasestudy.domain.model.DealProductItemModel
import com.target.targetcasestudy.domain.model.PriceModel
import com.target.targetcasestudy.domain.usecase.ExecuteDealUseCase
import com.target.targetcasestudy.ui.state.DealControlState
import com.target.targetcasestudy.ui.state.DealItemControlState
import com.target.targetcasestudy.ui.viewModel.DealProductViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class DealProductViewModelTest {
    private lateinit var dealProductViewModel: DealProductViewModel

    @Mock
    private lateinit var executeDealApiUseCase: ExecuteDealUseCase


    @get:Rule
    var testDispatcherRule = TestCoroutineDispatcherRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dealProductViewModel = DealProductViewModel(executeDealApiUseCase)
    }


    @Test
    fun test_for_fetch_all_data_loading_state() = runBlocking {
        val dealProductItem = DealProductItemModel(
            0, "test", "test", "test", "test", "test", "test",
            PriceModel(0, "test", "test"), PriceModel(0, "test", "test")
        )
        val dealProductItemList = listOf(dealProductItem)

        Mockito.`when`(executeDealApiUseCase.fetchAllDeals())
            .thenReturn(Result.success(dealProductItemList))

        dealProductViewModel.getDealsApi()


        val loadingState = dealProductViewModel.getDealLiveData().getOrAwaitValue()
        assertTrue(loadingState is DealControlState.Loading)

    }


    @Test
    fun test_for_fetch_all_data_success() = runBlocking {
        val dealProductItem = DealProductItemModel(
            0, "test", "test", "test", "test", "test", "test",
            PriceModel(0, "test", "test"), PriceModel(0, "test", "test")
        )
        val dealProductItemList = listOf(dealProductItem)

        Mockito.`when`(executeDealApiUseCase.fetchAllDeals())
            .thenReturn(Result.success(dealProductItemList))

        dealProductViewModel.getDealsApi()

        val result = withTimeoutOrNull(10000) {
            dealProductViewModel.getDealLiveData().getOrAwaitValue()
        }

        if (result == null) {
            fail("Timed out waiting for FetchAllItems state")
        } else {

            when (result) {
                is DealControlState.FetchAllItems -> {
                    assertNotNull(result.items)
                    assertEquals(1, result.items.size)
                    assertEquals(dealProductItem, result.items[0])
                }

                else -> fail("Expected FetchAllItems state")
            }
        }
    }

    @Test
    fun test_for_fetch_all_data_fail() = runBlocking {
        val exception = RuntimeException("test")
        Mockito.`when`(executeDealApiUseCase.fetchAllDeals())
            .thenReturn(Result.failure(exception))

        dealProductViewModel.getDealsApi()

        val result = withTimeoutOrNull(10000) {
            dealProductViewModel.getDealLiveData().getOrAwaitValue()
        }
        when (result) {
            is DealControlState.Failure -> {
                assertEquals("test", result.throwable.message)

            }

            else -> fail("Expected FetchAllItems state")
        }
    }

    @Test
    fun test_for_fetch_item_by_id_loading_state() = runBlocking {
        val dealProductItem = DealProductItemModel(
            0, "test", "test", "test", "test", "test", "test",
            PriceModel(0, "test", "test"), PriceModel(0, "test", "test")
        )

        Mockito.`when`(executeDealApiUseCase.fetchDealById(1))
            .thenReturn(Result.success(dealProductItem))

        dealProductViewModel.fetchItemById(1)


        val loadingState = dealProductViewModel.getDealItemLiveData().getOrAwaitValue()
        assertTrue(loadingState is DealItemControlState.Loading)

    }


    @Test
    fun test_for_fetch_item_by_id_success() = runBlocking {
        val dealProductItem = DealProductItemModel(
            0, "test", "test", "test", "test", "test", "test",
            PriceModel(0, "test", "test"), PriceModel(0, "test", "test")
        )


        Mockito.`when`(executeDealApiUseCase.fetchDealById(1))
            .thenReturn(Result.success(dealProductItem))

        dealProductViewModel.fetchItemById(1)

        val result = withTimeoutOrNull(10000) {
            dealProductViewModel.getDealItemLiveData().getOrAwaitValue()
        }

        if (result == null) {
            fail("Timed out waiting for FetchAllItems state")
        } else {

            when (result) {
                is DealItemControlState.FetchItemById -> {
                    assertNotNull(result.item)
                    assertEquals(dealProductItem, result.item)
                }

                else -> fail("Expected FetchAllItems state")
            }
        }
    }

    @Test
    fun test_for_fetch_item_by_id_fail() = runBlocking {
        val exception = RuntimeException("test")
        Mockito.`when`(executeDealApiUseCase.fetchDealById(1))
            .thenReturn(Result.failure(exception))

        dealProductViewModel.fetchItemById(1)



        val result = withTimeoutOrNull(10000) {
            dealProductViewModel.getDealItemLiveData().getOrAwaitValue(5)
        }

        when (result) {
            is DealItemControlState.Failure -> {
                assertEquals("test", result.throwable.message)

            }

            else -> fail("Expected FetchAllItems state but found $result")
        }
    }
}
