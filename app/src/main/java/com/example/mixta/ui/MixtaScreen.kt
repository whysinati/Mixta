package com.example.mixta.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mixta.R
import com.example.mixta.newscard.NewsCard
import com.example.mixta.newscard.View
import com.example.mixta.ui.theme.MixtaTheme

enum class MixtaScreen(@StringRes val title: Int) {
    Onboarding(title = R.string.app_name),
    Assets(title = R.string.assets),
    Conversation(title = R.string.conversation),
    News(title = R.string.news),
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
        Text(
            stringResource(R.string.welcome_to_mixta),
            modifier = Modifier.padding(vertical = 24.dp)
        )
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
            Text(
                text = stringResource(R.string.continue_to_design_assets),
                textAlign = TextAlign.Center
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.65f),
            onClick = onConversationClicked
        ) {
            Text(stringResource(R.string.continue_to_conversation))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.65f),
            onClick = onNewsClicked
        ) {
            Text(stringResource(R.string.continue_to_news_card))
        }
    }
}

@Composable
fun MixtaApp(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MixtaScreen
        .valueOf(backStackEntry?.destination?.route ?: MixtaScreen.Onboarding.name)

    Scaffold(
        topBar = {
            MixtaAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MixtaAppBar(
    currentScreen: MixtaScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
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