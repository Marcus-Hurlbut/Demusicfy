package com.marcushurlbut.demusicfy.domain.manager

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.marcushurlbut.demusicfy.MainActivity.Companion.context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.measureTime


actual class SoundManager actual constructor() {
    actual val sounds = listOf("tick", "low_hit", "thump")

    actual var sound = "tick"
    private val player = context?.let { ExoPlayer.Builder(it).build() }
    private var soundUri: Uri = Uri.parse("android.resource://${context?.packageName}/raw/$sound")
    private var loopJob : Job = Job()

    init {
        player?.setMediaItem(MediaItem.fromUri(soundUri))
    }

    actual suspend fun switchSound(sound: String) {
        stop()
        this.sound = sound
        soundUri = Uri.parse("android.resource://${context?.packageName}/raw/$sound")
    }

    actual suspend fun play(bpm : Int) {
        if (!loopJob.isActive) {
            player?.setMediaItem(MediaItem.fromUri(soundUri))
            player?.prepare()
            loopJob = CoroutineScope(Dispatchers.Main).launch {
                loop(bpm)
            }
        }
    }

    actual fun stop() {
        loopJob.cancel()
        player?.stop()
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
            player?.seekToDefaultPosition()
            delay(timeDelay(bpm))
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