package com.example.mixta.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mixta.AssetsQuery
import com.example.mixta.CardContent
import com.example.mixta.ui.theme.MixtaTheme

@Composable
fun Assets(modifier: Modifier = Modifier, viewModel: AssetsViewModel = viewModel()) {
    var assets by rememberSaveable { mutableStateOf(emptyList<AssetsQuery.Item?>()) }
    assets = viewModel.assetsUiState

    LazyColumn(modifier.padding(vertical = 4.dp)) {
        items(assets) {
            Asset(it)
        }
    }
}

@Composable
private fun Asset(item: AssetsQuery.Item?, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        if (item != null) {
            CardContent(item)
        }

    }
}

@Preview(showBackground = true, name = "My Asset Composable Preview")
@Composable
fun AssetPreview() {
    MixtaTheme {
        Asset(AssetsQuery.Item("title", "url"))
    }
}

@Preview(showBackground = true, widthDp = 320)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    widthDp = 320,
    name = "Dark Mode"
)
@Composable
fun PreviewAssets() {
    MixtaTheme {
        Surface(color = MaterialTheme.colorScheme.onSurface) {
            Assets()
        }
    }
}