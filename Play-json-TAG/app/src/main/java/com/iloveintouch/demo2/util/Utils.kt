package com.iloveintouch.demo2.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

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

//////// start - Glide ////////
fun glide(context: Context, bitmap: String?, view: ImageView) {
    Glide.with(context)
        .load(bitmap)
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