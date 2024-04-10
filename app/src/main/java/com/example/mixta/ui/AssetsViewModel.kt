package com.example.mixta.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mixta.AssetsQuery
import com.example.mixta.apolloClient
import kotlinx.coroutines.launch

class AssetsViewModel : ViewModel() {

    var assetsUiState: List<AssetsQuery.Item?> by mutableStateOf(emptyList())
        private set

    init {
        getAssetImages()
    }

    private fun getAssetImages() {
        viewModelScope.launch {
            val response = apolloClient.query(AssetsQuery()).execute()
            Log.d("Assets", "Success ${response.data}")

            assetsUiState = response.data?.assetCollection?.items ?: emptyList()
        }
    }
}