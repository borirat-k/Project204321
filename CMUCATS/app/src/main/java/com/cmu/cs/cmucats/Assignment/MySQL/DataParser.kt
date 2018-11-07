package com.cmu.cs.cmucats.Assignment.MySQL

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.cmu.cs.cmucats.Assignment.Assignment
import com.cmu.cs.cmucats.Assignment.CustomAdapter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class DataParser(private var c: Context, private var jsonData: String, private var rv: RecyclerView): AsyncTask<Void, Void, Boolean>() {

    private lateinit var pd: ProgressDialog
    private var assignments = ArrayList<Assignment>()

    override fun doInBackground(vararg p0: Void?): Boolean? {
        return parseData()
    }

    override fun onPreExecute() {
        super.onPreExecute()

        pd = ProgressDialog(c)
        pd.setTitle("Parse JSON")
        pd.setMessage("Parsing...Please wait")
        pd.show()
    }

    override fun onPostExecute(parsed: Boolean?) {
        super.onPostExecute(parsed)

        pd.dismiss()

        if (parsed!!){
            //BIND
            val adapter = CustomAdapter(assignments, c)
            rv.adapter = adapter
        }
        else{
            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseData(): Boolean{
        try {
            var ja: JSONArray = JSONArray(jsonData)
            var jo: JSONObject

            assignments.clear()
            var assignment: Assignment

            for (i in 0 until ja.length()){
                jo=ja.getJSONObject(i)

//                var stu_id=jo.getInt("stu_id")
                var stu_id=jo.getString("Fname")

                assignment = Assignment(stu_id)

                assignments.add(assignment)

            }
            return true
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return false

    }
}