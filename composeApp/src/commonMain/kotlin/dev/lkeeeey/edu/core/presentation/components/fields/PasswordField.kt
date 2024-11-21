package dev.lkeeeey.edu.core.presentation.components.fields

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.core.presentation.components.text.ShowError
import ecucateme.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun PasswordField(
    previousData: String,
    label: String,
    isError: Boolean = false,
    errorText: String = "",
    onTextChanged: (String) -> Unit,
) {

    var textValue by remember {
        mutableStateOf(previousData)
    }

    var isVisible by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = textValue,
        onValueChange = {
            if (textValue.length < 256) {
                textValue = it
                onTextChanged(textValue)
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Theme.colors.primaryBackground,
            focusedLabelColor = Theme.colors.primaryBackground,
            cursorColor = Theme.colors.primaryBackground,
            backgroundColor = Theme.colors.secondaryBackground,
            errorBorderColor = Theme.colors.errorColor
        ),
        label = {
            Row {
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.regular)),
                        fontWeight = FontWeight(400),
                        color = if (isError) Theme.colors.errorColor else Theme.colors.editPlaceholder,
                        letterSpacing = 0.3.sp,
                    )
                )

                Text(
                    text = "*",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        fontWeight = FontWeight(500),
                        color = Theme.colors.primaryBackground,
                        letterSpacing = 0.3.sp,
                    )
                )
            }
        },
        shape = RoundedCornerShape(16.dp),
        isError = isError,
        singleLine = true,
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {

            val description = if (isVisible) "Hide password" else "Show password"

            IconButton(
                onClick = {
                    isVisible = !isVisible
                }
            ) {
                Icon(
                    painter = painterResource(id = if (isVisible) Res.drawable.ic_authentication_opened_eye else Res.drawable.ic_authentication_closed_eye),
                    contentDescription = description,
                    tint = if (isError) Theme.colors.errorColor else Theme.colors.blackProfile,
                )
            }
        }
    )

    if(isError) {
        ShowError(text = errorText)
    }
}
