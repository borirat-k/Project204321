package com.cmu.cs.cmucats.FeatureAssignment.MySQL.SELECT

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.cmu.cs.cmucats.FeatureAssignment.Assignment.Assignment
import com.cmu.cs.cmucats.FeatureAssignment.Assignment.AssignmentAdapter
import com.cmu.cs.cmucats.FeatureAssignment.Assignment.AssignmentStudent
import com.cmu.cs.cmucats.FeatureAssignment.Student.AssignStudentAdapter
import com.roger.catloadinglibrary.CatLoadingView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class DataParserAssignment(private var c: Context, private var jsonData: String, private var rv: RecyclerView,
                           private var courseID: String, private var flag: String, private var assignmentID: String, private var teacherID: String): AsyncTask<Void, Void, Boolean>() {

    private lateinit var pd: ProgressDialog
    private var assignments = ArrayList<Assignment>()
    private var assignStu = ArrayList<AssignmentStudent>()
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
            if (flag == "assign") {
                val adapter = AssignmentAdapter(assignments, c, courseID, teacherID)
                rv.adapter = adapter
            }
            else if (flag == "student"){
                val adapter = AssignStudentAdapter(assignStu, c, courseID, assignmentID, teacherID)
                rv.adapter = adapter
            }
        }
        else{
            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseData(): Boolean{
        try {
            var ja: JSONArray = JSONArray(jsonData)
            var jo: JSONObject? = null

//            jo.put("Cid", course)

            assignments.clear()
            assignStu.clear()

            for (i in 0 until ja.length()){
                jo=ja.getJSONObject(i)

                if (flag == "assign") {
                    var assignment: Assignment
                    var Aid = jo.getString("Aid")
                    assignment = Assignment(Aid)
                    assignments.add(assignment)
                }
                else if (flag == "student"){
                    var student: AssignmentStudent
                    var stu_id = jo.getString("Stu_id")
                    student = AssignmentStudent(stu_id)
                    assignStu.add(student)
                }
            }
            return true
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return false

    }
}