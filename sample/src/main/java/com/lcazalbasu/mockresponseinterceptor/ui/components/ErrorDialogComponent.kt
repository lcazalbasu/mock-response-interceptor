package com.lcazalbasu.mockresponseinterceptor.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.lcazalbasu.mockresponseinterceptor.R
import com.lcazalbasu.mockresponseinterceptor.data.models.exceptions.AppException
import com.lcazalbasu.mockresponseinterceptor.ui.theme.AppSizes
import com.lcazalbasu.mockresponseinterceptor.ui.theme.AppTypography
import com.lcazalbasu.mockresponseinterceptor.ui.theme.MockResponseInterceptorTheme

@Composable
fun ErrorDialogComponent(
    exception: AppException,
    onDismiss: () -> Unit,
    onRetry: () -> Unit = { },
) {
    val interactionSource = remember { MutableInteractionSource() }

    Dialog(onDismissRequest = onDismiss) {
        Card {
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .padding(
                        horizontal = AppSizes.dialogPaddingHorizontal,
                        vertical = AppSizes.dialogPaddingVertical,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.label_error_title),
                    textAlign = TextAlign.Center,
                    style = AppTypography.Text24Medium,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.size(AppSizes.spacingRegular))
                Text(
                    text = exception.message ?: exception.localizedMessage ?: exception.toString(),
                    textAlign = TextAlign.Center,
                    style = AppTypography.Text17Regular,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.size(AppSizes.spacingRegular))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        shape = RoundedCornerShape(size = AppSizes.cardRadius),
                        elevation = null,
                        contentPadding = PaddingValues(
                            horizontal = AppSizes.spacingLarge,
                            vertical = AppSizes.spacingMedium,
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                        ),
                        onClick = { onDismiss() },
                        interactionSource = interactionSource,
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_close),
                            style = AppTypography.Text17SemiBold,
                            color = MaterialTheme.colorScheme.tertiary,
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        shape = RoundedCornerShape(size = AppSizes.cardRadius),
                        elevation = null,
                        contentPadding = PaddingValues(
                            horizontal = AppSizes.spacingLarge,
                            vertical = AppSizes.spacingMedium,
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                        ),
                        onClick = {
                            onRetry()
                            onDismiss()
                        },
                        interactionSource = interactionSource,
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_retry),
                            style = AppTypography.Text17SemiBold,
                            color = MaterialTheme.colorScheme.tertiary,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ErrorDialogComponentPreview() {
    MockResponseInterceptorTheme {
        ErrorDialogComponent(
            exception = AppException.UnknownException(Exception("Test")),
            onDismiss = { },
            onRetry = { },
        )
    }
}
