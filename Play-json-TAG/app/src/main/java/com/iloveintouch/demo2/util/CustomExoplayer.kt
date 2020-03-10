package com.iloveintouch.demo2.util

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

/*********************************************************
 *   ,--.           ,--.       ,--.   ,--.
 *   |  |   ,--.,--.|  |,-----.|   `.'   |
 *   |  |   |  ||  ||  |`-.  / |  |'.'|  |
 *   |  '--.'  ''  '|  | /  `-.|  |   |  |
 *   `-----' `----' `--'`-----'`--'   `--'
 *
 * Project : PlayJsonTag
 * Created by Android Studio
 * Developer : Lulz_M
 * Date : 2020/03/10
 * Time : 4:03 PM
 * GitHub : https://github.com/scadasystems
 * E-mail : redsmurf@lulzm.org
 *********************************************************/

object CustomExoplayer {
    private lateinit var player: SimpleExoPlayer
    private var playWhenReady = true

    fun initializePlayer(context: Context, exoPlayerView: PlayerView, media_url: String) {
        player = SimpleExoPlayer.Builder(context).build()
        exoPlayerView.player = player

        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(context, Util.getUserAgent(context, "PlayJsonTag"))

        val uri = Uri.parse(media_url)
        val videoSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
        player.playWhenReady = playWhenReady
        player.prepare(videoSource)
        player.repeatMode = Player.REPEAT_MODE_ONE
    }

    fun releasePlayer() {
        player.release()
        player.stop()
    }
}