package com.cmu.cs.cmucats.FeatureSchedule.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.cmu.cs.cmucats.FeatureSchedule.ConnectPHP.*
import com.cmu.cs.cmucats.FeatureSchedule.timetable.timetable
import com.cmu.cs.cmucats.R
import kotlinx.android.synthetic.main.teach_input_from.*

class Teach_input_form_activity : AppCompatActivity() {
    var changeAc = 0
    val TAG_URI_PHP_SELECT_detail = "http://192.168.0.102/Project204321/selectdetailCourse.php"
    val TAG_URI_PHP_INSERT_Se = "http://192.168.0.102/Project204321/insert_SelectIn.php"
    val TAG_URI_SELECT_SUBJECT_DETAIL = "http://192.168.0.102/Project204321/selectSubjectDetail.php"

    companion object {
        var detailCourseList = ArrayList<String>()
        var index: Int = 0
    }

    var current_state: Int? = null
    var all_form_course: Int? = null
    var position_cid: String? = null
    var user_id = 3;
    var semester: String? = null

    var old_course_L = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teach_input_from)
        all_form_course = intent.getIntExtra("all_form_course", 0)
        current_state = intent.getIntExtra("current_state", 0)
        semester = intent.getStringExtra("semester")
        if (current_state!! > 0) {
            old_course_L = intent.getStringArrayListExtra("old_course")
        }
//        DownLoadSubject(this,TAG_URI_SELECT_SUBJECT_DETAIL,user_id,semester!!).execute()
        val dropdown = findViewById<Spinner>(R.id.et_idCourse)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Teach_schedule_fragment.idCourseList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdown.adapter = arrayAdapter
        dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                position_cid = Teach_schedule_fragment.idCourseList[position]
                index = position
                DownLoadSubjectDetail(view.context, TAG_URI_PHP_SELECT_detail, position_cid!!).execute()
            }

        }


    }


    override fun onStart() {
        super.onStart()


        reload_data.setOnClickListener {
            tv_startTime.text = ParserSubjectDetail.coursedetailL[index].startTime
            tv_stopTime.text = ParserSubjectDetail.coursedetailL[index].stopTime
            tv_firstDay.text = ParserSubjectDetail.coursedetailL[index].firstDay
            tv_secondDay.text = ParserSubjectDetail.coursedetailL[index].secondDay

        }



        ok_btn.setOnClickListener {
            Insert_SelectIn(this, TAG_URI_PHP_INSERT_Se, semester, position_cid!!, user_id).execute()
            old_course_L.add(position_cid!!)
            if (current_state!! < all_form_course!! - 1) {

                val intent = Intent(this, Teach_input_form_activity::class.java)
                intent.putExtra("all_form_course", all_form_course!!)
                current_state = current_state!! + 1
                intent.putExtra("all_form_course", current_state!!)
                intent.putExtra("semester", semester)
                intent.putExtra("old_course", old_course_L)
                startActivity(intent)
                var c = CourseScheduleInsert("", position_cid, ParserSubjectDetail.coursedetailL[index].startTime, ParserSubjectDetail.coursedetailL[index].stopTime, ParserSubjectDetail.coursedetailL[index].firstDay, ParserSubjectDetail.coursedetailL[index].secondDay)
                Teach_schedule_fragment.course_ary.add(c)
                finish()
                Teach_schedule_fragment.idCourseList.removeAt(index)
            } else {
                var c = CourseScheduleInsert("", position_cid, ParserSubjectDetail.coursedetailL[index].startTime, ParserSubjectDetail.coursedetailL[index].stopTime, ParserSubjectDetail.coursedetailL[index].firstDay, ParserSubjectDetail.coursedetailL[index].secondDay)
                Teach_schedule_fragment.course_ary.add(c)
                val intent = Intent(this, timetable::class.java)
                startActivity(intent)
                finish()
                Teach_schedule_fragment.idCourseList.clear()
            }
            ParserSubjectDetail.coursedetailL.clear()


        }
    }
}