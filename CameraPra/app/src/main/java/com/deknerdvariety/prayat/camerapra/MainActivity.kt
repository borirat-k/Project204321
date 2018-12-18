package com.deknerdvariety.prayat.camerapra

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        val gotoC = findViewById<Button>(R.id.goCamera)
        gotoC.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.getId()) {
            R.id.goCamera -> {
                val intent = Intent(this, Camera2api::class.java)
                startActivity(intent)
            }
        }
    }
}
