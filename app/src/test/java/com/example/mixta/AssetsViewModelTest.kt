package com.example.mixta

import com.example.mixta.ui.AssetsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class AssetsViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun assetsViewModel_getAssetImages_verifyAssetsUiStateEmptyResult() = runTest {
        val assetsViewModel = AssetsViewModel()
        assertEquals(
            emptyList<List<AssetsQuery.Item?>>(),
            assetsViewModel.assetsUiState
        )
    }

    @Ignore
    @Test
    fun assetsViewModel_getAssetImages_verifyAssetsUiStateSuccess() = runTest {
        // TODO: Test success ui state; dependent on setup of success/failure/loading ui states and
        //  dependency injections of data (repository, service or response results?)
    }
}