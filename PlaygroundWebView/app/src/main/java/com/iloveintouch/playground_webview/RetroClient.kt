package com.iloveintouch.playground_webview

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*********************************************************
 *   ,--.           ,--.       ,--.   ,--.
 *   |  |   ,--.,--.|  |,-----.|   `.'   |
 *   |  |   |  ||  ||  |`-.  / |  |'.'|  |
 *   |  '--.'  ''  '|  | /  `-.|  |   |  |
 *   `-----' `----' `--'`-----'`--'   `--'
 *
 * Project : Playground-WebView
 * Created by Android Studio
 * Developer : Lulz_M
 * Date : 2020/03/04
 * Time : 3:56 PM
 * GitHub : https://github.com/scadasystems
 * E-mail : redsmurf@lulzm.org
 *********************************************************/

object RetroClient {
    private val ROOT_URL = "https://storage.googleapis.com/lulzm/assets/"

    private val retrofitInstance: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService: ApiService
        get() = retrofitInstance.create(ApiService::class.java)
}