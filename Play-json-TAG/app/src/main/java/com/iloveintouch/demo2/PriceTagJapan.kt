package com.iloveintouch.demo2

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PriceTagJapan {
    @SerializedName("totalCountNumber")
    @Expose
    var totalCountNumber: String? = null
    @SerializedName("company")
    @Expose
    var company: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("kind")
    @Expose
    var kind: String? = null
    @SerializedName("weight")
    @Expose
    var weight: String? = null
    @SerializedName("price")
    @Expose
    var price: String? = null
    @SerializedName("priceTax")
    @Expose
    var priceTax: String? = null
    @SerializedName("images")
    @Expose
    var images: String? = null
    @SerializedName("point")
    @Expose
    var point: String? = null
    @SerializedName("appCoupon")
    @Expose
    var appCoupon: Boolean? = null
}