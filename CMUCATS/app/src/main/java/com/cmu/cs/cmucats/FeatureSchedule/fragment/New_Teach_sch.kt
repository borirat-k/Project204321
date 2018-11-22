//package com.cmu.cs.cmucats.FeatureSchedule.fragment
//
//import android.app.AlertDialog
//import android.content.DialogInterface
//import android.content.Intent
//import android.os.Bundle
//import android.support.v4.app.Fragment
//import android.support.v7.widget.LinearLayoutManager
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import com.cmu.cs.cmucats.FeatureSchedule.ConnectPHP.*
//import com.cmu.cs.cmucats.FeatureSchedule.adapter.TeachHeadingAdapter
//import com.cmu.cs.cmucats.FeatureSchedule.timetable.timetable
//import com.cmu.cs.cmucats.R
//import kotlinx.android.synthetic.main.alert_semester_form.*
//import kotlinx.android.synthetic.main.teach_input_from.view.*
//import kotlinx.android.synthetic.main.teach_schedule_fragment.*
//import kotlinx.android.synthetic.main.teach_schedule_fragment.view.*
//
//class New_Teach_sch : Fragment(),AdapterView.OnItemSelectedListener {
//    companion object {
//        val course_ary: ArrayList<CourseScheduleInsert> = ArrayList()
//        val semester_list = ArrayList<String>()
//        val countCourse_list = ArrayList<Int>()
//        var course_schedule_Head = ArrayList<HeadScedule>()
//        val teachHeading: ArrayList<String> = ArrayList()
//        fun addTeachSchedule(name: String) {
//            teachHeading.add(name)
//        }
//    }
//
//    var changeAc = 0
//    //    val TAG_URI_PHP_SELECT = "http://10.80.100.107/Project204321/selectSchedule.php"
////    val TAG_URI_PHP_INSERT_T = "http://10.80.100.107/Project204321/insertSchedule.php"
////    val TAG_URI_PHP_INSERT_Se = "http://10.80.100.107/Project204321/insert_SelectIn.php"
//    val TAG_URI_PHP_SELECT = "http://192.168.0.102/Project204321/selectSchedule.php"
//    val TAG_URI_PHP_INSERT_T = "http://192.168.0.102/Project204321/insertSchedule.php"
//    val TAG_URI_PHP_INSERT_Se = "http://192.168.0.102/Project204321/insert_SelectIn.php"
//    val TAG_URR_SELECT_SEMESTER_AND_COUNT_COURSE = "http://192.168.0.102/Project204321/select_semester_and_count_Course.php"
//    val user_id = 3
//
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.teach_schedule_fragment, container, false)
//
//        JsonDownloadSchedule(view.context, TAG_URI_PHP_SELECT, view.teach_list).execute()
//        DownloadSemesterCourse(view.context,TAG_URR_SELECT_SEMESTER_AND_COUNT_COURSE,user_id).execute()
//
//        view.teach_list.layoutManager = LinearLayoutManager(context)
//        view.teach_list.adapter = TeachHeadingAdapter(course_schedule_Head, view.context)
//
//        return view
//    }
//
//    override fun onViewCreated(view1: View, savedInstanceState: Bundle?) {
//
//        teach_btn.setOnClickListener {
//            //create and set up custom dialog with alert_dialog.xml
//            val view = layoutInflater.inflate(R.layout.alert_semester_form, null)
//            val builder = AlertDialog.Builder(context)
//            builder.setView(view)
//            builder.setTitle("add schdule")
//            builder.setPositiveButton("Create", { dialog: DialogInterface, i: Int -> })
//            builder.setNegativeButton("Cancel", { dialog: DialogInterface, i: Int -> })
//            val customDialog = builder.create()
//            customDialog.show()
//
//
//            //when clik positive button of alert dialog
//            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener { it ->
//
//                val dropdownSemester = view.findViewById<Spinner>(R.id.dropDown_semester)
//                val arrayAdapter = ArrayAdapter(view.context,android.R.layout.simple_spinner_item, semester_list)
//                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                dropdownSemester.adapter = arrayAdapter
//
//                dropdownSemester.onItemSelectedListener = this
//
//            }
//            //when clik negative button of alert dialog
//            customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener { it ->
//                Toast.makeText(context, "cancel create schedule", Toast.LENGTH_SHORT).show()
//                customDialog.dismiss()
//            }
//        }
//
//
//    }
//
//    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//            Toast.makeText(view.context,"select ${parent.getItemAtPosition(position).toString()} ",Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onNothingSelected(parent: AdapterView<*>?) {
//
//    }
//
//}
//
