package com.deknerdvariety.prayat.schedule


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.teach_input_from.*


class inputFrom : AppCompatActivity() {


    var all_form_course: Int = 0
    var current_from: Int = 1

    var str_semester = ""
    var str_idCourse = ""
    var str_startTime = ""
    var str_stopTime = ""
    var str_startDate = ""
    var str_stopDate = ""

    val course_ary: ArrayList<CourseSchedule> = ArrayList()

    var AlertDialogShow = -1 // first time show Alertdialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.teach_input_from)


//        //create custom dialog with alert_dialog.xml
//        val view = layoutInflater.inflate(R.layout.alert_dialog, null)
//        //show at activity context
//        val builder = AlertDialog.Builder(this)
//        //setting alert dialog
//        builder.setView(view)
//        builder.setTitle("add schdule")
//        //set positive button
//        builder.setPositiveButton("Create", { dialog: DialogInterface, i: Int -> })
//        //set negative button
//        builder.setNegativeButton("Cancel", { dialog: DialogInterface, i: Int -> })
//        // create and show alert dialog
//        val customDialog = builder.create()
//        customDialog.show()
//
//        val count_edt = view.findViewById<EditText>(R.id.count_edt)
//
//
//        //when clik positive button of alert dialog
//        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener { it ->
//            all_form_course = count_edt.text.toString().toInt()
//            customDialog.dismiss()
//            Toast.makeText(this, "$all_form_course", Toast.LENGTH_SHORT).show()
//            tv_countForm.setText("$current_from/$all_form_course")
//            //update current form
//
//        }
//
//        //when clik negative button of alert dialog
//        customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener { it ->
//            intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//
//        }
    }

    override fun onResume() {
        super.onResume()

//        ok_btn.setOnClickListener {
//
//            str_semester = et_semester.text.toString()
//            str_idCourse = et_idCourse.text.toString()
//            str_startTime = et_startTime.text.toString()
//            str_stopTime = et_stopTime.text.toString()
//            str_startDate = et_startDate.text.toString()
//            str_startDate = et_stopDate.text.toString()
//
//            current_from++
//            course_ary.add(CourseSchedule(str_semester, str_idCourse, str_startTime, str_stopTime, str_startDate, str_stopDate))
//
//
//            current_from++
//            intent = Intent(this, inputFrom::class.java)
//            intent.putExtra("current_form", current_from)
//            intent.putExtra("all_form", all_form_course)
//            startActivity(intent)
//
//        }
//
//        Toast.makeText(this, "current_form -> $current_from", Toast.LENGTH_SHORT).show()
    }

}

class CourseSchedule(val semester: String, val idCourse: String, val startTime: String, val stopTime: String, val startDate: String, val stopDate: String) {}