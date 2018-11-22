package com.cmu.cs.cmucats.FeatureSchedule.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmu.cs.cmucats.FeatureSchedule.ConnectPHP.HeadScedule
import com.cmu.cs.cmucats.FeatureSchedule.ConnectPHP.JsonDownloadDetailSchedule
import com.cmu.cs.cmucats.FeatureSchedule.timetable.timetable
import com.cmu.cs.cmucats.R
import kotlinx.android.synthetic.main.teach_list_item.view.*

class TeachHeadingAdapter(val items: ArrayList<HeadScedule>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

//    val TAG_DOWNLOAD_DETAIL_SCHEDULE = "http://10.80.100.107/Project204321/fetchdata.php"
    val TAG_DOWNLOAD_DETAIL_SCHEDULE = "http://192.168.0.102/Project204321/fetchdata.php"

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.teach_list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder?.teach_heading?.text = items.get(position)
//        ไม่กำหนดตัวเเปร ใช้ไอดีโดยตรง
        holder.view.teach_heading.text = "ตารางสอนปีการศึกษา ${items[position].semester}"
        //val intent = Intent(holder.view.context,timetable::class.java)
        //holder.view.context.startActivity(intent)
        holder.view.teach_heading.setOnClickListener {
            //println("dddddddddddddddddddddddddddddddddddd")
            var tc_id = items[position].tc_id
            val check_list = 1
            JsonDownloadDetailSchedule(holder.view.context, TAG_DOWNLOAD_DETAIL_SCHEDULE, tc_id).execute()
            val intent = Intent(holder.view.context, timetable::class.java)
            intent.putExtra("check_list",check_list)
            holder.view.context.startActivity(intent)
        }
    }
}

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
//   var teach_head = view.findViewById<TextView>(R.id.teach_heading)

    //    การเขียน onclick in class viewholder
//    init {
//        view.setOnClickListener {
//            val intent = Intent(view.context, timetable::class.java)
//            view.context.startActivity(intent)
//        }
//    }
}