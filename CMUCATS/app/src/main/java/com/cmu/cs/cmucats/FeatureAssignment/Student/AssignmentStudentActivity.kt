package com.cmu.cs.cmucats.FeatureAssignment.Student

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.cmu.cs.cmucats.FeatureAssignment.Assignment.AssignmentActivity
import com.cmu.cs.cmucats.FeatureAssignment.Assignment.AssignmentStudent
import com.cmu.cs.cmucats.FeatureAssignment.MySQL.DownloaderAssignment
import com.cmu.cs.cmucats.NavigationActivity
import com.cmu.cs.cmucats.R
import kotlinx.android.synthetic.main.content_assignment_student.*

private var course: String? = null
private var assignment: String? = null

class AssignmentStudentActivity : NavigationActivity() {

    val FLAG_ASSIGNMENT = "student"

    var urlAdress: String = "http://10.0.2.2/Project204321/select_assign_stu.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle = intent.extras
        course = bundle.getString("course")
        assignment = bundle.getString("assign")!!

        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(R.layout.activity_assignment, contentFrameLayout)

        val contentAssignment: FrameLayout = findViewById(R.id.content_assignment)
        layoutInflater.inflate(R.layout.content_assignment_student, contentAssignment)

        val recyclerView = findViewById(R.id.recycleView_assign_stu) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        take_camera_assignment.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        DownloaderAssignment(this@AssignmentStudentActivity, urlAdress, recyclerView, course!!, FLAG_ASSIGNMENT).execute()

//        val assignmentStuID = ArrayList<AssignmentStudent>()

        //add assignment student from database mySQL
//        assignmentStuID.add(AssignmentStudent("204100"))
//        assignmentStuID.add(AssignmentStudent("204361"))
//        assignmentStuID.add(AssignmentStudent("204451"))
//        assignmentStuID.add(AssignmentStudent("204111"))
//        assignmentStuID.add(AssignmentStudent("204321"))
//        assignmentStuID.add(AssignmentStudent("204321"))
//        assignmentStuID.add(AssignmentStudent("204321"))
//        assignmentStuID.add(AssignmentStudent("204321"))
//        assignmentStuID.add(AssignmentStudent("204321"))
//        assignmentStuID.add(AssignmentStudent("204321"))
//        assignmentStuID.add(AssignmentStudent("204321"))

//        val adapter = CourseAdapter(assignmentStuID, this, course!!)
//        recyclerView.adapter = adapter
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
