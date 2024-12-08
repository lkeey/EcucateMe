package dev.lkeeeey.edu.main.presentation.calendar

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarEvent
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CalendarScreen (
    viewModel: CalendarViewModel = koinViewModel(),
    navController: NavController
) {
    CalendarView(

    )

    Button(
        onClick = {
            viewModel.onEvent(CalendarEvent.OnBtnClick)
        }
    ) {
        Text(text = "click to know")
    }
}
