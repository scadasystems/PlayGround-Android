package com.iloveintouch.demo2.util

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import java.util.*

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
 * Date : 2020/03/10
 * Time : 3:52 PM
 * GitHub : https://github.com/scadasystems
 * E-mail : redsmurf@lulzm.org
 *********************************************************/

object CustomBarcode {
    fun encodeAsBitmap(contents: String?, format: BarcodeFormat?, img_width: Int, img_height: Int): Bitmap? {
        val contentsToEncode = contents ?: return null
        var hints: MutableMap<EncodeHintType?, Any?>? = null
        val encoding = guessAppropriateEncoding(contentsToEncode)
        if (encoding != null) {
            hints = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
            hints[EncodeHintType.CHARACTER_SET] = encoding
        }

        val writer = MultiFormatWriter()
        val result: BitMatrix
        result = try { writer.encode(contentsToEncode, format, img_width, img_height, hints)
        } catch (iae: IllegalArgumentException) {
            return null
        }

        val width = result.width
        val height = result.height
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (result[x, y]) Color.BLACK else Color.TRANSPARENT
            }
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    private fun guessAppropriateEncoding(contents: CharSequence): String? {
        for (element in contents) {
            if (element.toInt() > 0xFF) {
                return "UTF-8"
            }
        }
        return null
    }
}