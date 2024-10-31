package com.lcazalbasu.mockresponseinterceptor.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.lcazalbasu.mockresponseinterceptor.R

@Composable
fun Loading() {
    var animatedValue by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        animate(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(FULL_ROTATION_DURATION, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
            ),
            block = { value, _ ->
                animatedValue = value
            },
        )
    }

    Dialog(onDismissRequest = {}) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(R.drawable.loading_ellipse),
                contentDescription = null,
                modifier = Modifier
                    .graphicsLayer {
                        rotationZ = animatedValue
                    },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingComponentPreview() {
    Loading()
}

private const val FULL_ROTATION_DURATION = 1000
