package dev.lkeeeey.edu.library.presentation.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.core.presentation.components.btn.FilledBtn
import dev.lkeeeey.edu.core.presentation.components.fields.OutlinedText
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin

@Composable
fun TaskModelView (
    num: Int,
    content: String,
    type: TaskType,
    previousData: String,
    onUpdateAnswer: (String) -> Unit,
    onClick: () -> Unit
) {
    var textValue by remember {
        mutableStateOf(previousData)
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Задание ${num + 1}", style = TextStyle(
                fontSize = 17.sp,
                fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                fontWeight = FontWeight.Bold,
                color = Theme.colors.blackProfile,
                letterSpacing = 0.4.sp,
            )
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = content, style = TextStyle(
                fontSize = 17.sp,
                fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                fontWeight = FontWeight(500),
                color = Theme.colors.blackProfile,
                letterSpacing = 0.4.sp,
            )
        )

        Spacer(Modifier.height(8.dp))

        OutlinedText(
            previousData = previousData,
            label = "Введите ответ - $type",
            isEnabled = type != TaskType.SOLVED_RIGHT,
            isError = type == TaskType.SOLVED_BAD,
            isSuccess = type == TaskType.SOLVED_RIGHT,
            onTextChanged = {
                textValue = it
                onUpdateAnswer(it)
            }
        )

        Spacer(Modifier.height(8.dp))

        FilledBtn(
            text = "Ответить",
            isEnabled = textValue.isNotEmpty() && type != TaskType.SOLVED_RIGHT,
            backgroundColor = Theme.colors.primaryBackground.copy(alpha = 1f)
        ) {
            onClick()
        }

        Spacer(Modifier.height(16.dp))
    }

}
