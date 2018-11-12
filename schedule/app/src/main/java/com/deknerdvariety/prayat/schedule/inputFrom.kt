package com.deknerdvariety.prayat.schedule


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class inputFrom :AppCompatActivity(){

    lateinit var et_semester:EditText
    lateinit var et_idCourse:EditText
    lateinit var et_starttime:EditText
    lateinit var et_stoptime:EditText
    lateinit var et_startDate:EditText
    lateinit var et_stopDate:EditText
    lateinit var btn_ok: Button

    lateinit var str_semester:String
    lateinit var str_idCourse:String
    lateinit var str_startTime:String
    lateinit var str_stopTime:String
    lateinit var str_startDate:String
    lateinit var str_stopDate:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teach_input_from)
        bindData()

        btn_ok.setOnClickListener {
            var intent = Intent(this,timetable::class.java)

            intent.putExtra("semester",str_semester)
            intent.putExtra("idCourse",str_idCourse)
            intent.putExtra("startTime",str_startTime)
            intent.putExtra("stopTime",str_stopTime)
            intent.putExtra("startDate",str_startDate)
            intent.putExtra("stopDate",str_stopDate)
            startActivity(intent)
            finish()
        }


    }

    fun bindData(){
        et_semester = findViewById(R.id.et_semester)
        et_idCourse = findViewById(R.id.et_idCourse)
        et_starttime = findViewById(R.id.et_startTime)
        et_stoptime = findViewById(R.id.et_stopTime)
        et_startDate = findViewById(R.id.et_startDate)
        et_stopDate = findViewById(R.id.et_stopDate)
        btn_ok = findViewById(R.id.ok_btn)

        str_semester = et_semester.text.toString()
        str_idCourse = et_idCourse.text.toString()
        str_startTime = et_starttime.text.toString()
        str_stopTime = et_stoptime.text.toString()
        str_startDate = et_startDate.text.toString()
        str_stopDate = et_stopDate.text.toString()
    }
}