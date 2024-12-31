package dev.lkeeeey.edu.main.presentation.profile.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import ecucateme.composeapp.generated.resources.ic_profile_btn_back
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun Reference(
    icon: DrawableResource,
    title: String,
    content: String,
    isVisible: Boolean = true,
    isShowBack: Boolean = true,
    isLogout: Boolean = false,
    onClick: () -> Unit
) {
    if(isVisible) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp
                )
                .clip(RoundedCornerShape(20.dp))
                .clickable(onClick = onClick),
            horizontalArrangement = if (isShowBack) Arrangement.SpaceEvenly else Arrangement.Start
        ) {
            Spacer(modifier = Modifier.width(8.dp))

            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                painter = painterResource(icon),
                contentDescription = "reference icon",
                alignment = Alignment.CenterEnd,
                colorFilter = ColorFilter.tint(color = if (isLogout) Theme.colors.errorColor else Theme.colors.primaryBackground.copy(alpha = 1f))
            )

            Spacer(modifier = Modifier.width(24.dp))

            Column {
                Text(
                    text = title, style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                        fontWeight = FontWeight(500),
                        color = Theme.colors.blackProfile,
                        letterSpacing = 0.3.sp,
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = content, style = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                        fontWeight = FontWeight(400),
                        color = Theme.colors.greyDescription,
                        letterSpacing = 0.2.sp,
                    )
                )
            }
            if (isShowBack) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    painter = painterResource(Res.drawable.ic_profile_btn_back),
                    contentDescription = "button back",
                    alignment = Alignment.CenterEnd
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(29.dp),
        )
    }
}
