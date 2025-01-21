package dev.lkeeeey.edu.main.presentation.profile.teachers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.keys.Keys
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.library.domain.LibraryRepository
import dev.lkeeeey.edu.main.domain.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyTeachersViewModel (
    private val profileRepository: ProfileRepository,
    private val libraryRepository: LibraryRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MyTeachersState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()

    fun onEvent(
        event: MyTeachersEvent
    ) {
        when (event) {
            is MyTeachersEvent.OnOpenTeacherDescription -> {
                settings.putString(
                    key = Keys.SELECTED_TEACHER,
                    value = event.username
                )
            }

            MyTeachersEvent.OnSearchSelectedTeachers -> {
                findMyTeachers()
            }
        }
    }

    private fun findMyTeachers() {
        viewModelScope.launch {

            profileRepository.refreshToken()
                .onSuccess {
                    authRepository.updateAccessToken(it.accessToken)

                    libraryRepository
                        .getSelectedTeachers()
                        .onSuccess { t ->
                            _state.update {
                                it.copy(
                                    myTeachers = t
                                )
                            }
                        }
                }

        }
    }
}
