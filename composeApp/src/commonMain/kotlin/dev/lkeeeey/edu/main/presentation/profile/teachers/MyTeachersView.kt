package dev.lkeeeey.edu.main.presentation.profile.teachers

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.library.domain.models.TeacherModel
import dev.lkeeeey.edu.library.presentation.teachers.components.TeacherCard
import dev.lkeeeey.edu.main.presentation.calendar.components.ImageWithText
import dev.lkeeeey.edu.main.presentation.profile.main.components.BackBtn
import dev.lkeeeey.edu.main.presentation.profile.teachers.viewmodel.MyTeachersAction
import dev.lkeeeey.edu.main.presentation.profile.teachers.viewmodel.MyTeachersEvent
import dev.lkeeeey.edu.main.presentation.profile.teachers.viewmodel.MyTeachersState
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import ecucateme.composeapp.generated.resources.ic_calendar_no_plans
import org.jetbrains.compose.resources.Font

@Composable
fun MyTeachersView (
    state: MyTeachersState,
    onEvent: (MyTeachersEvent) -> Unit,
    onOpenScreen: (MyTeachersAction) -> Unit
) {
    Column (
        modifier = Modifier
            .animateContentSize()
            .fillMaxSize()
            .systemBarsPadding()
    ) {

        Text(
            text = "",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(Res.font.Thin)),
                fontWeight = FontWeight(600),
                color = Theme.colors.blackProfile,
                textAlign = TextAlign.Center
            )
        )

        BackBtn(
            text = "Мои учителя",
            onClick = {
                onOpenScreen(MyTeachersAction.OnOpenBack)
            },
            containerColor = White
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column (
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            if (state.myTeachers.isEmpty()) {
                ImageWithText (
                    drawable = Res.drawable.ic_calendar_no_plans,
                    text = "Учителя еще не выбраны"
                )
            } else {
                state.myTeachers.forEach { teacher->
                    TeacherCard(
                        teacher = TeacherModel(teacher.username, teacher.name, "")
                    ) {
                        onEvent(MyTeachersEvent.OnOpenTeacherDescription(username = teacher.username))
                        onOpenScreen(MyTeachersAction.OnOpenTeacherDescription)
                    }
                }
            }
        }
    }
}
