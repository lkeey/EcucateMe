package dev.lkeeeey.edu.main.presentation.profile.main

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
import dev.lkeeeey.edu.main.presentation.profile.main.components.Reference
import dev.lkeeeey.edu.main.presentation.profile.main.viewmodel.ProfileAction
import dev.lkeeeey.edu.main.presentation.profile.main.viewmodel.ProfileEvent
import dev.lkeeeey.edu.main.presentation.profile.main.viewmodel.ProfileState
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import ecucateme.composeapp.generated.resources.ic_profile_education
import ecucateme.composeapp.generated.resources.ic_profile_logout
import ecucateme.composeapp.generated.resources.ic_profile_timetable
import ecucateme.composeapp.generated.resources.ic_profile_win
import ecucateme.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProfileView(
    state: ProfileState,
    onEvent: (ProfileEvent) -> Unit,
    onOpenScreen: (ProfileAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Spacer(
            Modifier.height(12.dp)
        )

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

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = state.name, style = TextStyle(
                    fontSize = 17.sp,
                    fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                    fontWeight = FontWeight(600),
                    color = Theme.colors.blackProfile,
                    letterSpacing = 0.4.sp,
                )
            )

            Spacer(
                Modifier.height(12.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
                .background(
                    color = White,
                    shape = RoundedCornerShape(size = 25.dp)
                )
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Reference(
                icon = Res.drawable.ic_profile_win,
                title = "Преподаватели",
                content = "Те, от кого зависит мой успех",
                isVisible = true
            ) {
                onOpenScreen(ProfileAction.OnOpenTeachers)
            }

            Reference(
                icon = Res.drawable.ic_profile_education,
                title = "Предметы",
                content = "Вот это мне нравится!",
                isVisible = true
            ) {
                onOpenScreen(ProfileAction.OnOpenSubjects)
            }

            Reference(
                icon = Res.drawable.ic_profile_timetable,
                title = "Расписание",
                content = "Эх, школа",
                isVisible = true
            ) {
                onOpenScreen(ProfileAction.OnOpenTimeTable)
            }

            Reference(
                icon = Res.drawable.ic_profile_education,
                title = "Успеваемость",
                content = "Здесь мой прогресс",
                isVisible = true
            ) {
//                Toast.makeText(context, "It's developing", Toast.LENGTH_SHORT).show()
            }

            Reference(
                icon = Res.drawable.ic_profile_logout,
                title = "Выйти из аккаунта",
                content = "Уверены?",
                isLogout = true,
            ) {
                onEvent(ProfileEvent.OnLogOut)

                onOpenScreen(ProfileAction.OnOpenLogin)
            }
        }
    }
}
