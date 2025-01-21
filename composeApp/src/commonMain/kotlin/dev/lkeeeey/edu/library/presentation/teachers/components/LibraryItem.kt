package dev.lkeeeey.edu.library.presentation.teachers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import ecucateme.composeapp.generated.resources.ic_tg
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun LibraryItem(
    subject: String,
    title: String,
    paint: DrawableResource,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .paint(
                painter = painterResource(paint),
                contentScale = ContentScale.FillBounds
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .padding(start = 8.dp, top = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = subject,
                modifier = Modifier
                    .background(
                        color = Theme.colors.backgroundMain,
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .padding(horizontal = 2.dp, vertical = 2.dp),
                style = TextStyle(
                    color = Theme.colors.blackProfile,
                    fontSize = 8.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp)
                .align(Alignment.BottomStart),
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .padding(horizontal = 2.dp, vertical = 2.dp),
                style = TextStyle(
                    color = White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
