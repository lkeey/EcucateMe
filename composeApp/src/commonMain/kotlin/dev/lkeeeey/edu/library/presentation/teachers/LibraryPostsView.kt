package dev.lkeeeey.edu.library.presentation.teachers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.library.presentation.teachers.components.LibraryBox
import dev.lkeeeey.edu.library.presentation.teachers.components.LibraryItem
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersAction
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersEvent
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersState
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.ic_biology

@Composable
fun LibraryPostsView (
    state: AllTeachersState,
    onEvent: (AllTeachersEvent) -> Unit,
    onOpen: (AllTeachersAction) -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        LibraryBox(
            title = "То, что искали",
            action = "подробнее",
            onActionClicked = {
            }
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(state.searchedArticles) { article ->
                    LibraryItem(
                        subject = article.subject,
                        title = article.title,
                        paint = Res.drawable.ic_biology,
                        onClick = {
                            onEvent(AllTeachersEvent.OnOpenBlock(id = article.id))
                            onOpen(AllTeachersAction.OnOpenFullBlock)
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

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
}
