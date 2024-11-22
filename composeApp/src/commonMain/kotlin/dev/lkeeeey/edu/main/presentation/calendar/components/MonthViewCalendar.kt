package dev.lkeeeey.edu.main.presentation.calendar.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun MonthViewCalendar(
//    loadedDates: Array<List<LocalDate>>,
//    selectedDate: LocalDate,
//    currentMonth: YearMonth,
//    loadDatesForMonth: (YearMonth) -> Unit,
//    onDayClick: (LocalDate) -> Unit,
//    state: PlanState
) {

//    val itemWidth = LocalConfiguration.current.screenWidthDp / 7

//    CalendarPager(
//        loadedDates = loadedDates,
//        loadNextDates = {
//            loadDatesForMonth(currentMonth)
//        },
//        loadPrevDates = {
//            loadDatesForMonth(currentMonth.minusMonths(2))
//        }
//    ) { currentPage ->
//
//        FlowRow(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            loadedDates[currentPage].forEachIndexed { index, date ->
//
//                Box(
//                    Modifier
////                        .width(itemWidth.dp)
//                        .fillMaxWidth(0.14f)
//                        .padding(5.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    DayView(
//                        date,
//                        isSelected = selectedDate == date,
//                        onDayClick = {
//                            onDayClick(date)
//                        },
//                        // if index less then 7,
//                        // it means that we should show weekDay
//                        weekDayLabel = index < 7,
//                        modifier = Modifier
//                            .dayViewModifier(date, currentMonth, monthView = true),
//                        state = state
//                    )
//                }
//            }
//        }
//    }
}
