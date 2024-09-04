package com.br.devmendesc.playcast.ui.components

import android.content.Context
import android.util.Log
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.STATE_READY
import java.util.concurrent.TimeUnit

class AudioPlayer(private val context: Context) {
    private var _player: ExoPlayer? = ExoPlayer.Builder(context).build()

    val player: ExoPlayer?
        get() = _player

    fun initializePlayer(audiosUrl: List<String>, index: Int) {
        player?.apply {
            setMediaItem(MediaItem.fromUri(audiosUrl[index]))
            prepare()
        }
    }

    fun play() {
        player?.play()
    }

    fun pause() {
        player?.pause()
    }

    fun next(audiosUrl: List<String>, index: Int) {
        if (index < audiosUrl.size - 1) {
            setMediaItem(MediaItem.fromUri(audiosUrl[index + 1]))
            prepare()
            player?.playWhenReady = true
        }
    }

    fun previous(audiosUrl: List<String>, index: Int) {
        if (index > 0) {
            setMediaItem(MediaItem.fromUri(audiosUrl[index - 1]))
            prepare()
            player?.playWhenReady = true
        }
    }

    fun seekForward(seconds: Long) {
        player?.seekTo((player?.currentPosition ?: 0) + seconds * 1000)
    }

    fun seekBackward(seconds: Long) {
        player?.seekTo((player?.currentPosition ?: 0) - seconds * 1000)
    }

    fun getCurrentPosition(): Long {
        return if (player?.playbackState == STATE_READY) {
            player?.currentPosition ?: 0
        } else {
            0
        }
    }

    fun getDuration(): Long {
        return player?.duration ?: 0
    }

    fun seekTo(position: Long) {
        player?.seekTo(position)
    }

    fun release() {
        player?.release()
    }

    private fun setMediaItem(mediaItem: MediaItem) {
        player?.setMediaItem(mediaItem)
    }

    private fun prepare() {
        player?.prepare()
    }

    fun getTimeRemaining(): String {
        val duration = player?.duration ?: 0
        val currentPosition = player?.currentPosition ?: 0
        val remainingTime = duration - currentPosition
        return formatTime(remainingTime)
    }

    fun getFormattedCurrentPosition(): String {
        val currentPosition = player?.currentPosition ?: 0
        return formatTime(currentPosition)
    }

    private fun formatTime(timeInMillis: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(timeInMillis)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeInMillis) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timeInMillis) % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

}
