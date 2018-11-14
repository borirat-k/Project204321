package com.cmu.cs.cmucats.FeatureAssignment.MySQL

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.widget.TextView
import com.cmu.cs.cmucats.R
import com.roger.catloadinglibrary.CatLoadingView
import kotlinx.android.synthetic.main.content_assign_stu_detail.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class DataParserAssignStuDetail(private var c: Context, private var jsonData: String,
                                private var courseID: String, private var assignmentID: String, private var studentID: String): AsyncTask<Void, Void, Boolean>() {

    var mView: CatLoadingView? = null
    var nameStu: TextView? = null
    var score_correct: TextView? = null
    var max_score: TextView? = null

    override fun onPreExecute() {
        super.onPreExecute()

        nameStu = (c as Activity).findViewById(R.id.assign_detail_name)
        score_correct = (c as Activity).findViewById(R.id.assign_detail_score)
        max_score = (c as Activity).findViewById(R.id.assign_detail_max_score)

        mView = CatLoadingView()
        mView!!.show((c as FragmentActivity).supportFragmentManager, "")
    }

    override fun doInBackground(vararg p0: Void?): Boolean {
        return parseData()
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)

        mView!!.dismiss()
    }

    private fun parseData(): Boolean{
        try {
            var ja: JSONArray = JSONArray(jsonData)
            var jo: JSONObject? = null

            nameStu!!.text = ""
            score_correct!!.text = ""
            max_score!!.text = ""

            for (i in 0 until ja.length()){
                jo=ja.getJSONObject(i)

                var name: String
                var title = jo.getString("title")
                name = title
                name += " "

                var fname = jo.getString("fname")
                name += fname
                name += " "

                var lname = jo.getString("lname")
                name += lname
                name += " "

                nameStu!!.text = name

                var correct_score = jo.getString("score_correct")
                score_correct!!.text = correct_score

                var score_max = jo.getString("max_score")
                max_score!!.text = score_max
            }
            return true
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return false

    }
}