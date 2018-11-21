package com.cmu.cs.cmucats.FeatureCourse

import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.cmu.cs.cmucats.FeatureAssignment.MySQL.Connector
import com.roger.catloadinglibrary.CatLoadingView
import java.io.*
import java.net.HttpURLConnection
import java.net.URLEncoder

class DeleteCourse(private var c: Context, private var urlAdress: String,
                   private var courseID: String): AsyncTask<Void, Void, Boolean>() {

    var mView: CatLoadingView? = null

    override fun onPreExecute() {
        super.onPreExecute()

        mView = CatLoadingView()
        mView!!.show((c as FragmentActivity).supportFragmentManager, "")
        println(courseID)
    }

    override fun doInBackground(vararg p0: Void?): Boolean? {
        return downloadData()
    }

    override fun onPostExecute(status: Boolean?) {
        super.onPostExecute(status)

        mView!!.dismiss()

        if(status!!){
            Toast.makeText(c, "Delete successful", Toast.LENGTH_SHORT).show()
        }
        else{
            //PARSE
            Toast.makeText(c, "Unsuccessful", Toast.LENGTH_SHORT).show()
        }
    }

    private fun downloadData(): Boolean? {
        val con: HttpURLConnection? = Connector().connect(urlAdress)
        if(con==null){
            return null
        }

        try {
            val ops: OutputStream = con.outputStream
            val writer: BufferedWriter = BufferedWriter(OutputStreamWriter(ops, "UTF-8"))
            var data: String = URLEncoder.encode("Cid", "UTF-8") + "=" + URLEncoder.encode(courseID, "UTF-8")
            writer.write(data)

            writer.flush()

            writer.close()
            ops.close()
            if (con.responseCode == 200) {
                var inputstream: InputStream = BufferedInputStream(con.inputStream)
                inputstream.close()
                return true
            }
            else{
                return false
            }
            return true
        } catch (e: IOException){
            e.printStackTrace()
        }
        return false
    }
}