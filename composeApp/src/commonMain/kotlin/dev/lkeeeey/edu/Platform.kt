package dev.lkeeeey.edu

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform