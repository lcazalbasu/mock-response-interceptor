package com.lcazalbasu.mockresponseinterceptor.tools

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Light mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Dark mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class MultipleUiModePreview

@Preview(
    name = "1 - Pixel 2",
    showBackground = true,
    device = Devices.PIXEL_2,
)
annotation class PixelDevicePreviewWithBackground
