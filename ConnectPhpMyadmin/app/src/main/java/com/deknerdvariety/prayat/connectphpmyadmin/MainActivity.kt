package com.deknerdvariety.prayat.connectphpmyadmin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        val Tag = "http://192.168.0.102:80/upload.php"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        insert_btn.setOnClickListener{
            var bgwk = BackgroundWorker(this)
            bgwk.execute(Tag)

            Toast.makeText(this,"clik insert btn",Toast.LENGTH_SHORT).show()

        }
    }
}
