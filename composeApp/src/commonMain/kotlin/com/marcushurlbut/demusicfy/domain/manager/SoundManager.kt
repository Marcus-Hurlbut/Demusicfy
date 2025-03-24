package com.marcushurlbut.demusicfy.domain.manager

expect class SoundManager () {
    val sounds: List<String>
    var sound: String
    suspend fun play(bpm : Int)
    suspend fun adjustTiming(bpm: Int)
    fun stop()
    suspend fun switchSound(sound: String)
}