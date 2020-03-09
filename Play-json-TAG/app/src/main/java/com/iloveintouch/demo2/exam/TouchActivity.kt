package com.iloveintouch.demo2.exam

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iloveintouch.demo2.*
import kotlinx.android.synthetic.main.activity_touch.*
import retrofit2.Call
import retrofit2.Response
import java.text.NumberFormat

class TouchActivity : AppCompatActivity() {

    var nf = NumberFormat.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_touch)

        val listener = View.OnTouchListener(function = { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                view.x = motionEvent.rawX - view.width / 2
//                view.y = motionEvent.rawY - view.height / 2
            }
            true
        })
        cv_jp_first.setOnTouchListener(listener)
        cv_jp_second.setOnTouchListener(listener)

        /* Parsing */
        val api: ApiService = RetroClient.apiService
        val call = api.productJSON

        call.clone().enqueue(object : retrofit2.Callback<Products> {
            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful) {
                    val priceTagList = response.body()!!.priceTagJapan
                    for (data in priceTagList!!.indices) {

                        val tv_totalNumber =
                            resources.getIdentifier("tv_totalNumber${data + 1}", "id", packageName)
                        val tv_company =
                            resources.getIdentifier("tv_company${data + 1}", "id", packageName)
                        val tv_title =
                            resources.getIdentifier("tv_title${data + 1}", "id", packageName)
                        val tv_kind =
                            resources.getIdentifier("tv_kind${data + 1}", "id", packageName)
                        val tv_weight =
                            resources.getIdentifier("tv_weight${data + 1}", "id", packageName)
                        val tv_price =
                            resources.getIdentifier("tv_price${data + 1}", "id", packageName)
                        val tv_priceTax =
                            resources.getIdentifier("tv_priceTax${data + 1}", "id", packageName)
                        val ll_coupon =
                            resources.getIdentifier("ll_coupon${data + 1}", "id", packageName)
                        val iv_product =
                            resources.getIdentifier("iv_product${data + 1}", "id", packageName)
                        val tv_point =
                            resources.getIdentifier("tv_point${data + 1}", "id", packageName)

                        val data_totalNumber = findViewById<TextView>(tv_totalNumber)
                        val data_company = findViewById<TextView>(tv_company)
                        val data_title = findViewById<TextView>(tv_title)
                        val data_kind = findViewById<TextView>(tv_kind)
                        val data_weight = findViewById<TextView>(tv_weight)
                        val data_price = findViewById<TextView>(tv_price)
                        val data_priceTax = findViewById<TextView>(tv_priceTax)
                        val data_coupon = findViewById<LinearLayout>(ll_coupon)
                        val data_productImage = findViewById<ImageView>(iv_product)
                        val data_point = findViewById<TextView>(tv_point)

                        val result = nf.format(priceTagList[data].totalCountNumber?.toInt())
                        data_totalNumber.text = "${result}개"
                        data_company.text = priceTagList[data].company
                        data_title.text = priceTagList[data].title
                        data_kind.text = priceTagList[data].kind
                        data_weight.text = "각 ${priceTagList[data].weight}g"
                        data_price.text = "각 ${priceTagList[data].price} 円"
                        data_priceTax.text = "(세금 후, 각 ${priceTagList[data].priceTax} 円)"
                        if (priceTagList[data].appCoupon == true) {
                            data_coupon.visibility = View.VISIBLE
                        } else {
                            data_coupon.visibility = View.GONE
                        }
                        glide(applicationContext, priceTagList[data].images, data_productImage)
                        data_point.text = priceTagList[data].point
                    }
                }
            }

        })
    }
}
