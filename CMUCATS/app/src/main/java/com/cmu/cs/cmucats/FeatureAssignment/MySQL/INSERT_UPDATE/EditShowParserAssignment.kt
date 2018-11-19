package com.cmu.cs.cmucats.FeatureAssignment.MySQL.INSERT_UPDATE

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.cmu.cs.cmucats.R
import com.rengwuxian.materialedittext.MaterialEditText
import com.roger.catloadinglibrary.CatLoadingView
import nectec.thai.widget.date.DatePicker
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class EditShowParserAssignment (private var c: Context, private var jsonData: String, private var dialog: MaterialDialog,
                                private var courseID: String, private var assignmentID: String): AsyncTask<Void, Void, Boolean>() {

    var mView: CatLoadingView? = null
    var assignment_name: MaterialEditText? = null
    var assignment_max_score: MaterialEditText? = null
    var datePicker: DatePicker? = null
    lateinit var assignID: String
    lateinit var maxScore: String
    lateinit var deadLine: String

    override fun onPreExecute() {
        super.onPreExecute()

        mView = CatLoadingView()
        mView!!.show((c as FragmentActivity).supportFragmentManager, "")

        assignment_name = dialog.findViewById(R.id.edit_assignment_name)
        assignment_max_score = dialog.findViewById(R.id.edit_assignment_max_score)
        datePicker = dialog.findViewById(R.id.date_picker)
    }

    override fun doInBackground(vararg params: Void?): Boolean {
        return parseData()
    }

    override fun onPostExecute(parsed: Boolean?) {
        super.onPostExecute(parsed)

        mView!!.dismiss()

        if (parsed!!){
            assignment_name!!.setText(assignID)
            assignment_max_score!!.setText(maxScore)
            var deadLineSplit = deadLine.split("-")
            var deadLine_year = deadLineSplit[0].toInt()
            var deadLine_month = deadLineSplit[1].toInt()
            var deadLine_day = deadLineSplit[2].toInt()
            datePicker!!.updateDate(deadLine_year, deadLine_month - 1, deadLine_day)
        }
        else{
            Toast.makeText(c, "กรุณาเชื่อมต่ออินเทอร์เน็ต", Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseData(): Boolean{
        try {
            var ja: JSONArray = JSONArray(jsonData)
            var jo: JSONObject? = null

            for (i in 0 until ja.length()){
                jo=ja.getJSONObject(i)

                var Aid = jo.getString("Aid")
                assignID = Aid

                var max_score = jo.getString("max_score")
                maxScore = max_score

                var Dead_line = jo.getString("Dead_line")
                deadLine = Dead_line

            }
            return true
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return false

    }
}