package com.cmu.cs.cmucats.Assignment

import android.content.Intent
import android.os.Bundle
import com.cmu.cs.cmucats.NavigationActivity
import com.cmu.cs.cmucats.R

private var course: String? = null
private var assignment: String? = null

class AssignmentStudentActivity : NavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle = intent.extras
        course = bundle.getString("course")
        assignment = bundle.getString("assign")!!
    }

    override fun onBackPressed() {
        val intent = Intent(this, AssignmentActivity::class.java)
        intent.putExtra("course", course)
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.setTitle(course)
        supportActionBar?.setSubtitle(assignment)
    }
}
