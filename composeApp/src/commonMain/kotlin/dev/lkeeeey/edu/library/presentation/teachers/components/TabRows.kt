package dev.lkeeeey.edu.library.presentation.teachers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.core.presentation.components.fields.OutlinedText
import dev.lkeeeey.edu.library.presentation.teachers.AllTeachersView
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersEvent
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersState
import org.jetbrains.compose.resources.stringResource

@Composable
fun TabRows (
    state: AllTeachersState,
    onEvent: (AllTeachersEvent) -> Unit
){
    val pagerState = rememberPagerState { 2 }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(state.selectedTabIndex) {
        pagerState.animateScrollToPage(state.selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        onEvent(AllTeachersEvent.OnTabSelected(pagerState.currentPage))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.primaryBackground.copy(.3f))
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        LibrarySearchBar(
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp),
            searchQuery = state.query,
            label = "Что хотите найти?",
            onImeSearch = {
                keyboardController?.hide()
            },
            onSearchQueryChange = {
                onEvent(AllTeachersEvent.OnSubjectUpdate(it))
            }
        )

        Spacer(Modifier.height(24.dp))

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = Theme.colors.backgroundMain,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .widthIn(max = 700.dp)
                        .fillMaxWidth(),
                    backgroundColor = Color.White,
                    indicator = { tabPositions ->

                    }
                ) {
                    Tab(
                        selected = state.selectedTabIndex == 0,
                        onClick = {
                            onEvent(AllTeachersEvent.OnTabSelected(0))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = Color.Red,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = "1st",
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }
                    Tab(
                        selected = state.selectedTabIndex == 1,
                        onClick = {
                            onEvent(AllTeachersEvent.OnTabSelected(1))

                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = Color.Red,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = "2nd",
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { pageIndex ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when(pageIndex) {
                            0 -> {
                                Text("1st screen")
                            }
                            1 -> {
                                AllTeachersView(
                                    state = state,
                                    onEvent = onEvent,
                                    onOpenTeacherDescription = {}
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
