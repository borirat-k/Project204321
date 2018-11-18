package com.cmu.cs.cmucats.FeatureCourse

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.cmu.cs.cmucats.NavigationActivity
import com.cmu.cs.cmucats.R

class Editstudent : NavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_editstudent)

        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(R.layout.activity_editstudent, contentFrameLayout)
    }
}