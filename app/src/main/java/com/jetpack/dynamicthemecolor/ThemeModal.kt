package com.jetpack.dynamicthemecolor

data class ThemeModal(
    val primary: ColorModel,
    val primaryVariant: ColorModel,
    val onPrimary: ColorModel,
    val secondary: ColorModel,
    val onSecondary: ColorModel,
    val surface: ColorModel,
    val onSurface: ColorModel,
    val onBackground: ColorModel,
    val error: ColorModel,
    val onError: ColorModel
)

data class ColorModel(val value: Long)
