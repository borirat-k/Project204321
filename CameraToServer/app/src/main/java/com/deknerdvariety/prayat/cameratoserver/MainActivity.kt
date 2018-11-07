package com.deknerdvariety.prayat.cameratoserver

import android.content.Intent
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseIntArray
import android.view.Surface
import android.view.TextureView
import android.widget.Button
import android.widget.ImageButton
import camera.CameraJava
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.HashMap
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var passBtn = findViewById<Button>(R.id.passBtn)

        passBtn.setOnClickListener {

            intent = Intent(this, CameraJava::class.java)
//            intent.putExtra("count_file",count_file)
            startActivity(intent)
        }

    }
}





