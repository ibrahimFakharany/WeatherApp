package com.example.weatherapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.Constants
import com.example.weatherapp.R
import com.example.weatherapp.utils.ImageSaver
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        image.let { imageview ->

            intent.extras.let {
                var bitmap =
                    ImageSaver(this).setFileName(it?.getString(Constants.IMAGE_INTENT_KEY))
                        .setDirectoryName("images").load()
                imageview.setImageBitmap(bitmap)
            }

        }

    }
}