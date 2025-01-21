package dev.lkeeeey.edu.auth.presentation.splash

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.auth.presentation.splash.viewmodel.SplashState
import dev.lkeeeey.edu.core.presentation.Theme
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashView () {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .isLoading(true)
            .padding(horizontal = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(Res.drawable.logo),
            contentDescription = "app image name"
        )
    }
}

private fun Modifier.isLoading(isLoading: Boolean) = composed {
    if (isLoading) {
        var size by remember {
            mutableStateOf(IntSize.Zero)
        }

        val transition = rememberInfiniteTransition()
        val startOffsetX by transition.animateFloat(
            initialValue = -2*size.width.toFloat(),
            targetValue = 2*size.width.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(1000)
            )
        )

        background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.Black,
                    Theme.colors.primaryBackground.copy(1f),
                    Color.White
                ),
                start = Offset(startOffsetX, 0f),
                end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
            )
        )
            .onGloballyPositioned {
                size = it.size
            }
    }
    else {
        Modifier
    }
}
