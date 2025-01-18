package dev.lkeeeey.edu.main.presentation.calendar.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.core.presentation.components.text.ShowError
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@Composable
fun OutlinedText (
    previousData: String,
    label: String,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    isSuccess: Boolean = false,
    isEmail: Boolean = false,
    errorText: String = "",
    onTextChanged: (String) -> Unit,
) {

    var textValue by remember {
        mutableStateOf(previousData)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Row {
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(Res.font.Thin)),
                        fontWeight = FontWeight(400),
                        color = Theme.colors.secondaryBorder,
                        letterSpacing = 0.3.sp,
                    )
                )

                Text(
                    text = "*",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(Res.font.Thin)),
                        fontWeight = FontWeight(500),
                        color = if(isError) Theme.colors.errorColor else Theme.colors.editPlaceholder,
                        letterSpacing = 0.3.sp
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
        keyboardOptions = KeyboardOptions(
            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
            keyboardType = if (isEmail) KeyboardType.Email else KeyboardType.Text
        ),
        singleLine = true,
        maxLines = 1,
        value = textValue,
        onValueChange = {
            textValue = it
            onTextChanged(it)
        },
        shape = RoundedCornerShape(16.dp),
        isError = isError,
        enabled = isEnabled
    )

    if(isError) {
        ShowError(text = errorText)
    }
}
