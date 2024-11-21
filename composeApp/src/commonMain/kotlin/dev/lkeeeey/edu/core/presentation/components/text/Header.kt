package dev.lkeeeey.edu.core.presentation.components.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import ecucateme.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun Header (
    actionText: String
){

    Spacer(modifier = Modifier.padding(top = 8.dp))

    Image (
        modifier = Modifier
            .fillMaxWidth(),
        painter = painterResource(Res.drawable.logo),
        contentDescription = "image auth my olimp"
    )

    Spacer(modifier = Modifier.padding(top = 16.dp))

    Text(
        text = actionText,
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(Res.font.Thin)),
            fontWeight = FontWeight(700),
            color = Theme.colors.defaultText,
            textAlign = TextAlign.Center,
        )
    )

    Spacer(modifier = Modifier.height(22.dp))


}
