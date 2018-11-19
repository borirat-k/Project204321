package com.cmu.cs.cmucats.FeatureAssignment.MySQL.INSERT_UPDATE

import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.cmu.cs.cmucats.FeatureAssignment.MySQL.Connector
import com.roger.catloadinglibrary.CatLoadingView
import java.io.*
import java.net.HttpURLConnection
import java.net.URLEncoder

class EditShowAssignment (private var c: Context, private var urlAdress: String, private var dialog: MaterialDialog,
                          private var courseID: String, private var assignmentID: String): AsyncTask<Void, Void, String>(){

    var mView: CatLoadingView? = null

    override fun onPreExecute() {
        super.onPreExecute()

        mView = CatLoadingView()
        mView!!.show((c as FragmentActivity).supportFragmentManager, "")
    }

    override fun doInBackground(vararg params: Void?): String? {
        return downloadData()
    }

    override fun onPostExecute(jsonData: String?) {
        super.onPostExecute(jsonData)

        mView!!.dismiss()

        if(jsonData==null){
            Toast.makeText(c, "Unsuccessful,No data retrieved", Toast.LENGTH_SHORT).show()
        }
        else{
            //PARSE
            EditShowParserAssignment(c, jsonData, dialog, courseID, assignmentID).execute()
        }
    }

    private fun downloadData(): String? {
        val con: HttpURLConnection? = Connector().connect(urlAdress)
        if(con==null){
            return null
        }

        try {
            val ops: OutputStream = con.outputStream
            val writer: BufferedWriter = BufferedWriter(OutputStreamWriter(ops, "UTF-8"))
            var data: String = URLEncoder.encode("Cid", "UTF-8") + "=" + URLEncoder.encode(courseID, "UTF-8")
            data += "&" + URLEncoder.encode("Aid", "UTF-8") + "=" + URLEncoder.encode(assignmentID, "UTF-8")
            writer.write(data)

            writer.flush()

            writer.close()
            ops.close()
            if (con.responseCode == 200) {
                var inputstream: InputStream = BufferedInputStream(con.inputStream)
                var br: BufferedReader = BufferedReader(InputStreamReader(inputstream))

                var line: String?
                var jsonData: StringBuffer = StringBuffer()

                do {
                    line = br.readLine()

                    if (line == null) {
                        break
                    }

                    jsonData.append(line + "\n")
                } while (true)

                br.close()
                inputstream.close()

                println("JSONNNNNNNN = " + jsonData.toString())
                return jsonData.toString()
            }
            else{
                return "Error" + con.responseMessage
            }
        } catch (e: IOException){
            e.printStackTrace()
        }
        return null
    }
}