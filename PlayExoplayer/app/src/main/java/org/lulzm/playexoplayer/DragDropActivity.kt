package org.lulzm.playexoplayer

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_drag_drop.*

class DragDropActivity : AppCompatActivity(), View.OnTouchListener, View.OnDragListener {
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_drag_drop)

        Logger.addLogAdapter(AndroidLogAdapter())

        tv_dragdrop.setOnTouchListener(this)
        cl_layout.setOnDragListener(this)
    }

    override fun onDrag(view: View?, dragEvent: DragEvent?): Boolean {
        Log.d(TAG, "onDrag: view->$view\\n DragEvent$dragEvent")
        when (dragEvent?.action) {
            DragEvent.ACTION_DRAG_ENDED -> {
                Logger.d("ACTION_DRAG_ENDED")
                return true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                Logger.d("ACTION_DRAG_EXITED")
                return true
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                Logger.d("ACTION_DRAG_ENTERED")
                return true
            }
            DragEvent.ACTION_DRAG_STARTED -> {
                Logger.d("ACTION_DRAG_STARTED")
                return true
            }
            DragEvent.ACTION_DROP -> {
                Log.d(TAG, "ACTION_DRAG_DROP")
                val tvState = dragEvent.localState as View
                Logger.d("onDrag: viewX -> ${dragEvent.x} / viewY -> ${dragEvent.y}")
                Logger.d("onDrag: Owner -> ${tvState.parent}")
                val tvParent = tvState.parent as ViewGroup
                tvParent.removeView(tvState)
                val container = view as ConstraintLayout
                container.addView(tvState)
                tvParent.removeView(tvState)
                tvState.x = dragEvent.x
                tvState.y = dragEvent.y
                view.addView(tvState)
                view.visibility = View.VISIBLE
                return true
            }
            DragEvent.ACTION_DRAG_LOCATION -> {
                Logger.d("ACTION_DRAG_LOCATION")
                return true
            }
            else -> return false
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        Log.d(TAG, "onTouch: view->view$view\\n MotionEvent$motionEvent")
        return if (motionEvent?.action == MotionEvent.ACTION_DOWN) {
            val dragShadowBuilder = View.DragShadowBuilder(view)
            view?.startDragAndDrop(null, dragShadowBuilder, view, 0)
            true
        } else {
            false
        }
    }
}
