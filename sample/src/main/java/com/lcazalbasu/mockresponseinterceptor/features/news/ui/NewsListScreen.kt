package com.lcazalbasu.mockresponseinterceptor.features.news.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lcazalbasu.mockresponseinterceptor.features.news.data.News
import com.lcazalbasu.mockresponseinterceptor.features.news.ui.components.NewsListItem
import com.lcazalbasu.mockresponseinterceptor.navigation.Destinations
import com.lcazalbasu.mockresponseinterceptor.tools.MultipleUiModePreview
import com.lcazalbasu.mockresponseinterceptor.ui.components.ErrorDialogComponent
import com.lcazalbasu.mockresponseinterceptor.ui.components.PullToRefreshBox
import com.lcazalbasu.mockresponseinterceptor.ui.theme.AppSizes
import com.lcazalbasu.mockresponseinterceptor.ui.theme.MockResponseInterceptorTheme

@Composable
fun NewsListScreen(
    viewModel: NewsListViewModel = hiltViewModel(),
    onNavigateTo: (Destinations.NewsListDestinations) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenContent(
        state,
        onNewsClick = {
            onNavigateTo(Destinations.NewsListDestinations.ToDetail(id = it.id))
        },
        onRefreshList = {
            viewModel.refreshList()
        },
    )

    val exception = state.exception
    if (exception != null) {
        ErrorDialogComponent(
            onDismiss = {
                viewModel.onErrorDialogClosed()
            },
            onRetry = {
                viewModel.refreshList()
            },
            exception = exception,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    state: NewsListState,
    onRefreshList: () -> Unit,
    onNewsClick: (news: News) -> Unit,
) {
    val lazyListState = rememberLazyListState()

    PullToRefreshBox(
        isRefreshing = state.isLoading,
        onRefresh = onRefreshList,
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        LazyColumn(
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = AppSizes.spacingRegular),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(state.list) {
                NewsListItem(
                    modifier = Modifier.padding(
                        top = if (state.list.firstOrNull() == it) AppSizes.zero else AppSizes.spacingRegular,
                        bottom = if (state.list.lastOrNull() == it) AppSizes.spacingRegular else AppSizes.zero,
                    ),
                    item = it,
                    onClicked = {
                        onNewsClick(it)
                    },
                )
            }
        }
    }
}

@Composable
@MultipleUiModePreview
private fun NewsListScreenPreview() {
    MockResponseInterceptorTheme {
        ScreenContent(
            state = NewsListState(
                list = listOf(
                    News(
                        id = 0,
                        title = "Sample title",
                        subtitle = "This is a sample subtitle",
                        shortDescription = "This is a sample short description",
                        thumbUrl = "https://test.com",
                    ),
                    News(
                        id = 0,
                        title = "Sample title",
                        subtitle = "This is a sample subtitle",
                        shortDescription = "This is a sample short description",
                        thumbUrl = "https://test.com",
                    ),
                ),
                exception = null,
                isLoading = false,
            ),
            onNewsClick = {},
            onRefreshList = {},
        )
    }
}
