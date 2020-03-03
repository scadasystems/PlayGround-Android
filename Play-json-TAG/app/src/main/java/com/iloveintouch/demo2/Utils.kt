package com.iloveintouch.demo2

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

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
 * Date : 2020/03/03
 * Time : 9:42 AM
 * GitHub : https://github.com/scadasystems
 * E-mail : redsmurf@lulzm.org
 *********************************************************/

private var player: SimpleExoPlayer? = null
private var playWhenReady = true
private var currentWindow = 0
private var playBackPosition: Long = 0

fun initializePlayer(context: Context, exoPlayerView: PlayerView?, media_url: String) {
//    player = ExoPlayerFactory.newSimpleInstance(context)
    player = SimpleExoPlayer.Builder(context).build()
    exoPlayerView?.player = player

    val uri = Uri.parse(media_url)
    val mediaSource = buildMediaSource(context, uri)
    player!!.playWhenReady = playWhenReady
    player!!.seekTo(currentWindow, playBackPosition)
    player!!.prepare(mediaSource, false, false)
    player!!.repeatMode = Player.REPEAT_MODE_ONE
}

fun buildMediaSource(context: Context, uri: Uri?): MediaSource {
    val dataSourceFactory = DefaultDataSourceFactory(context, "PlayBackground")
    return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
}

fun releasePlayer() {
    if (player != null) {
        playWhenReady = player!!.playWhenReady
        playBackPosition = player!!.currentPosition
        currentWindow = player!!.currentWindowIndex
        player!!.release()
        player = null
    }
}
//////// end - Exoplayer Util /////////////

//////// start - Glide ////////
fun glide(context: Context, bitmap: Bitmap?, view: ImageView, width: Int, height: Int) {
    Glide.with(context)
        .load(bitmap)
        .override(width, height)
        .skipMemoryCache(true)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}
//////// end - Glide ////////

//////// start - Hide navigation bar /////////
fun hideNavigationBar(activityReference: Activity): Unit {
    val uiOptions: Int = activityReference.window.decorView.systemUiVisibility
    var newUiOptions = uiOptions
    newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_FULLSCREEN
    newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    activityReference.window.decorView.systemUiVisibility = newUiOptions
}
//////// end - Hide navigation bar /////////