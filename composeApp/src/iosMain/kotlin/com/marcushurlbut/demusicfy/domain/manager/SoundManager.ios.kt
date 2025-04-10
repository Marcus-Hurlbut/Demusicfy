package com.marcushurlbut.demusicfy.domain.manager
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import platform.AVFAudio.AVAudioPlayer
import platform.AVFoundation.*
import platform.CoreMedia.CMTimeMake
import platform.Foundation.NSBundle
import platform.Foundation.NSURL
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.measureTime


actual class SoundManager actual constructor() {
    actual val sounds = listOf("tick", "low_hit", "thump")
    actual var sound: String = "tick"
    private var player: AVAudioPlayer? = null
    private var loopJob : Job = Job()
    private var nsUrl = NSBundle.mainBundle.URLForResource(sound, "wav")

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun play(bpm: Int) {
        player = nsUrl?.let { AVAudioPlayer(it, null) }
        player?.prepareToPlay()
        loopJob = CoroutineScope(Dispatchers.Main).launch {
            loop(bpm)
        }
    }

    actual suspend fun switchSound(sound: String) {
        stop()
        this.sound = sound
        nsUrl = NSBundle.mainBundle.URLForResource(sound, "wav")
    }

    actual fun stop() {
        loopJob.cancel()
        player?.pause()
    }

    actual suspend fun adjustTiming(bpm: Int) {
        if (loopJob.isActive) {
            stop()
            play(bpm)
        }
    }

    private suspend fun loop(bpm: Int) {
        while (loopJob.isActive) {
            player?.play()
            delay(timeDelay(bpm))
            player?.pause()
            player?.currentTime = 0.0
        }
    }

    private fun timeDelay(bpm: Int) : Duration {
        var millisecondsPerBeat : Long
        val lag = measureTime {
            millisecondsPerBeat = ((60000 / bpm).toLong())
        }.inWholeMilliseconds

        return (millisecondsPerBeat - lag).milliseconds
    }

}