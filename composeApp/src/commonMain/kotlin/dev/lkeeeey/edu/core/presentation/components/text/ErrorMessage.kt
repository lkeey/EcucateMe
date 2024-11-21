package dev.lkeeeey.edu.core.presentation.components.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme

@Composable
fun ErrorMessage (
    text: String
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 12.sp,
//                fontFamily = FontFamily(Font(Res.font.)),
                fontWeight = FontWeight(400),
                color = Theme.colors.errorColor,
                textAlign = TextAlign.Start,
            ),
        )
    }
}
