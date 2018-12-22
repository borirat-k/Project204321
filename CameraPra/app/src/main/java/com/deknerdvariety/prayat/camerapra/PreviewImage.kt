package com.deknerdvariety.prayat.camerapra

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.ImageView

class PreviewImage:AppCompatActivity() {

    private var bytedata:ByteArray? = null
    private lateinit var preview: ImageView
    private lateinit var sendBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview_activity)

        preview = findViewById(R.id.PreviewImage)
        val bundle = intent.extras
        if (bundle != null) {
            bytedata = bundle.getByteArray("data")
        }
        val bmp = BitmapFactory.decodeByteArray(bytedata, 0, bytedata!!.size)
        preview.setImageBitmap(bmp)

    }
}