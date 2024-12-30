package dev.lkeeeey.edu.main.presentation.calendar.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageWithText (
    drawable: DrawableResource,
    text: String
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(drawable),
            contentDescription = "no homework",
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                fontWeight = FontWeight(400),
                color = Theme.colors.blackProfile,
                textAlign = TextAlign.Center,
                letterSpacing = 0.5.sp,
            )
        )
    }
}
