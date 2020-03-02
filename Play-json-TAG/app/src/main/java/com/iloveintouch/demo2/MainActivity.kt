package com.iloveintouch.demo2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Parsing */
        val api: ApiService = RetroClient.apiService
        val call = api.productJSON

        call.clone().enqueue(object: retrofit2.Callback<Products> {
            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful) {
                    val productList = response.body()!!.products

//                    for (data in productList!!) {
//                        val data_field1 = data.field1
//                        val data_title = data.title
//                        val data_price = data.price
//                        val data_salePrice = data.salePrice
//                        val data_origin = data.origin
//                        val data_company = data.company
//                        val data_articleId = data.articleId
//                        val data_left = data.left
//                        val data_type = data.type
//                    }
                    for (data in productList!!.indices) {
                        if (productList[data].salePrice.equals(productList[data].price)) {
                            Log.d(TAG, productList[data].title.toString())
                        }
                    }
                }
            }

        })
    }
}
