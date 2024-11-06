package com.freshtawi.tawi.presentation.common.composable.util

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    name = "Small Font",
    group = "Small Font Group",
    fontScale = 0.5f,
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Small Font",
    group = "Small Font Group",
    fontScale = 0.5f,
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true

)

@Preview(
    name = "Normal Font",
    group = "Normal Font Group",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true

)
@Preview(
    name = "Normal Font",
    group = "Normal Font Group",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true

)
@Preview(
    name = "Large Font",
    group = "Large Font Group",
    fontScale = 1.5f,
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true

)
@Preview(
    name = "Large Font",
    group = "Large Font Group",
    fontScale = 1.5f,
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true

)
annotation class FontScalePreviews

@Preview(
    name = "Light Mode",
    group = "Light Mode Group",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true

)
@Preview(
    name = "Dark Mode",
    group = "Dark Mode Group",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true

)
annotation class UiModePreviews

@Preview(
    name = "Pixel XL",
    group = "Device Group",
    device = Devices.PIXEL_XL,
    showSystemUi = true
)
@Preview(
    name = "Pixel 2 ",
    group = "Device Group",
    device = Devices.PIXEL_2,
    showSystemUi = true,
)
@Preview(
    name = "Pixel 3",
    group = "Device Group",
    device = Devices.PIXEL_3,
    showBackground = true
)

@Preview(
    name = "Pixel 4",
    group = "Device Group",
    device = Devices.PIXEL_4,
    showSystemUi = true
)
@Preview(
    name = "Pixel C",
    group = "Device Group",
    device = Devices.PIXEL_C,
    showBackground = true
)

@Preview(
    name = "Nexus 7",
    group = "Device Group",
    device = Devices.NEXUS_7,
    showBackground = true
)
@Preview(
    name = "Nexus 10",
    group = "Device Group",
    device = Devices.NEXUS_10,
    showBackground = true
)
annotation class DevicePreviews




