package com.jetpack.dynamicthemecolor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants.defaultButtonColors
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.dynamicthemecolor.ui.theme.DesignSystemTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel)
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun MainScreen(viewModel: MainViewModel) {
    DesignSystemTheme {
        val designSystemState by viewModel.colorState.collectAsState()

        when (designSystemState) {
            DesignSystemState.Default -> Unit
            is DesignSystemState.Updated -> {
                val updated = designSystemState as DesignSystemState.Updated
                DesignSystemTheme.colors.update(updated.colors)
            }
        }

        MainLayout(viewModel::updateDesignSystem)
    }
}

@Composable
fun MainLayout(
    click: () -> Unit = {}
) {
    DesignSystemSurface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            DesignSystemText(
                text = "Welcome to, Dynamic Theme",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                DesignSystemButton(
                    click = click,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(20.dp)
                ) {
                    DesignSystemText(
                        text = "Load Theme"
                    )
                }
            }
        }
    }
}

//Text color
@Composable
fun DesignSystemText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            color = DesignSystemTheme.colors.onSecondary,
            fontSize = 16.sp
        )
    )
}

//Button color
@Composable
fun DesignSystemButton(
    modifier: Modifier = Modifier,
    click: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = click,
        content = content,
        modifier = modifier,
        colors = defaultButtonColors(
            backgroundColor = DesignSystemTheme.colors.secondary,
            contentColor = DesignSystemTheme.colors.onSecondary
        )
    )
}

//Surface color
@Composable
fun DesignSystemSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        color = DesignSystemTheme.colors.surface,
        content = content
    )
}

















