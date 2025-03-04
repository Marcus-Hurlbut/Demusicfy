package com.marcushurlbut.demusicfy

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform