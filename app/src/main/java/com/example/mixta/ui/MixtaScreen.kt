package com.example.mixta.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mixta.R
import com.example.mixta.newscard.NewsCard
import com.example.mixta.newscard.View
import com.example.mixta.ui.theme.MixtaTheme

enum class MixtaScreen {
    Onboarding,
    Assets,
    Conversation,
    News,
}

@Composable
fun OnboardingScreen(
    onAssetsClicked: () -> Unit,
    onConversationClicked: () -> Unit,
    onNewsClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Mixta!", modifier = Modifier.padding(vertical = 24.dp))
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(0.65f),
            onClick = onAssetsClicked
        ) {
            Text(text = "Continue to Design Assets", textAlign = TextAlign.Center)
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.65f),
            onClick = onConversationClicked
        ) {
            Text("Continue to Conversation")
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.65f),
            onClick = onNewsClicked
        ) {
            Text("Continue to News Card")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MixtaApp(navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Mixta") })
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = MixtaScreen.Onboarding.name,
            modifier = Modifier.padding(it)
        ) {
            composable(MixtaScreen.Onboarding.name) {
                OnboardingScreen(
                    onAssetsClicked = { navController.navigate(MixtaScreen.Assets.name) },
                    onConversationClicked = {
                        navController.navigate(
                            MixtaScreen.Conversation.name
                        )
                    },
                    onNewsClicked = { navController.navigate(MixtaScreen.News.name) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(MixtaScreen.Assets.name) {
                Assets(modifier = Modifier.fillMaxSize())
            }
            composable(MixtaScreen.Conversation.name) {
                Conversation(SampleData.conversationSample)
            }
            composable(MixtaScreen.News.name) {
                NewsCard(
                    modifier = Modifier.wrapContentHeight(Alignment.Top),
                    view = View.ArticleItem,
                    thumbnail = painterResource(id = R.drawable.news_card_thumbnail),
                    headline = "Amazing Card Works!",
                    author = "Me Me"
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    MixtaTheme {
        OnboardingScreen(onAssetsClicked = {
            /* empty */
        }, onConversationClicked = { /* empty */ }, onNewsClicked = { /* empty */ })
    }
}

@Preview
@Preview(showBackground = true)
@Composable
fun PreviewMixtaApp() {
    MixtaTheme {
        MixtaApp()
    }
}