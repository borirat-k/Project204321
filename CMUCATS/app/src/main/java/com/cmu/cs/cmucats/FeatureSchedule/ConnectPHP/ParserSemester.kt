package com.cmu.cs.cmucats.FeatureSchedule.ConnectPHP

import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.cmu.cs.cmucats.FeatureSchedule.fragment.Teach_schedule_fragment
import com.cmu.cs.cmucats.R
import com.roger.catloadinglibrary.CatLoadingView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ParserSemester(var context: Context,var jsonData: String) : AsyncTask<Void, Void, Boolean>() {

    private lateinit var mView: CatLoadingView

    companion object {
        var courseScheduleL = ArrayList<CourseScheduleSelect>()
    }



    private fun parse(): Boolean {
        try {
            val ja = JSONArray(jsonData)
            var jo = JSONObject()

            for (i in 0 until ja.length()) {
                jo = ja.getJSONObject(i)
                val semester = jo.getString("semester")
                val count_course = jo.getInt("count_course")


                Teach_schedule_fragment.semester_list.add(semester)
                Teach_schedule_fragment.countCourse_list.add(count_course)

            }

            return true
        } catch (e: JSONException) {
            e.printStackTrace()
            return false
        }
    }


    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        mView.dismiss()
        if (result!!) {
            Toast.makeText(context, "parse semester and count_course successful", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "this is data that we try to parse ${jsonData}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun doInBackground(vararg params: Void?): Boolean {
        return parse()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        mView = CatLoadingView()
        mView.show((context as FragmentActivity).supportFragmentManager, "")
    }
}