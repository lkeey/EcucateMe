package dev.lkeeeey.edu.library.presentation.tasks

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.library.domain.models.AnswerModel
import dev.lkeeeey.edu.main.presentation.profile.main.components.BackBtn
import ecucateme.composeapp.generated.resources.Bold
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import ecucateme.composeapp.generated.resources.preview
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FullTasksView (
    state: FullTaskState,
    onEvent: (FullTaskEvent) -> Unit,
    onOpenBack: () -> Unit
) {

    Column (
          modifier = Modifier
              .fillMaxSize()
              .statusBarsPadding()
              .blur(if (state.isLoading) 4.dp else 0.dp),
    ) {
        BackBtn(
            text = "Тренажер",
            onClick = {
                onOpenBack()
            },
            containerColor = Theme.colors.backgroundMain
        )

        Column (
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(Res.drawable.preview),
                contentDescription = "preview post",
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(8.dp))

            Column (
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = state.block.title, style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Bold)),
                        fontWeight = FontWeight(600),
                        color = Theme.colors.blackProfile,
                        letterSpacing = 0.4.sp,
                    )
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = state.block.author.name, style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                        fontWeight = FontWeight(600),
                        color = Theme.colors.blackProfile,
                        letterSpacing = 0.4.sp,
                    )
                )

                Spacer(Modifier.height(8.dp))

                FilterChip(
                    onClick = { },
                    content = {
                        Text(
                            text = state.block.subject,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                                fontWeight = FontWeight(500),
                                letterSpacing = 0.24.sp,
                            )
                        )
                    },
                    selected = true,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Theme.colors.primaryBackground.copy(1f)
                    ),
                    colors = ChipDefaults.filterChipColors(
                        backgroundColor = White,
                        contentColor = Theme.colors.primaryBackground.copy(1f),

                        selectedBackgroundColor = Theme.colors.primaryBackground.copy(1f),
                        selectedContentColor = White,
                    ),
                )

                Spacer(Modifier.height(8.dp))

                state.solvingTasks.forEachIndexed { index, solvingTaskModel ->
                    TaskModelView(
                        num = index,
                        content = solvingTaskModel.content,
                        type = solvingTaskModel.type,
                        previousData = solvingTaskModel.answerModel.answer,
                        onUpdateAnswer = {
                            onEvent(FullTaskEvent.OnUpdateAnswer(solvingTaskModel.id, AnswerModel(it)))
                        }
                    ) {
                        onEvent(FullTaskEvent.OnAnswer(solvingTaskModel.id))
                    }
                }
            }
        }
    }

}