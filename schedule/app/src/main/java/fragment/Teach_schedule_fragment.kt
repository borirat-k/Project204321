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
import android.widget.AutoCompleteTextView
import com.deknerdvariety.prayat.schedule.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.teach_input_from.*
import kotlinx.android.synthetic.main.teach_schedule_fragment.*
import kotlinx.android.synthetic.main.teach_schedule_fragment.view.*


class Teach_schedule_fragment:Fragment() {

    companion object {
        val teachHeading: ArrayList<String> = ArrayList()
        fun addTeachSchedule(name: String) {
            teachHeading.add(name)
        }
    }

    lateinit var count_edt: EditText
    var count_add_course: Int = 0

    var str_semester = ""
    var str_idCourse = ""
    var str_timeteach = ""
    var str_dateteach = ""

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
        teach_btn.setOnClickListener { view ->
            //create custom dialog with alert_dialog.xml
            val view = layoutInflater.inflate(R.layout.alert_dialog, null)
            //show at activity context
            val builder = AlertDialog.Builder(this.context)
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

            count_edt = view.findViewById<EditText>(R.id.count_edt)

            //when clik positive button of alert dialog
            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener { it ->
                count_add_course = count_edt.text.toString().toInt()
                customDialog.dismiss()

                for (i in 1..count_add_course) {

                }
            }

            //when clik negative button of alert dialog
            customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener { it ->
                Toast.makeText(context, "Cancel create schedule of this teacher", Toast.LENGTH_SHORT).show()
                customDialog.dismiss()
            }

        }
    }


//    fun settingFormSchedule(builder:AlertDialog.Builder){
//        val view = layoutInflater.inflate(R.layout.teach_input_from,null)
//        val et_semester = view.findViewById<EditText>(R.id.et_semester)
//        val et_idCourse = view.findViewById<EditText>(R.id.et_idCourse)
//        val et_timeteach = view.findViewById<EditText>(R.id.et_timeteach)
//        val et_dateteach = view.findViewById<EditText>(R.id.et_dateTeach)
//
//        builder.setView(view)
//        builder.setTitle("Create schedule")
//
//        builder.setPositiveButton("OK",{dialog:DialogInterface,i:Int->})
//        builder.setNegativeButton("Cancel",{dialog:DialogInterface,i:Int->})
//
//        val customDialog = builder.create()
//        customDialog.show()
//
//        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
//            str_semester = et_semester.text.toString()
//            str_idCourse = et_idCourse.text.toString()
//            str_timeteach = et_timeteach.text.toString()
//            str_dateteach = et_dateteach.text.toString()
//
//            addTeachSchedule(str_semester)
//            customDialog.dismiss()
//
//            MainActivity()
//        }
//
//        customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
//            Toast.makeText(context,"cancel",Toast.LENGTH_SHORT).show()
//        }
//    }

}
