package com.iloveintouch.demo2

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Products {
    @SerializedName("products")
    @Expose
    var products: List<Product>? = null
    @SerializedName("priceTagJapan")
    @Expose
    var priceTagJapan: List<PriceTagJapan>? = null
}