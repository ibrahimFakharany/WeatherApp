package com.example.weatherapp.utils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.provider.MediaStore.Images
import java.io.ByteArrayOutputStream
import java.io.OutputStream


object ImageUtil {

    fun drawOnImageView(img: Bitmap?, text: String?): Bitmap {
        val config: Bitmap.Config? = img?.config
        val width = img?.width
        val height = img?.height

        val newImage = Bitmap.createBitmap(width!!, height!!, config!!)

        val c = Canvas(newImage)
        c.drawBitmap(img, 0f, 0f, null)

        val paint = Paint()
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.textSize = 100f
        c.drawText(text!!, 0f, 100f, paint)
        return newImage
    }

    fun getByteFromImage(bitmap: Bitmap?): ByteArray {
        var bao = ByteArrayOutputStream();
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, bao)
        return bao.toByteArray()
    }

    fun shareImg(context: Context, bitmap: Bitmap) {
        val icon: Bitmap = bitmap
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/jpeg"

        val values = ContentValues()
        values.put(Images.Media.TITLE, "title")
        values.put(Images.Media.MIME_TYPE, "image/jpeg")
        val uri: Uri? = context.getContentResolver().insert(
            Images.Media.EXTERNAL_CONTENT_URI,
            values
        )


        val outstream: OutputStream?
        try {
            outstream = context.getContentResolver().openOutputStream(uri!!)
            icon.compress(Bitmap.CompressFormat.JPEG, 100, outstream)
            outstream?.close()
        } catch (e: Exception) {
            System.err.println(e.toString())
        }

        share.putExtra(Intent.EXTRA_STREAM, uri)
        context.startActivity(Intent.createChooser(share, "Share Image"))
    }

}