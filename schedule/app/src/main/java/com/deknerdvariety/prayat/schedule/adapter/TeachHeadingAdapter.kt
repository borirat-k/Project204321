package com.deknerdvariety.prayat.schedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deknerdvariety.prayat.schedule.ConnectPHP.CourseScheduleSelect
import com.deknerdvariety.prayat.schedule.ConnectPHP.HeadScedule
import com.deknerdvariety.prayat.schedule.R
import kotlinx.android.synthetic.main.teach_list_item.view.*

class TeachHeadingAdapter(val items : ArrayList<HeadScedule>, val context:Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.teach_list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder?.teach_heading?.text = items.get(position)
        holder.view.teach_heading.text = items[position].semester
    }
}

class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to

}