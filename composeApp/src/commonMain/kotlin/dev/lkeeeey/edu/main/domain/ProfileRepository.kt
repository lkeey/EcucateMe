package dev.lkeeeey.edu.main.domain

import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.main.domain.models.TimeTableModel

interface ProfileRepository {

    suspend fun getTimeTable(): Result<List<TimeTableModel>, DataError.Remote>

}
