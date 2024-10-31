package com.lcazalbasu.mockresponseinterceptor.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lcazalbasu.mockresponseinterceptor.R
import com.lcazalbasu.mockresponseinterceptor.ui.theme.AppTypography
import com.lcazalbasu.mockresponseinterceptor.ui.theme.MockResponseInterceptorTheme

private const val HEADER_HEIGHT = 56
private const val TOUCH_SIZE = 56
private const val ICON_SIZE = 32

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppBar(
    titleText: String,
    modifier: Modifier = Modifier,
    hasBackButton: Boolean = false,
    contentAction: @Composable (() -> Unit)? = null,
    onBackPressed: () -> Unit = {},
    onActionClicked: () -> Unit = {},
    onActionLongClick: (() -> Unit) = {},
    onTitleClick: (() -> Unit) = {},
    onTitleLongClick: (() -> Unit) = {},
) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.tertiary)
            .fillMaxWidth()
            .height(HEADER_HEIGHT.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .clickable(enabled = hasBackButton) {
                    onBackPressed()
                }
                .size(TOUCH_SIZE.dp),
            contentAlignment = Alignment.Center,
        ) {
            if (hasBackButton) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_left_white),
                    contentDescription = "Go back",
                    modifier = modifier.size(ICON_SIZE.dp),
                    tint = MaterialTheme.colorScheme.secondary,
                )
            }
        }

        Text(
            text = titleText,
            maxLines = 2,
            style = AppTypography.Text17SemiBold,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .combinedClickable(
                    onLongClick = {
                        onTitleLongClick.invoke()
                    },
                    onClick = {
                        onTitleClick.invoke()
                    },
                ),
        )

        Box(
            modifier = Modifier
                .combinedClickable(
                    onLongClick = {
                        onActionLongClick.invoke()
                    },
                    onClick = {
                        onActionClicked.invoke()
                    },
                )
                .size(TOUCH_SIZE.dp),
            contentAlignment = Alignment.Center,
        ) {
            contentAction?.invoke()
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewHeaderTitle() {
    MockResponseInterceptorTheme {
        AppBar(
            titleText = "Test Header Looooooooong",
            modifier = Modifier,
            hasBackButton = false,
            onActionClicked = {},
            onBackPressed = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewHeaderChevron() {
    MockResponseInterceptorTheme {
        AppBar(
            titleText = "Test Header",
            modifier = Modifier,
            hasBackButton = true,
            onActionClicked = {},
            onBackPressed = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun AppBarPreview() {
    MockResponseInterceptorTheme {
        AppBar(
            titleText = "Test Header",
            modifier = Modifier,
            contentAction = {
                ImageVector.vectorResource(
                    id = R.drawable.ic_bin,
                )
            },
            hasBackButton = true,
            onActionClicked = {},
            onBackPressed = {},
        )
    }
}
