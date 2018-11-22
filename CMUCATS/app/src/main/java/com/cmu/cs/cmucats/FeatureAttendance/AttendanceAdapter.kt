package com.cmu.cs.cmucats.FeatureAttendance


import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cmu.cs.cmucats.R


class AttendanceAdapter(val attendancelist: ArrayList<Attendance>) : RecyclerView.Adapter<AttendanceAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_attendance, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val attendance : Attendance = attendancelist[position]
        viewHolder.Attendance_text.text = attendance.id

    }

    override fun getItemCount(): Int {
        return attendancelist.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var mName: View = view
        var activity = mName as Activity

        val Attendance_text = view.findViewById<View>(R.id.attendance_text) as TextView
    }

}