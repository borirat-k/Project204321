package com.cmu.cs.cmucats.FeatureCourse

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.cmu.cs.cmucats.R

class CourseFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.activity_course, container, false)

//        val contentFrameLayout: FrameLayout = view.findViewById<FrameLayout>(R.id.content_frame)
//        layoutInflater.inflate(R.layout.activity_course, contentFrameLayout)

        val recyclerView = view.findViewById(R.id.recycleView_course) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)

        val course = ArrayList<Course>()

        //add course from database mySQL
        course.add(Course("204100", "IT AND MODERN LIFE"))
        course.add(Course("204361", "SOFTWARE ENGINEERING"))
        course.add(Course("204451", "ALGO DESIGN & ANALYSIS3"))
        course.add(Course("204111", "FUNDAMENTALS OF PROGRAMMING"))
        course.add(Course("204321", "DATABASE SYSTEM 1"))
        course.add(Course("204321", "DATABASE SYSTEM 1"))
        course.add(Course("204321", "DATABASE SYSTEM 1"))
        course.add(Course("204321", "DATABASE SYSTEM 1"))
        course.add(Course("204321", "DATABASE SYSTEM 1"))
        course.add(Course("204321", "DATABASE SYSTEM 1"))
        course.add(Course("204321", "DATABASE SYSTEM 1"))

        val adapter = CourseAdapter(course, view.context)

        recyclerView.adapter = adapter
        return view
    }



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
//        layoutInflater.inflate(R.layout.activity_course, contentFrameLayout)
//
//        val recyclerView = findViewById(R.id.recycleView_course) as RecyclerView
//        recyclerView.layoutManager = LinearLayoutManager(baseContext, LinearLayout.VERTICAL, false)
//
//        val course = ArrayList<FeatureCourse>()
//
//        //add course from database mySQL
//        course.add(FeatureCourse("204321", "DATABASE SYSTEM 1"))
//        course.add(FeatureCourse("204361", "SOFTWARE ENGINEERING"))
//        course.add(FeatureCourse("204451", "ALGO DESIGN & ANALYSIS3"))
//        course.add(FeatureCourse("204321", "DATABASE SYSTEM 1"))
//        course.add(FeatureCourse("204321", "DATABASE SYSTEM 1"))
//        course.add(FeatureCourse("204321", "DATABASE SYSTEM 1"))
//        course.add(FeatureCourse("204321", "DATABASE SYSTEM 1"))
//        course.add(FeatureCourse("204321", "DATABASE SYSTEM 1"))
//        course.add(FeatureCourse("204321", "DATABASE SYSTEM 1"))
//        course.add(FeatureCourse("204321", "DATABASE SYSTEM 1"))
//        course.add(FeatureCourse("204321", "DATABASE SYSTEM 1"))
//
//        val adapter = CourseAdapter(course, this)
//
//        recyclerView.adapter = adapter
//    }

    override fun onResume() {
        super.onResume()
        activity?.setTitle("Course")
    }
}