package dev.lkeeeey.edu.library.domain.models

import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.ic_biology
import org.jetbrains.compose.resources.DrawableResource

data class ArticleModel (
    val title: String = "Loading",
    val author : String = "Loading",
    val tags: List<String> = listOf(),
    val subject: String = "Loading",
    val paint: DrawableResource = Res.drawable.ic_biology
)
