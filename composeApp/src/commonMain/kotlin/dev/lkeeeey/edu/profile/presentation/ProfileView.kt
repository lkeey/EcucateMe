package dev.lkeeeey.edu.profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.profile.presentation.viewmodel.ProfileEvent
import dev.lkeeeey.edu.profile.presentation.viewmodel.ProfileState
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import ecucateme.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProfileView(
    state: ProfileState,
    onEvent: (ProfileEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = White, shape = RoundedCornerShape(
                        topStart = 0.dp, topEnd = 0.dp, bottomEnd = 40.dp, bottomStart = 40.dp
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(95.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape),
                painter = painterResource(Res.drawable.profile),
                contentDescription = "custom transition based on painter state",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.name, style = TextStyle(
                    fontSize = 17.sp,
                    fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                    fontWeight = FontWeight(600),
                    color = Theme.colors.blackProfile,
                    letterSpacing = 0.4.sp,
                )
            )
        }
    }
}
