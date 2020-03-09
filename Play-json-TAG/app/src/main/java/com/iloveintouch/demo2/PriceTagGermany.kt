package com.iloveintouch.demo2

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*********************************************************
 *   ,--.           ,--.       ,--.   ,--.
 *   |  |   ,--.,--.|  |,-----.|   `.'   |
 *   |  |   |  ||  ||  |`-.  / |  |'.'|  |
 *   |  '--.'  ''  '|  | /  `-.|  |   |  |
 *   `-----' `----' `--'`-----'`--'   `--'
 *
 * Project : PlayJsonTag
 * Created by Android Studio
 * Developer : Lulz_M
 * Date : 2020/03/09
 * Time : 3:34 PM
 * GitHub : https://github.com/scadasystems
 * E-mail : redsmurf@lulzm.org
 *********************************************************/

class PriceTagGermany {
    @SerializedName("productName")
    @Expose
    var productName: String? = null
    @SerializedName("productAddition")
    @Expose
    var productAddition: String? = null
    @SerializedName("productCount")
    @Expose
    var productCount: String? = null
    @SerializedName("productWeight")
    @Expose
    var productWeight: String? = null
    @SerializedName("productImage")
    @Expose
    var productImage: String? = null
    @SerializedName("price")
    @Expose
    var price: String? = null
    @SerializedName("salePrice")
    @Expose
    var salePrice: String? = null
    @SerializedName("barcode")
    @Expose
    var barcode: String? = null
    @SerializedName("point")
    @Expose
    var point: String? = null
}