package com.marcushurlbut.demusicfy.domain.manager

expect class SoundManager () {
    suspend fun play(bpm : Int)
    suspend fun adjustTiming(bpm: Int)
    fun stop()
}