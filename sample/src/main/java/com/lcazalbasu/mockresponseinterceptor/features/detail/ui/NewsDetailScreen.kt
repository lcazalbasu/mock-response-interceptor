package com.lcazalbasu.mockresponseinterceptor.features.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.lcazalbasu.mockresponseinterceptor.R
import com.lcazalbasu.mockresponseinterceptor.features.detail.data.NewsDetail
import com.lcazalbasu.mockresponseinterceptor.tools.PixelDevicePreviewWithBackground
import com.lcazalbasu.mockresponseinterceptor.ui.components.AppBar
import com.lcazalbasu.mockresponseinterceptor.ui.components.ErrorDialogComponent
import com.lcazalbasu.mockresponseinterceptor.ui.components.Loading
import com.lcazalbasu.mockresponseinterceptor.ui.theme.AppSizes
import com.lcazalbasu.mockresponseinterceptor.ui.theme.AppTypography
import com.lcazalbasu.mockresponseinterceptor.ui.theme.MockResponseInterceptorTheme
import com.lcazalbasu.mockresponseinterceptor.utils.emptyString

@Composable
fun NewsDetailScreen(viewModel: NewsDetailViewModel = hiltViewModel(), onBackPressed: () -> Unit) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenContent(
        state = state,
        onBackPressed = onBackPressed,
    )

    val exception = state.exception
    if (exception != null) {
        ErrorDialogComponent(
            onDismiss = { viewModel.onErrorDialogClosed() },
            exception = exception,
        )
    }
    if (state.isLoading) {
        Loading()
    }
}

@Composable
private fun ScreenContent(state: NewsDetailState, onBackPressed: () -> Unit) {
    Column(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.background,
        ),
    ) {
        AppBar(
            titleText = emptyString(),
            hasBackButton = true,
            onBackPressed = onBackPressed,
        )
        state.detail?.let {
            NewsDetailBody(detail = it)
        }
    }
}

@Composable
fun NewsDetailBody(modifier: Modifier = Modifier, detail: NewsDetail) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Column(modifier = modifier) {
        AsyncImage(
            model = detail.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight / 3),
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            error = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.size(AppSizes.spacingLarge))
        Text(
            modifier = Modifier.padding(horizontal = AppSizes.spacingRegular),
            text = detail.title,
            textAlign = TextAlign.Justify,
            style = AppTypography.Text24Bold,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.size(AppSizes.spacingRegular))

        Text(
            modifier = Modifier.padding(horizontal = AppSizes.spacingRegular),
            text = detail.subtitle,
            textAlign = TextAlign.Justify,
            style = AppTypography.Text17SemiBold,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.size(AppSizes.spacingRegular))
        Text(
            modifier = Modifier.padding(horizontal = AppSizes.spacingRegular),
            text = detail.content,
            textAlign = TextAlign.Justify,
            style = AppTypography.Text17Regular,
            color = Color.Black,
        )
    }
}

@Composable
@PixelDevicePreviewWithBackground
private fun NewsDetailScreenPreview() {
    MockResponseInterceptorTheme {
        ScreenContent(
            state = NewsDetailState.default().copy(
                detail = NewsDetail(
                    id = 1,
                    title = "The Great Gatsby",
                    subtitle = "F. Scott Fitzgerald",
                    shortDescription = "The Great Gatsby is a novel by American author F. Scott Fitzgerald. The book was first published in 1925, and it has been republished in 1945 and 1953. There are many editions of this book, and this is the first edition.",
                    content = "The Great Gatsby is a novel by American author F. Scott Fitzgerald. The book was first published in 1925, and it has been republished in 1945 and 1953. There are many editions of this book, and this is the first edition",
                    imageUrl = "https://images-na.ssl-images-amazon.com/images/I/51ZJ2q4%2B%2BPL._SX331_BO1,204,203,200_.jpg",
                ),
            ),
            onBackPressed = {},
        )
    }
}
