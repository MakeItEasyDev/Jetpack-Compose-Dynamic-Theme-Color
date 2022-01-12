package com.jetpack.dynamicthemecolor

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.dynamicthemecolor.ui.theme.DesignSystemColors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _colorState: MutableStateFlow<DesignSystemState> = MutableStateFlow(DesignSystemState.Default)
    val colorState: StateFlow<DesignSystemState> = _colorState

    fun updateDesignSystem() {
        viewModelScope.launch {
            //here we can write api call, now i just add static color
            val colors = themeModal.toDesignSystem()

            _colorState.emit(DesignSystemState.Updated(colors))
        }
    }
}

//Static val
//we have change to text, button, background color
val themeModal = ThemeModal(
    primary = ColorModel(0xFF00ff00),
    primaryVariant = ColorModel(0xFF00ff00),
    onPrimary = ColorModel(0xFF00ff00),
    secondary = ColorModel(0xFF0000ff), // button background color
    onSecondary = ColorModel(0xFFffffff), // text color white
    surface = ColorModel(0xFF00ff00), // background color
    onSurface = ColorModel(0xFF00ff00),
    onBackground = ColorModel(0xFF00ff00),
    error = ColorModel(0xFF00ff00),
    onError = ColorModel(0xFF00ff00),
)

sealed class DesignSystemState {
    object Default: DesignSystemState()
    data class Updated(val colors: DesignSystemColors) : DesignSystemState()
}

private fun ThemeModal.toDesignSystem(): DesignSystemColors = DesignSystemColors(
    primary = Color(primary.value),
    primaryVariant = Color(primaryVariant.value),
    onPrimary = Color(onPrimary.value),
    secondary = Color(secondary.value),
    onSecondary = Color(onSecondary.value),
    surface = Color(surface.value),
    onSurface = Color(onSurface.value),
    onBackground = Color(onBackground.value),
    error = Color(error.value),
    onError = Color(onError.value)
)




















