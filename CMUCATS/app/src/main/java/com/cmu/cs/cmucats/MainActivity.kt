package com.cmu.cs.cmucats

import android.os.Bundle
import android.widget.FrameLayout
import com.cmu.cs.cmucats.FeatureCourse.CourseFragment
import kotlinx.android.synthetic.main.header.*

class MainActivity : NavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        var check = intent.getIntExtra("check",0)
        if(check == 1){
            teacher_id = "5"
        }
        else{
            teacher_id = "3"
        }

        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(R.layout.activity_main, contentFrameLayout)

        //คำสั่งเพิ่ม Fragment ลงบน ViewGroup
        val myFragment = CourseFragment().newInstance(teacher_id)
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.content_frame, myFragment)
        transaction.commit()
//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.content_frame, CourseFragment(), TAG_COURSE_FRAGMENT)
//                .commit()
//        navigationView.setCheckedItem(R.id.my_course)

    }


}
