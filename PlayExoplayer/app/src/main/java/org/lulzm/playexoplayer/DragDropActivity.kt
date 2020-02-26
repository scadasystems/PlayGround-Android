package org.lulzm.playexoplayer

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_drag_drop.*


class DragDropActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_drag_drop)
        Logger.addLogAdapter(AndroidLogAdapter())

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val listener = View.OnTouchListener(function = { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                view.x = motionEvent.rawX - view.width / 2
                view.y = motionEvent.rawY - view.height / 2
            }
            true
        })

        price_tag.setOnTouchListener(listener)

    }
}
