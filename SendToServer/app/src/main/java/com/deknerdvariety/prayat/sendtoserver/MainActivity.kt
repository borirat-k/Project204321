package com.deknerdvariety.prayat.sendtoserver

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.graphics.BitmapFactory
import android.graphics.Bitmap



class MainActivity : AppCompatActivity() {
//    lateinit var image:Bitmap;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goOut_btn = findViewById<Button>(R.id.out_btn)

        goOut_btn.setOnClickListener {
            var intent = Intent(this,Camera::class.java)
            startActivity(intent)

//            val uri = Uri.parse(Camera.name)
        }
//        image = BitmapFactory.decodeFile(Camera.name)
//        Upload(image, "first_one").execute()
    }




}
