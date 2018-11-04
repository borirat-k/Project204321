package com.cmu.cs.cmucats

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class MainActivity : NavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(R.layout.activity_main, contentFrameLayout)

        //คำสั่งเพิ่ม Fragment ลงบน ViewGroup
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, CourseActivity(), TAG_COURSE_FRAGMENT)
                .commit()
        navigationView.setCheckedItem(R.id.my_course)

    }


}