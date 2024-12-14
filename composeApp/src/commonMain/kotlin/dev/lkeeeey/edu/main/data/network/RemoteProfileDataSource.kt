package dev.lkeeeey.edu.main.data.network

import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.main.domain.models.TimeTableModel

interface RemoteProfileDataSource {
    suspend fun getTimeTable(
        access: String
    ): Result<List<TimeTableModel>, DataError.Remote>
}
