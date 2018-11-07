package com.cmu.cs.cmucats

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.cmu.cs.cmucats.Assignment.AssignmentActivity

class FeatureActivity : NavigationActivity(), View.OnClickListener {

    private var course:String? = null

    private var assignmentCard: CardView? = null
    private var attendanceCard: CardView? = null
    private var chatCard: CardView? = null
    private var studentCard: CardView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle = intent.extras
        course = bundle.getString("course")!!
//        setContentView(R.layout.activity_feature)
//        setSupportActionBar(toolbar)

        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
//        for (fragment in supportFragmentManager.fragments) {
//            supportFragmentManager.beginTransaction().remove(fragment).commit()
//        }
        layoutInflater.inflate(R.layout.activity_feature, contentFrameLayout)

        assignmentCard = findViewById(R.id.assignment_card) as CardView
        attendanceCard = findViewById(R.id.attendance_card) as CardView
        chatCard = findViewById(R.id.chat_card) as CardView
        studentCard = findViewById(R.id.student_card) as CardView

        assignmentCard!!.setOnClickListener(this)
        attendanceCard!!.setOnClickListener(this)
        chatCard!!.setOnClickListener(this)
        studentCard!!.setOnClickListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onNavigationItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.setTitle(course)
    }

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.assignment_card -> {
                Toast.makeText(this, "Assignment", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AssignmentActivity::class.java)
                startActivity(intent)
            }
            R.id.attendance_card -> {
                Toast.makeText(this, "Attendance",Toast.LENGTH_SHORT).show()
            }
            R.id.chat_card -> {
                Toast.makeText(this, "Chat",Toast.LENGTH_SHORT).show()
            }
            R.id.student_card -> {
                Toast.makeText(this, "Student",Toast.LENGTH_SHORT).show()
            }
        }
        this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }
}
