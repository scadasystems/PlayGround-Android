package com.iloveintouch.demo2

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.iloveintouch.demo2.util.glide
import retrofit2.Call
import retrofit2.Response

class LabelCollectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_label_collection)

        /* Parsing */
        val api: ApiService = RetroClient.apiService
        val call = api.productJSON

        call.clone().enqueue(object : retrofit2.Callback<Products> {
            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful) {
                    val priceTagGermany = response.body()!!.priceTagGermany
                    for (data in priceTagGermany!!.indices) {
                        val type = resources.getIdentifier(
                            "label_type${data + 1}",
                            "id",
                            packageName
                        )

                        val label_type = findViewById<View>(type)   // include 정의
                        /* json id 정의 */
                        val productName =
                            label_type.findViewById<AppCompatTextView>(R.id.insert_product_name)
                        val productAddition =
                            label_type.findViewById<AppCompatTextView>(R.id.insert_product_addition)
                        val productCount =
                            label_type.findViewById<AppCompatTextView>(R.id.insert_product_count)
                        val productWeight =
                            label_type.findViewById<AppCompatTextView>(R.id.insert_product_weight)
                        val productImage =
                            label_type.findViewById<AppCompatImageView>(R.id.insert_product_img)
                        val price_euro =
                            label_type.findViewById<AppCompatTextView>(R.id.insert_price_euro)
                        val price_cent =
                            label_type.findViewById<AppCompatTextView>(R.id.insert_price_cent)
                        val salePrice_euro =
                            label_type.findViewById<AppCompatTextView>(R.id.insert_price_sale_euro)
                        val salePrice_cent =
                            label_type.findViewById<AppCompatTextView>(R.id.insert_price_sale_cent)
                        val barcode =
                            label_type.findViewById<AppCompatTextView>(R.id.insert_barcode)
                        val barcodeImage =
                            label_type.findViewById<AppCompatImageView>(R.id.insert_barcode_img)
                        val point =
                            label_type.findViewById<AppCompatTextView>(R.id.insert_point)

                        productName.text = priceTagGermany[data].productName
                        productAddition.text = priceTagGermany[data].productAddition
                        productCount.text = priceTagGermany[data].productCount
                        productWeight.text = "${priceTagGermany[data].productWeight}g"
                        if (!priceTagGermany[data].productImage.isNullOrEmpty()) {
                            glide(
                                applicationContext,
                                priceTagGermany[data].productImage,
                                productImage
                            )
                        }
                        val euro = priceTagGermany[data].price?.split(".")
                        val sale_euro = priceTagGermany[data].salePrice?.split(".")
                        price_euro.text = euro?.get(0)
                        price_cent.text = euro?.get(1)
                        if (!priceTagGermany[data].salePrice.isNullOrEmpty()) {
                            salePrice_euro.text = sale_euro?.get(0)
                            salePrice_cent.text = sale_euro?.get(1)
                        }

                        barcode.text = priceTagGermany[data].barcode
                        point.text = "${priceTagGermany[data].point}p"
                    }
                }
            }

        })

    }
}


/*
*  초기화 값
* <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:visibility="gone">

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/insert_product_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/insert_product_addition"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/insert_product_count"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/insert_product_weight"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/insert_product_img"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/insert_price_euro"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/insert_price_cent"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/insert_price_sale_euro"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/insert_price_sale_cent"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/insert_barcode"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/insert_barcode_img"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/insert_point"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />
    </LinearLayout>
* */