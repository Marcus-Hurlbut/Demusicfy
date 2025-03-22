package com.marcushurlbut.demusicfy.domain.manager

actual class SoundManager actual constructor() {
    actual suspend fun play(bpm: Int) {
    }

    actual fun stop() {
    }

    actual suspend fun adjustTiming(bpm: Int) {
    }

}