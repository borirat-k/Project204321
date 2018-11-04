package com.cmu.cs.cmucats.Course

import android.app.Activity
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cmu.cs.cmucats.R
import com.cmu.cs.cmucats.FeatureActivity
import android.content.Intent
import android.widget.Toast



class CustomAdapter(val courseList: ArrayList<Course>, context: Context): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var mContext: Context = context
    private var activity = mContext as Activity

    //มีหน้าที่เพื่อให้เราสร้าง view ต่างๆแล้วเก็บไว้ใน ViewHolder อีกที
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_course, parent, false)
        return ViewHolder(v)
    }

    //มีหน้าที่บอกว่าเรามีข้อมูลที่ต้องการจะแสดงผลทั้งหมดกี่ชุด
    override fun getItemCount(): Int {
        return courseList.size
    }

    //มีหน้าที่จัดการข้อมูลที่ใส่เข้าไป เพื่อนำไปแสดงผล บน view ตามที่ต้องการ
    //เช่น ใส่ค่า String เข้าไป เพื่อนำไปแสดงผลบน textView หรือตอนที่มีการ scroll ข้อมูลใหม่เข้ามาแสดงผลบนหน้าจอ
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course: Course = courseList[position]
        holder.textCourseID.text = course.id
        holder.textCourseName.text = course.name

        //set Click option menu
//        holder.textCourseOption

        holder.course_layout.setOnClickListener(object :View.OnClickListener {
            override fun onClick(p0: View?) {
                Toast.makeText(mContext, "onClick: clicked on: ", Toast.LENGTH_SHORT).show()

                val intent = Intent(mContext, FeatureActivity::class.java)
                println(holder.textCourseID.text)
                intent.putExtra("course", holder.textCourseID.text)
                mContext.startActivity(intent)
                activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
        })

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){//, View.OnClickListener{

        private var view: View = itemView

        val textCourseID = itemView.findViewById(R.id.textCourseID) as TextView
        val textCourseName = itemView.findViewById(R.id.textCourseName) as TextView
        val textCourseOption = itemView.findViewById(R.id.textCourseOption) as TextView
        val course_layout = itemView.findViewById(R.id.course_layout) as ConstraintLayout
//        var itemClickListener: ItemClickListener? = null
//
//        init{
//            view.setOnClickListener(this)
//        }
//
//        fun setItemClickListener(itemClickListener: ItemClickListener){
//            this.itemClickListener=itemClickListener
//        }
//
//
//        override fun onClick(p0: View?) {
//            this.itemClickListener.onItemClick(layoutPosition)
//        }
    }
}