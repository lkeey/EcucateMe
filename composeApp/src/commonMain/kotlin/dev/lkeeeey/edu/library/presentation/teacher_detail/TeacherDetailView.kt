package dev.lkeeeey.edu.library.presentation.teacher_detail

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
import dev.lkeeeey.edu.core.presentation.components.btn.FilledBtn
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersEvent
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersState
import dev.lkeeeey.edu.main.presentation.profile.main.components.Reference
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import ecucateme.composeapp.generated.resources.ic_subject
import ecucateme.composeapp.generated.resources.ic_tg
import ecucateme.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.painterResource

@Composable
fun TeacherDetailView (
    state: AllTeachersState,
    onEvent: (AllTeachersEvent) -> Unit,
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
                text = state.selectedTeacherModel.name, style = TextStyle(
                    fontSize = 17.sp,
                    fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                    fontWeight = FontWeight(600),
                    color = Theme.colors.blackProfile,
                    letterSpacing = 0.4.sp,
                )
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = state.selectedTeacherModel.bio, style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                    fontWeight = FontWeight(400),
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
                icon = Res.drawable.ic_subject,
                title = "Предмет",
                content = state.selectedTeacherModel.subject,
                isVisible = true
            ) {

            }

            Reference(
                icon = Res.drawable.ic_tg,
                title = "Телеграмм",
                content = state.selectedTeacherModel.username,
                isVisible = true
            ) {

            }

            FilledBtn(
                text = "Выбрать специлиста"
            ) {
                onEvent(AllTeachersEvent.OnSelectTeacher)
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}
