package dev.lkeeeey.edu.library.presentation.teachers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.library.presentation.teachers.components.LibraryBox
import dev.lkeeeey.edu.library.presentation.teachers.components.LibraryItem
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersState

@Composable
fun LibraryPostsView (
    state: AllTeachersState,
) {

    LibraryBox(
        title = "Для Вас",
        action = "подробнее",
        onActionClicked = {
        }
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(state.articles) { article ->
                LibraryItem(
                    subject = article.subject,
                    title = article.title,
                    paint = article.paint,
                    onClick = {

                    }
                )
            }
        }
    }
}
