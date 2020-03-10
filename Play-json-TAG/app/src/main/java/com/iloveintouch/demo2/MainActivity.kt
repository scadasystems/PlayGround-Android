package com.iloveintouch.demo2

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.iloveintouch.demo2.util.CustomBarcode
import com.iloveintouch.demo2.util.CustomExoplayer
import com.iloveintouch.demo2.util.CustomExoplayer.initializePlayer
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var player: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

//        val listener = View.OnTouchListener(function = { view, motionEvent ->
//            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
//                view.x = motionEvent.rawX - view.width / 2
////                view.y = motionEvent.rawY - view.height / 2
//            }
//            true
//        })
//        price_tag1.setOnTouchListener(listener)
//        price_tag2.setOnTouchListener(listener)
//        price_tag3.setOnTouchListener(listener)
//        price_tag4.setOnTouchListener(listener)

        // 종료
        btn_exit.setOnClickListener {
            cl_finish.visibility = View.VISIBLE

            val timer = object : CountDownTimer(4000, 1000) {
                override fun onFinish() {
                    finish()
                }

                override fun onTick(millisUntilFinished: Long) {
                    tv_finish_time.text = (millisUntilFinished / 1000).toString()
                }
            }.start()
        }

        /* Parsing */
        val api: ApiService = RetroClient.apiService
        val call = api.productJSON

        call.clone().enqueue(object : retrofit2.Callback<Products> {
            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful) {
                    val productList = response.body()!!.products
                    // json list 만큼
                    for (data in productList!!.indices) {
                        val tv_field =
                            resources.getIdentifier("tv_field${data + 1}", "id", packageName)
                        val tv_title =
                            resources.getIdentifier("tv_title${data + 1}", "id", packageName)
                        val tv_origin =
                            resources.getIdentifier("tv_origin${data + 1}", "id", packageName)
                        val tv_company =
                            resources.getIdentifier("tv_company${data + 1}", "id", packageName)
                        val tv_price =
                            resources.getIdentifier("tv_price${data + 1}", "id", packageName)
                        val tv_salePrice =
                            resources.getIdentifier("tv_salePrice${data + 1}", "id", packageName)
                        val iv_barCode =
                            resources.getIdentifier("iv_barCode${data + 1}", "id", packageName)
                        val tv_barCode =
                            resources.getIdentifier("tv_barCode${data + 1}", "id", packageName)
                        val tv_sale =
                            resources.getIdentifier("tv_sale${data + 1}", "id", packageName)
                        // line
                        val data_field1 = findViewById<TextView>(tv_field)
                        val data_title = findViewById<TextView>(tv_title)
                        val data_origin = findViewById<TextView>(tv_origin)
                        val data_company = findViewById<TextView>(tv_company)
                        val data_price = findViewById<TextView>(tv_price)
                        val data_salePrice = findViewById<TextView>(tv_salePrice)
                        val data_saleText = findViewById<TextView>(tv_sale)
                        val data_barCodeImage = findViewById<ImageView>(iv_barCode)
                        val data_barCode = findViewById<TextView>(tv_barCode)

                        // todo xml 꾸미기 - 잘 모르겠음...

                        // 텍스트 넣기
                        data_field1.text = productList[data].field1
                        data_title.text = productList[data].title
                        data_origin.text = productList[data].origin
                        data_company.text = productList[data].company

                        // 할인가와 기본가가 같으면
                        if ((productList[data].salePrice.equals(productList[data].price)) or (productList[data].salePrice.isNullOrEmpty())
                            or (productList[data].salePrice.equals("null")) or (productList[data].salePrice!!.equals(
                                "0"
                            ))
                        ) {
                            data_saleText.visibility = View.INVISIBLE
                            data_salePrice.text = "₩ ${productList[data].price}"
                        } else {
                            data_price.text = productList[data].price
                            data_price.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                            data_salePrice.text = "₩ ${productList[data].salePrice}"
                        }

                        data_barCode.text = productList[data].articleId

                        // 바코드
                        var bitmap: Bitmap?

                        try {
                            bitmap = CustomBarcode.encodeAsBitmap(
                                productList[data].articleId,
                                BarcodeFormat.CODE_128,
                                1500,
                                330
                            )
                            data_barCodeImage.setImageBitmap(bitmap)
                            // 바코드 이미지 넣기
                        } catch (e: WriterException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        })
    }

    private fun exo_play_start() {
        val asset_first_route = "asset:///first_video.mp4"
        val asset_second_route = "asset:///second_video.mp4"
        val asset_third_route = "asset:///third_video.mp4"
        val asset_fourth_route = "asset:///fourth_video.mp4"

        initializePlayer(applicationContext, exo_first_view, asset_first_route)
        initializePlayer(applicationContext, exo_second_view, asset_second_route)
        initializePlayer(applicationContext, exo_third_view, asset_third_route)
        initializePlayer(applicationContext, exo_fourth_view, asset_fourth_route)
    }

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT > 23) {
            exo_play_start()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT <= 23) {
            exo_play_start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT > 23) {
            if (player.isPlaying) CustomExoplayer.releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT > 23) {
            if (player.isPlaying) CustomExoplayer.releasePlayer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT > 23) {
            if (player.isPlaying) CustomExoplayer.releasePlayer()
        }
    }

}
