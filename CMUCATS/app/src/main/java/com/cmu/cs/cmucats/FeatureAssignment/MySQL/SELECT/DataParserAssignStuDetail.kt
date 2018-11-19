package com.cmu.cs.cmucats.FeatureAssignment.MySQL.SELECT

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.widget.TextView
import android.widget.Toast
import com.cmu.cs.cmucats.R
import com.roger.catloadinglibrary.CatLoadingView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class DataParserAssignStuDetail(private var c: Context, private var jsonData: String,
                                private var courseID: String, private var assignmentID: String, private var studentID: String): AsyncTask<Void, Void, Boolean>() {

    var mView: CatLoadingView? = null
    var name_Stu: TextView? = null
    var score_correct: TextView? = null
    var max_score: TextView? = null
    lateinit var nameStu: String
    lateinit var scoreCorrect: String
    lateinit var maxScore: String

    override fun onPreExecute() {
        super.onPreExecute()

//        val intent = Intent(c, AssignStuDetailActivity::class.java)
//        intent.putExtra("course", courseID)
//        intent.putExtra("assign", assignmentID)
//        intent.putExtra("studetail", studentID)
//        c.startActivity(intent)

        name_Stu = (c as Activity).findViewById(R.id.assign_detail_name)
        score_correct = (c as Activity).findViewById(R.id.assign_detail_score)
        max_score = (c as Activity).findViewById(R.id.assign_detail_max_score)

        mView = CatLoadingView()
        mView!!.show((c as FragmentActivity).supportFragmentManager, "")
    }

    override fun doInBackground(vararg p0: Void?): Boolean {
        return parseData()
    }

    override fun onPostExecute(parsed: Boolean?) {
        super.onPostExecute(parsed)

        mView!!.dismiss()

        if (parsed!!){
            println(nameStu)
            println(scoreCorrect)
            println(maxScore)
            name_Stu!!.text = nameStu
            score_correct!!.text = scoreCorrect
            max_score!!.text = maxScore
        }
        else{
            name_Stu!!.text = "not found"
            score_correct!!.text = "0"
            max_score!!.text = "0"
            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseData(): Boolean{
        try {
            var ja: JSONArray = JSONArray(jsonData)
            var jo: JSONObject? = null

//            name_Stu!!.text = ""
//            score_correct!!.text = ""
//            max_score!!.text = ""

            for (i in 0 until ja.length()){
                jo=ja.getJSONObject(i)

                var title = jo.getString("title")
                nameStu = title
                nameStu += " "

                var fname = jo.getString("fname")
                nameStu += fname
                nameStu += " "

                var lname = jo.getString("lname")
                nameStu += lname
                nameStu += " "

//                nameStu?.text  = name

                var correct_score = jo.getString("score_correct")
//                score_correct?.text = correct_score
                scoreCorrect = correct_score

                var score_max = jo.getString("max_score")
//                max_score?.text = score_max
                maxScore = score_max
            }
            return true
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return false

    }
}