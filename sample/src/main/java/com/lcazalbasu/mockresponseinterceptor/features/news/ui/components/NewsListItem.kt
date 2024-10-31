package com.lcazalbasu.mockresponseinterceptor.features.news.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.lcazalbasu.mockresponseinterceptor.R
import com.lcazalbasu.mockresponseinterceptor.features.news.data.News
import com.lcazalbasu.mockresponseinterceptor.ui.theme.AppSizes
import com.lcazalbasu.mockresponseinterceptor.ui.theme.AppTypography
import com.lcazalbasu.mockresponseinterceptor.ui.theme.MockResponseInterceptorTheme

@Composable
fun NewsListItem(item: News, modifier: Modifier, onClicked: () -> Unit) {
    Surface(
        modifier = modifier
            .clickable {
                onClicked()
            }
            .background(
                color = MaterialTheme.colorScheme.surface,
                RoundedCornerShape(AppSizes.cardRadius),
            )
            .padding(
                top = AppSizes.spacingMedium,
                bottom = AppSizes.spacingMedium,
                start = AppSizes.spacingRegular,
                end = AppSizes.spacingRegular,
            ),
    ) {
        Row {
            AsyncImage(
                model = item.thumbUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(AppSizes.thumbSize)
                    .clip(CircleShape),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Fit,
            )
            Spacer(modifier = Modifier.width(AppSizes.spacingMedium))
            Column(
                modifier = Modifier
                    .weight(0.9f)
                    .padding(top = AppSizes.spacingSmall),
            ) {
                Text(
                    text = item.title,
                    style = AppTypography.Text17SemiBold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(AppSizes.spacingSmall))
                Text(
                    text = item.subtitle,
                    style = AppTypography.Text12Medium,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(AppSizes.spacingSmall))
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = item.shortDescription,
                    style = AppTypography.Text11Regular,
                    color = Color.Black,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsItemPreview() {
    MockResponseInterceptorTheme {
        NewsListItem(
            item = News(
                id = 1,
                title = "Another sample title ",
                subtitle = "This one is added to favorites",
                shortDescription = "This is a short description",
                thumbUrl = "https://www.laurentiu.cazalbasu.com",
            ),
            modifier = Modifier,
            onClicked = {},
        )
    }
}
