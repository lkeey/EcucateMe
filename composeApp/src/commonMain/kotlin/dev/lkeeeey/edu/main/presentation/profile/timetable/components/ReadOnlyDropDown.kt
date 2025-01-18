
package dev.lkeeeey.edu.main.presentation.profile.timetable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.core.presentation.components.text.ShowError
import dev.lkeeeey.edu.main.domain.models.SubjectPresModel
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReadOnlyDropDown (
    options: List<SubjectPresModel>,
    previousData: String,
    label: String,
    isError: Boolean = false,
    onTextChanged: (SubjectPresModel) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var textValue by remember {
        mutableStateOf(previousData)
    }

    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {

        // The `menuAnchor` modifier must be passed to the text field for correctness.

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
//                .menuAnchor(),
            label = {
                Row {
                    Text(
                        text = label,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                            fontWeight = FontWeight(400),
                            color = Color(0x80222222),
                            letterSpacing = 0.3.sp,
                        )
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Theme.colors.primaryBackground.copy(1f),
                focusedLabelColor = Theme.colors.primaryBackground.copy(1f),
                cursorColor = Color(0xFF3579F8),
                backgroundColor = White,
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            maxLines = 1,
            value = textValue,
            onValueChange = {
                // user can't write anything
            },
            readOnly = true,
            isError = isError
        )

        ExposedDropdownMenu(
            modifier = Modifier
                .background(White)
                .heightIn(
                    max = 150.dp
                ),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    content = {
                        Text(selectionOption.name)
                    },
                    onClick = {
                        textValue = selectionOption.name
                        expanded = false
                        onTextChanged(selectionOption)
                    },
//                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
    if(isError) {
        ShowError(text = "error")
    }
}
