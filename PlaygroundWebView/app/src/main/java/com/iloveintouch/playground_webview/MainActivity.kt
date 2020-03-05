package com.iloveintouch.playground_webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled", "WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        webView.setInitialScale(1)
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false

        WebView.setWebContentsDebuggingEnabled(true)

        webView.loadUrl("file:///android_asset/index.html")

    }

    override fun onDestroy() {
        val parent = this.parent
        if (parent is ViewGroup) {
            (parent as ViewGroup).removeView(webView)
        }
        try {
            (parent as ViewGroup).removeAllViews()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        webView.destroy()

        super.onDestroy()
    }
}
