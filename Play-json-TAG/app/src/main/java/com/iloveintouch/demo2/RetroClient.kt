package com.iloveintouch.demo2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*********************************************************
 *   ,--.           ,--.       ,--.   ,--.
 *   |  |   ,--.,--.|  |,-----.|   `.'   |
 *   |  |   |  ||  ||  |`-.  / |  |'.'|  |
 *   |  '--.'  ''  '|  | /  `-.|  |   |  |
 *   `-----' `----' `--'`-----'`--'   `--'
 *
 * Project : Play-json-TAG
 * Created by Android Studio
 * Developer : Lulz_M
 * Date : 2020/03/02
 * Time : 4:23 PM
 * GitHub : https://github.com/scadasystems
 * E-mail : redsmurf@lulzm.org
 *********************************************************/

object RetroClient {
    private val ROOT_URL = "https://raw.githubusercontent.com/scadasystems/PlayGround-Android/"

    private val retrofitInstance: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService: ApiService
        get() = retrofitInstance.create(ApiService::class.java)
}