package dev.lkeeeey.edu.library.presentation.tasks

import androidx.compose.ui.util.fastFilter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.keys.Keys.SELECTED_BLOCK
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.library.domain.LibraryRepository
import dev.lkeeeey.edu.library.domain.models.AnswerModel
import dev.lkeeeey.edu.library.domain.models.SolvingTaskModel
import dev.lkeeeey.edu.main.domain.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FullTaskViewModel (
    private val profileRepository: ProfileRepository,
    private val libraryRepository: LibraryRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FullTaskState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()

    fun onEvent(
        event: FullTaskEvent
    ) {
        when (event) {
            is FullTaskEvent.OnAnswer -> {
                println("send request - ${state.value.solvingTasks.fastFilter { it.id == event.id }}")
            }
            FullTaskEvent.OnLoadFullModel -> {
                loadFullBlock()
            }

            is FullTaskEvent.OnUpdateAnswer -> {
                _state.update {
                    it.copy(
                        solvingTasks = state.value.solvingTasks.map { t->
                            if (t.id == event.id) {
                                t.copy(answerModel = event.answer)
                            } else{
                                t
                            }
                        }
                    )
                }
            }
        }
    }

    private fun loadFullBlock() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            profileRepository.refreshToken()
                .onSuccess { loginDto ->
                    authRepository.updateAccessToken(loginDto.accessToken)

                    libraryRepository
                        .getFullTaskBlock(
                            id = settings.getString(SELECTED_BLOCK, "")
                        )
                        .onSuccess { b ->

                            _state.update {
                                it.copy(
                                    block = b,
                                    solvingTasks = b.tasks.map { t->
                                        SolvingTaskModel(
                                            id = t.id,
                                            content = t.content,
                                            type = TaskType.NOT_SOLVED,
                                            answerModel = AnswerModel(
                                                answer = ""
                                            )
                                        )
                                    },
                                    isLoading = false
                                )
                            }
                        }
                        .onError { e ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    error = e.name
                                )
                            }
                        }
                }
                .onError { e ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = e.name
                        )
                    }
                }

        }
    }



}