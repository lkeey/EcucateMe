package dev.lkeeeey.edu.library.presentation.teachers

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.core.presentation.components.fields.OutlinedText
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersEvent
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersState
import ecucateme.composeapp.generated.resources.Bold
import ecucateme.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun AllTeachersView (
    state: AllTeachersState,
    onEvent: (AllTeachersEvent) -> Unit,
    onOpenTeacherDescription: () -> Unit
) {

    Column (
        modifier = Modifier
            .animateContentSize()
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedText(
            previousData = state.query,
            label = "Введите предмет",
        ) {
            onEvent(AllTeachersEvent.OnSubjectUpdate(it))
        }

        Spacer(Modifier.height(24.dp))

        Column (
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            state.teachers.forEach { teacher->
                Text(
                    text = "${teacher.name} - ${teacher.subject}",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(Res.font.Bold)),
                        fontWeight = FontWeight(400),
                        color = Theme.colors.blackProfile,
                        letterSpacing = 0.3.sp,
                    )
                )
            }
        }
    }
}
