package fragment


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.deknerdvariety.prayat.schedule.R
import adapter.TeachHeadingAdapter
import android.content.Intent
import android.util.Log
import com.deknerdvariety.prayat.schedule.MainActivity
import com.deknerdvariety.prayat.schedule.PromptRunnable
import com.deknerdvariety.prayat.schedule.timetable
import kotlinx.android.synthetic.main.teach_input_from.*
import kotlinx.android.synthetic.main.teach_input_from.view.*
import kotlinx.android.synthetic.main.teach_schedule_fragment.*
import kotlinx.android.synthetic.main.teach_schedule_fragment.view.*


class Teach_schedule_fragment : Fragment() {

    companion object {
        val course_ary: ArrayList<CourseSchedule> = ArrayList()
        val teachHeading:ArrayList<String> = ArrayList()
        fun addTeachSchedule(name: String) {
            teachHeading.add(name)
        }
    }

    var str_semester:String? = ""
    var str_idCourse:String? = ""
    var str_startTime:String? = ""
    var str_stopTime:String? = ""
    var str_startDate:String? = ""
    var str_stopDate:String? = ""

    lateinit var count_edt: EditText

    var changeAc = 0




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.teach_schedule_fragment, container, false)

//        teach_list.layoutManager = LinearLayoutManager(context)
//        teach_list.adapter = TeachHeadingAdapter(animals,requireContext())
        view.teach_list.layoutManager = LinearLayoutManager(context)
        view.teach_list.adapter = TeachHeadingAdapter(teachHeading, requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // add floating btn was cliked
        teach_btn.setOnClickListener {
            //            val intent = Intent(view.context,inputFrom::class.java)
//            startActivity(intent)
//            view.onFinishTemporaryDetach()

            //create custom dialog with alert_dialog.xml
            val view = layoutInflater.inflate(R.layout.alert_dialog, null)
            //show at activity context
            val builder = AlertDialog.Builder(context)
            //setting alert dialog
            builder.setView(view)
            builder.setTitle("add schdule")
            //set positive button
            builder.setPositiveButton("Create", { dialog: DialogInterface, i: Int -> })
            //set negative button
            builder.setNegativeButton("Cancel", { dialog: DialogInterface, i: Int -> })
            // create and show alert dialog
            val customDialog = builder.create()
            customDialog.show()

            val count_edt = view.findViewById<EditText>(R.id.count_edt)

            //when clik positive button of alert dialog
            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener { it ->
                val all_form_course = count_edt.text.toString().toInt()
                customDialog.dismiss()
                Toast.makeText(context, "$all_form_course", Toast.LENGTH_SHORT).show()
                for(i in 1..all_form_course){
                    input_dialog(all_form_course, PromptRunnable())
                }
                //update current form
            }

            //when clik negative button of alert dialog
            customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener { it ->
                Toast.makeText(context,"cancel create schedule",Toast.LENGTH_SHORT).show()
                customDialog.dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        if (changeAc != all_form) {
//            var intent = Intent(context, timetable::class.java)
//            startActivity(intent)
//        }
    }

    fun input_dialog(all_form_course:Int,posturn:PromptRunnable){

        val view = layoutInflater.inflate(R.layout.teach_input_from, null)
        //show at activity context
        val builder = AlertDialog.Builder(context)
        //setting alert dialog
        builder.setView(view)
        builder.setTitle("input Form")
        //set positive button
        builder.setPositiveButton("OK", { dialog: DialogInterface, i: Int -> })
        //set negative button
        builder.setNegativeButton("Cancel", { dialog: DialogInterface, i: Int -> })
        // create and show alert dialog
        val customDialog = builder.create()
        customDialog.show()

        //when clik positive button of alert dialog
        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener { it ->
            customDialog.dismiss()

            str_semester = view.et_semester.text?.toString()
            str_idCourse = view.et_idCourse.text?.toString()
            str_startTime = view.et_startTime.text?.toString()
            str_stopTime = view.et_stopTime.text?.toString()
            str_startDate = view.et_startDate.text?.toString()
            str_stopDate = view.et_stopDate.text?.toString()


            course_ary.add(CourseSchedule(str_semester, str_idCourse, str_startTime, str_stopTime, str_startDate, str_stopDate))

            //update current form

            changeAc++
            customDialog.dismiss()
            posturn.run(){
                if(changeAc == all_form_course){
                    val intent = Intent(context,timetable::class.java)
                    startActivity(intent)
                    changeAc = 0
                }
            }


        }

        //when clik negative button of alert dialog
        customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener { it ->
            Toast.makeText(context,"cancel create Form",Toast.LENGTH_SHORT).show()
            customDialog.dismiss()
        }

    }
}


class CourseSchedule(val semester:String?, val idCourse: String?, val startTime: String?, val stopTime: String?, val startDate: String?, val stopDate: String?) {}