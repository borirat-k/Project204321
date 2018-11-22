package com.cmu.cs.cmucats.FeatureSchedule.ConnectPHP

import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.cmu.cs.cmucats.FeatureSchedule.fragment.Teach_input_form_activity
import com.cmu.cs.cmucats.FeatureSchedule.fragment.Teach_schedule_fragment
import com.roger.catloadinglibrary.CatLoadingView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ParserSubject(var context: Context, var jsonData: String) : AsyncTask<Void, Void, Boolean>() {

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
                val idCourse = jo.getString("idCourse")

                Teach_schedule_fragment.idCourseList.add(idCourse)
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
            Toast.makeText(context, "parse subject successful", Toast.LENGTH_SHORT).show()
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
