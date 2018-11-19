package com.cmu.cs.cmucats.FeatureCourse

import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.roger.catloadinglibrary.CatLoadingView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class parsercourse (private var c: Context, private var jsonData: String,
                    private var rv: RecyclerView, private var Tid: String): AsyncTask<Void, Void, Boolean>() {


    val courses = ArrayList<Course>()
    var mView: CatLoadingView? = null

    override fun onPreExecute() {
        super.onPreExecute()

        mView = CatLoadingView()
        mView!!.show((c as FragmentActivity).supportFragmentManager, "")

//        pd = ProgressDialog(c)
//        pd.setTitle("Parse JSON")
//        pd.setMessage("Parsing...Please wait")
//        pd.show()
    }

    override fun doInBackground(vararg p0: Void?): Boolean? {
        return parseData()
    }

    override fun onPostExecute(parsed: Boolean?) {
        super.onPostExecute(parsed)

//        pd.dismiss()
        mView!!.dismiss()

        if (parsed!!){
            //BIND
            val adapter = CourseAdapter(courses, c, Tid)
            rv.adapter = adapter
        }
        else{
            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseData(): Boolean{
        try {
            var ja: JSONArray = JSONArray(jsonData)
            var jo: JSONObject? = null

            courses.clear()

            for (i in 0 until ja.length()){
                jo=ja.getJSONObject(i)


                var course: Course
                var course_id = jo.getString("Cid")
                var course_name = jo.getString("C_name")
                course = Course(course_id, course_name)
                courses.add(course)

            }
            return true
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return false

    }
}