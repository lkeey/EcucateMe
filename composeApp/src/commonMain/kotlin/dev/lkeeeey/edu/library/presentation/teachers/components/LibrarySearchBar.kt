package dev.lkeeeey.edu.library.presentation.teachers.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@Composable
fun LibrarySearchBar(
    label: String,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        shape = RoundedCornerShape(100),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Theme.colors.primaryBackground.copy(1f),
            focusedBorderColor = Theme.colors.primaryBackground.copy(1f)
        ),
        placeholder = {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                    fontWeight = FontWeight(500),
                    color = Theme.colors.editPlaceholder,
                    letterSpacing = 0.3.sp
                )
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Theme.colors.editPlaceholder
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                onImeSearch()
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        trailingIcon = {
            AnimatedVisibility(
                visible = searchQuery.isNotBlank()
            ) {
                IconButton(
                    onClick = {
                        onSearchQueryChange("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "delete",
                        tint = Theme.colors.editPlaceholder
                    )
                }
            }
        },
        modifier = modifier
            .background(
                shape = RoundedCornerShape(100),
                color = Color.White
            )
            .minimumInteractiveComponentSize()
    )
}
