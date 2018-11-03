package SendToServer

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.HashMap
import javax.net.ssl.HttpsURLConnection

class RequestHandlerKt {
    fun sendGetRequest(uri: String): String? {
        try {
            val url = URL(uri)
            val con = url.openConnection() as HttpURLConnection
            val bufferedReader = BufferedReader(InputStreamReader(con.inputStream))

            var result: String

            val sb = StringBuilder()

            result = bufferedReader.readLine()

            while (result != null) {
                sb.append(result)
            }

            return sb.toString()
        } catch (e: Exception) {
            return null
        }

    }

    fun sendPostRequest(requestURL: String,
                        postDataParams: HashMap<String, String>): String {

        val url: URL
        var response = ""
        try {
            url = URL(requestURL)

            val conn = url.openConnection() as HttpURLConnection
            conn.readTimeout = 15000
            conn.connectTimeout = 15000
            conn.requestMethod = "POST"
            conn.doInput = true
            conn.doOutput = true


            val os = conn.outputStream
            val writer = BufferedWriter(
                    OutputStreamWriter(os, "UTF-8"))
            writer.write(getPostDataString(postDataParams))

            writer.flush()
            writer.close()
            os.close()
            val responseCode = conn.responseCode

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                val br = BufferedReader(InputStreamReader(conn.inputStream))
                response = br.readLine()
            } else {
                response = "Error Registering"
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return response
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getPostDataString(params: HashMap<String, String>): String {
        val result = StringBuilder()
        var first = true
        for ((key, value) in params) {
            if (first)
                first = false
            else
                result.append("&")

            result.append(URLEncoder.encode(key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(value, "UTF-8"))
        }

        return result.toString()
    }
}