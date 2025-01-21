package dev.lkeeeey.edu.library.presentation.teachers.viewmodel

import dev.lkeeeey.edu.library.domain.models.ArticleModel
import dev.lkeeeey.edu.library.domain.models.DescriptionTeacherModel
import dev.lkeeeey.edu.library.domain.models.TeacherModel
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.ic_biology
import ecucateme.composeapp.generated.resources.ic_english

data class AllTeachersState(
    val teachers: List<TeacherModel> = emptyList(),
    val articles: List<ArticleModel> = listOf(
        ArticleModel(
            title = "Алкины",
            author = "Алексей",
            listOf("ЕГЭ", "олимпиады"),
            subject = "Химия",
            paint = Res.drawable.ic_biology
        ),
        ArticleModel(
            title = "Модальные глаголы",
            author = "Алексей",
            listOf("ЕГЭ", "олимпиады"),
            subject = "Английский язык",
            paint = Res.drawable.ic_english
        )
    ),
    val selectedTeacherModel: DescriptionTeacherModel = DescriptionTeacherModel("", "", "", ""),
    val selectedUsername: String = "",
//    val query: String = "",
    val subject: String = "",
    val selectedTabIndex: Int = 0,
    val isTeacherSelected: Boolean = false,
)