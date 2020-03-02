package com.iloveintouch.demo2

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Product {
    @SerializedName("field1")
    @Expose
    var field1: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("price")
    @Expose
    var price: String? = null
    @SerializedName("salePrice")
    @Expose
    var salePrice: String? = null
    @SerializedName("origin")
    @Expose
    var origin: String? = null
    @SerializedName("company")
    @Expose
    var company: String? = null
    @SerializedName("articleId")
    @Expose
    var articleId: String? = null
    @SerializedName("left")
    @Expose
    var left: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
}