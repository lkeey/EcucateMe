package dev.lkeeeey.edu.core.presentation.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@Composable
fun FooterAuth (
    content: String,
    offer: String,
    onUrlClicked: () -> Unit
) {
    Text(
        text = content,
        style = TextStyle(
            fontSize = 12.sp,
            lineHeight = 21.sp,
            fontFamily = FontFamily(Font(Res.font.Thin)),
            fontWeight = FontWeight(400),
            color = Theme.colors.blackProfile,
            textAlign = TextAlign.Center
        )
    )

    Text(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onUrlClicked()
            },
        text = offer,
        style = TextStyle(
            fontSize = 12.sp,
            lineHeight = 21.sp,
            fontFamily = FontFamily(Font(Res.font.Thin)),
            fontWeight = FontWeight(400),
            color = Theme.colors.primaryBackground,
        )
    )
}
