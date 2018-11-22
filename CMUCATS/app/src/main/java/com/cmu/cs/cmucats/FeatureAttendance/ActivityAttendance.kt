package com.cmu.cs.cmucats.FeatureAttendance

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.R.id.container
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.cmu.cs.cmucats.FeatureAttendance.PHP.Downloadattendance
import com.cmu.cs.cmucats.R
import com.cmu.cs.cmucats.NavigationActivity
import com.cmu.cs.cmucats.FeatureActivity


private var courseID: String? = null
private var teacherID: String? = null

class ActivityAttendance() : NavigationActivity() {




        private var mRecyclerView: RecyclerView? = null
        private var mAdapter: RecyclerView.Adapter<*>? = null


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_assignment)
            val bundle: Bundle = intent.extras
            courseID = bundle.getString("course")!!
            teacherID = bundle.getString("teacher")!!

//            var view: View = inflater.inflate(R.layout.activity_attendance, container, false)
//            val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
//            layoutInflater.inflate(R.layout.activity_attendance, contentFrameLayout)

            val recyclerView = findViewById<View>(R.id.attendance_recycle) as RecyclerView

//
//            val fab = view.findViewById(R.id.attendance_recycle) as? FloatingActionButton
//            if (fab != null) {
//                fab.setOnClickListener { view ->
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show()
//                mRecyclerView!!.setHasFixedSize(true)
//                mRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
//    //            mAdapter = AttendanceAdapter(this, Downloadattendance())
//                mRecyclerView!!.adapter = mAdapter


    //            Downloadattendance(address,recyclerView,course_id!!).execute()


        }
            override fun onBackPressed() {
//        if (supportFragmentManager.findFragmentByTag(TAG_ASSIGNMENT_FRAGMENT)) {
                val intent = Intent(this, FeatureActivity::class.java)
                intent.putExtra("course", courseID)
                intent.putExtra("teacher", teacherID)
                startActivity(intent)
                finish()
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
            }



}
class AssignmentFragment : Fragment() {

        val FLAG_ATTENDANCE = "attendance"

        var urlAdress: String = "http://10.0.2.2/Project204321/select_attendance.php"

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {
            var view: View = inflater.inflate(R.layout.activity_attendance, container, false)

            val recyclerView = view.findViewById<View>(R.id.attendance_recycle) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)


            val fab = view.findViewById(R.id.attendance_recycle) as? FloatingActionButton
            if (fab != null) {
                fab.setOnClickListener { view ->
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                }
                Downloadattendance(view.context,urlAdress,recyclerView,courseID!!).execute()
//                    val attendance = ArrayList<Attendance>()
//
//                    //add course from database mySQL
//                    attendance.add(Attendance("590510555"))
//                    attendance.add(Attendance("5905105512"))
//                    attendance.add(Attendance("5905105513"))
//                    attendance.add(Attendance("5905105514"))
//                    attendance.add(Attendance("5905105515"))
//                    attendance.add(Attendance("5905105516"))
//                    attendance.add(Attendance("5905105517"))
//                    attendance.add(Attendance("5905105518"))
//                    attendance.add(Attendance("5905105519"))
//                    attendance.add(Attendance("5905105520"))
//                    attendance.add(Attendance("5905105521"))
//
//                    val adapter = AttendanceAdapter(attendance,view.context)
//
//                    recyclerView.adapter = adapter



            }
            return view
        }
        override fun onResume() {
            super.onResume()
            activity?.setTitle("Attendance")
    }

    }


//    var addressatt: String = "http://10.0.3.89/Project204321/attendance.php"
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //setContentView(R.layout.activity_attendance)
//        val bundle: Bundle = intent.extras
//        courseID = bundle.getString("course")!!
//        teacherID = bundle.getString("teacher")!!
//
//        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
//        layoutInflater.inflate(R.layout.activity_attendance, contentFrameLayout)
//        super.onCreate(savedInstanceState)
//
//        val recyclerView = findViewById(R.id.attendance_recycle) as RecyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
//
//        Downloadattendance(this@ActivityAttendance, addressatt, recyclerView).execute()
//
//
//    }
//
//    class AttendanceFragment : Fragment() {
//
//        val FLAG_ATTENDANCE = "attendance"
//
//        var urlAdress: String = "http://10.0.2.2/Project204321/select_attendance.php"
//
//        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//            var view: View = inflater.inflate(R.layout.activity_attendance, container, false)
//
//            val recyclerView = view.findViewById(R.id.attendance_recycle) as RecyclerView
//            recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)
//
//            val fab = view.findViewById(R.id.attendance_layout) as FloatingActionButton
//            fab.setOnClickListener { view ->
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show()
//            }
//
//            Downloadattendance(view.context, urlAdress, recyclerView, FLAG_ATTENDANCE).execute()
//            return view
//
//        }
//


