package com.cmu.cs.cmucats.FeatureSchedule.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.widget.*
import com.cmu.cs.cmucats.FeatureSchedule.ConnectPHP.*
import com.cmu.cs.cmucats.FeatureSchedule.adapter.TeachHeadingAdapter
import com.cmu.cs.cmucats.R
import kotlinx.android.synthetic.main.teach_input_from.view.*
import kotlinx.android.synthetic.main.teach_schedule_fragment.*
import kotlinx.android.synthetic.main.teach_schedule_fragment.view.*
import com.cmu.cs.cmucats.FeatureSchedule.timetable.timetable
import kotlinx.android.synthetic.main.alert_dialog.*
import kotlinx.android.synthetic.main.alert_semester_form.view.*


class Teach_schedule_fragment : Fragment(){

    companion object {
        val semester_list = ArrayList<String>()
        val countCourse_list = ArrayList<Int>()
        val course_ary: ArrayList<CourseScheduleInsert> = ArrayList()
        var course_schedule_Head = ArrayList<HeadScedule>()
        val teachHeading: ArrayList<String> = ArrayList()
        val idCourseList = ArrayList<String>()
        fun addTeachSchedule(name: String) {
            teachHeading.add(name)
        }
    }

    //var str_semester: String? = ""
    var str_idCourse: String? = ""
    var str_startTime: String? = ""
    var str_stopTime: String? = ""
    var str_startDate: String? = ""
    var str_stopDate: String? = ""
    lateinit var semester:String

    var user_id = 3

    var check_semester = -1

    var check_subject = 1


    var all_form_course = 0
    val old_course = ArrayList<String>()

    var changeAc = 0
//    val TAG_URI_PHP_SELECT = "http://10.80.100.107/Project204321/selectSchedule.php"
//    val TAG_URI_PHP_INSERT_T = "http://10.80.100.107/Project204321/insertSchedule.php"
//    val TAG_URI_PHP_INSERT_Se = "http://10.80.100.107/Project204321/insert_SelectIn.php"
    val TAG_URI_PHP_SELECT = "http://192.168.0.102/Project204321/selectSchedule.php"
    val TAG_URI_PHP_INSERT_T = "http://192.168.0.102/Project204321/insertSchedule.php"
    val TAG_URI_SELECT_SUBJECT_DETAIL ="http://192.168.0.102/Project204321/selectSubjectDetail.php"
    val TAG_URR_SELECT_SEMESTER_AND_COUNT_COURSE = "http://192.168.0.102/Project204321/select_semester_and_count_Course.php"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.teach_schedule_fragment, container, false)
        semester_list.clear()
        DownloadSemesterCourse(view.context,TAG_URR_SELECT_SEMESTER_AND_COUNT_COURSE,user_id).execute()
        JsonDownloadSchedule(view.context, TAG_URI_PHP_SELECT, view.teach_list).execute()
        view.teach_list.layoutManager = LinearLayoutManager(context)
        view.teach_list.adapter = TeachHeadingAdapter(course_schedule_Head,view.context)

        return view
    }
    override fun onViewCreated(view1: View, savedInstanceState: Bundle?) {
        // add floating btn was cliked
        val current_state = 0
        teach_btn.setOnClickListener {
            //create custom dialog with alert_dialog.xml
            val view = layoutInflater.inflate(R.layout.alert_semester_form, null)

            val builder = AlertDialog.Builder(context)
            builder.setView(view)
            builder.setTitle("add schdule")
            builder.setPositiveButton("Create", { dialog: DialogInterface, i: Int -> })
            builder.setNegativeButton("Cancel", { dialog: DialogInterface, i: Int -> })


            val dropdown = view.findViewById<Spinner>(R.id.dropDown_semester)
            val arrayAdapter = ArrayAdapter(context,android.R.layout.simple_spinner_item, Teach_schedule_fragment.semester_list)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dropdown.adapter = arrayAdapter
            dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(view.context,"select ${parent.getItemAtPosition(position).toString()} ",Toast.LENGTH_SHORT).show()
                    all_form_course = countCourse_list[position]
                    semester = semester_list[position]
                }

            }

            val customDialog = builder.create()
            customDialog.show()

            //when clik positive button of alert dialog
            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener { it ->
                Insert_Tschedule(view.context,TAG_URI_PHP_INSERT_T,user_id,semester).execute()
                DownLoadSubject(view.context,TAG_URI_SELECT_SUBJECT_DETAIL,user_id,semester).execute()
                customDialog.dismiss()

                val intent = Intent(context,Teach_input_form_activity::class.java)
                intent.putExtra("all_form_course",all_form_course)
                intent.putExtra("current_state",current_state)
                intent.putExtra("semester",semester)


                startActivity(intent)

            }
            //when clik negative button of alert dialog
            customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener { it ->
                Toast.makeText(context, "cancel create schedule", Toast.LENGTH_SHORT).show()
                customDialog.dismiss()
            }
        }
    }


}

class CourseScheduleInsert(val semester: String?, val idCourse: String?, val startTime: String?, val stopTime: String?, val startDate: String?, val stopDate: String?) {}
