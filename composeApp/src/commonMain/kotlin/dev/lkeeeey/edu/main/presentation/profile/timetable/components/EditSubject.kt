package dev.lkeeeey.edu.main.presentation.profile.timetable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@Composable
fun EditSubject (
    index: Int,
    subject: String,
    onSubjectChanged: (Int, String) -> Unit
) {

    var textValue by remember {
        mutableStateOf(subject)
    }

    Row (
       modifier = Modifier
           .fillMaxWidth()
           .padding(vertical = 8.dp, horizontal = 24.dp)
           .background(color = Theme.colors.primaryBackground.copy(1f))
    ) {
        Text(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(White),
            text = index.toString(),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(Res.font.Thin)),
                fontWeight = FontWeight(500),
                color = Color.Black,
                letterSpacing = 0.32.sp,
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Row {
                    Text(
                        text = "Предмет - $index",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(Res.font.Thin)),
                            fontWeight = FontWeight(400),
                            color = Theme.colors.secondaryBorder,
                            letterSpacing = 0.3.sp,
                        )
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Black,
                focusedLabelColor = Black,
                cursorColor = Black,
                backgroundColor = White,
                errorBorderColor = Theme.colors.errorColor,
            ),
            singleLine = true,
            maxLines = 1,
            value = textValue,
            onValueChange = {
                textValue = it
                onSubjectChanged(index, it)
            },
            shape = RoundedCornerShape(16.dp),
        )
    }

}
