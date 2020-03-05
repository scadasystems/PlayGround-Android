package com.iloveintouch.demo2.exam

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.iloveintouch.demo2.ApiService
import com.iloveintouch.demo2.R
import com.iloveintouch.demo2.RetroClient

class TouchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_touch)

        /* Parsing */
        val api: ApiService = RetroClient.apiService
        val call = api.productJSON
    }
}
