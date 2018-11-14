package com.cmu.cs.cmucats.FeatureAssignment.Student

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cmu.cs.cmucats.R
import android.widget.Toast
import com.cmu.cs.cmucats.FeatureAssignment.Assignment.AssignmentStudent


class AssignStudentAdapter(val assignList: ArrayList<AssignmentStudent>, context: Context,
                           private var courseID: String, private var assignmentID: String): RecyclerView.Adapter<AssignStudentAdapter.ViewHolder>() {

    private var mContext: Context = context
    private var activity = mContext as Activity

    val TAG_ASSIGN_STU_FRAGMENT = "tag_assign_stu_fragment"

    //มีหน้าที่เพื่อให้เราสร้าง view ต่างๆแล้วเก็บไว้ใน ViewHolder อีกที
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_student, parent, false)
        return ViewHolder(v)
    }

    //มีหน้าที่บอกว่าเรามีข้อมูลที่ต้องการจะแสดงผลทั้งหมดกี่ชุด
    override fun getItemCount(): Int {
        return assignList.size
    }

    //มีหน้าที่จัดการข้อมูลที่ใส่เข้าไป เพื่อนำไปแสดงผล บน view ตามที่ต้องการ
    //เช่น ใส่ค่า String เข้าไป เพื่อนำไปแสดงผลบน textView หรือตอนที่มีการ scroll ข้อมูลใหม่เข้ามาแสดงผลบนหน้าจอ
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assignstu: AssignmentStudent = assignList[position]
        holder.textAssignStuID.text = assignstu.id

        holder.student_layout.setOnClickListener(object :View.OnClickListener {
            override fun onClick(p0: View?) {
                Toast.makeText(mContext, "onClick: clicked on: ", Toast.LENGTH_SHORT).show()

                val intent = Intent(mContext, AssignStuDetailActivity::class.java)
                intent.putExtra("course", courseID)
                intent.putExtra("assign", assignmentID)
                intent.putExtra("studetail", holder.textAssignStuID.text)
                mContext.startActivity(intent)
//                activity.finish()
            }
        })
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){//, View.OnClickListener{

        val textAssignStuID = itemView.findViewById(R.id.textAssignStuID) as TextView
        val student_layout = itemView.findViewById(R.id.student_layout) as ConstraintLayout
    }
}