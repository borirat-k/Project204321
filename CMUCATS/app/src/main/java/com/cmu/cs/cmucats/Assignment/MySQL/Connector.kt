package com.cmu.cs.cmucats.Assignment.MySQL

import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class Connector {
    fun connect (urlAddress: String): HttpURLConnection? {
        try {
            val url: URL = URL(urlAddress)
            val con: HttpURLConnection = url.openConnection() as HttpURLConnection

            //CON PROPS
            con.requestMethod = "GET"
            con.connectTimeout = 15000
            con.readTimeout = 15000
            con.doInput = true

            return con
        } catch (e: MalformedURLException){
            e.printStackTrace()
        } catch (e: IOException){
            e.printStackTrace()
        }

        return null
    }
}