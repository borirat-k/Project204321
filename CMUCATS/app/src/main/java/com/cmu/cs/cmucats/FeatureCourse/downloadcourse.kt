package com.cmu.cs.cmucats.FeatureCourse

import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.roger.catloadinglibrary.CatLoadingView
import java.io.*
import java.net.HttpURLConnection
import java.net.URLEncoder

class downloadcourse(private var c: Context, private var urlAdress: String, private var rv: RecyclerView) : AsyncTask<Void, Void, String>() {

    var mView: CatLoadingView? = null

    override fun onPreExecute() {
        super.onPreExecute()

        mView = CatLoadingView()
        mView!!.show((c as FragmentActivity).supportFragmentManager, "")
    }

    override fun doInBackground(vararg p0: Void?): String? {
        println("passsssssssssDoInBackground")
        return downloadData()
    }

    override fun onPostExecute(jsonData: String?) {
        super.onPostExecute(jsonData)
        println("passssssssssPOSTTTTTTTTBeforee")

//        pd.dismiss()
        mView!!.dismiss()

        println("passssssssssPOSTTTTTTTTAfterrrr")
//        Toast.makeText(c, jsonData.toString(), Toast.LENGTH_SHORT).show()

        if (jsonData == null) {
            Toast.makeText(c, "Unsuccessful,No data retrieved", Toast.LENGTH_SHORT).show()
        }
        else {
            //PARSE
            parsercourse(c, jsonData, rv).execute()
        }
    }

    private fun downloadData(): String? {
        val con: HttpURLConnection? = Connector().connect(urlAdress)
        if (con == null) {
            return null
        }

        try {
//            println(courseID)
            val ops: OutputStream = con.outputStream
            val writer: BufferedWriter = BufferedWriter(OutputStreamWriter(ops, "UTF-8"))
            var data: String = URLEncoder.encode("Tid", "UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")
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
            } else {
                return "Error" + con.responseMessage
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}