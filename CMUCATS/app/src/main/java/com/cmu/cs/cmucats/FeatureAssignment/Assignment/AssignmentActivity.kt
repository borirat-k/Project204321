package com.cmu.cs.cmucats.FeatureAssignment.Assignment

import `in`.srain.cube.views.ptr.PtrClassicFrameLayout
import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.cmu.cs.cmucats.FeatureAssignment.MySQL.SELECT.DownloaderAssignment
import com.cmu.cs.cmucats.FeatureActivity
import com.cmu.cs.cmucats.FeatureAssignment.MySQL.INSERT_UPDATE.InsertAssignment
import com.cmu.cs.cmucats.NavigationActivity
import com.cmu.cs.cmucats.R
import com.rengwuxian.materialedittext.MaterialEditText
import com.rengwuxian.materialedittext.validation.RegexpValidator
import kotlinx.android.synthetic.main.content_assignment.*
import nectec.thai.widget.date.DatePicker
import java.util.*


private var courseID: String? = null
private var teacherID: String? = null

class AssignmentActivity : NavigationActivity(){

    val TAG_ASSIGNMENT_FRAGMENT = "tag_assignment_fragment"

    val FLAG_ASSIGNMENT = "assign"
    var check_add_assignment: Boolean = true

//    var urlAdress: String = "http://10.0.2.2/Project204321/select_assignment.php"
    var urlAdress: String = "http://192.168.1.110/Project204321/select_assignment.php"
//    var insertAdress: String = "http://10.0.2.2/Project204321/insert_assignment.php"
    var insertAdress: String = "http://192.168.1.110/Project204321/insert_assignment.php"

//    val datePickerCallback = object : DateView.DatePickerCallback {
//        override fun onPicked(view: DateView, calendar: Calendar) {
//            val button = findViewById(R.id.spinner_date) as Button
//            button.setText(DatePrinter.print(calendar))
//        }
//
//        override fun onCancel() {
//            if (BuildConfig.DEBUG) Toast.makeText(AssignmentActivity(), "onCancle", Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_assignment)
        val bundle: Bundle = intent.extras
        courseID = bundle.getString("course")!!
        teacherID = bundle.getString("teacher")!!

        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(R.layout.activity_assignment, contentFrameLayout)

//        val contentAssignment: FrameLayout = findViewById(R.id.content_assignment)
        val contentAssignment: FrameLayout = findViewById(R.id.content_assignment)
        layoutInflater.inflate(R.layout.content_assignment, contentAssignment)

        val recyclerView = findViewById(R.id.recycleView_assign) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        add_assignment.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            var optionAssign: String = "insert"
            val dialog = MaterialDialog(this).show {
                title(text = "Add Assignment")
                customView(R.layout.edit_add_assignment)
                positiveButton(R.string.submit){dialog ->
                    val customView = dialog.getCustomView()!!
                    val assignment_name: MaterialEditText = customView.findViewById(R.id.edit_assignment_name)
                    val assignment_max_score: MaterialEditText = customView.findViewById(R.id.edit_assignment_max_score)
                    val datePicker: DatePicker = customView.findViewById(R.id.date_picker) as DatePicker

                    println(datePicker.year.toString() + datePicker.month.toString() + datePicker.dayOfMonth.toString())
                    val nowToday: Calendar = Calendar.getInstance()
                    println("TODAYYYYYYYYY")
                    println(nowToday.get(Calendar.YEAR))
                    println(nowToday.get(Calendar.MONTH))
                    println(nowToday.get(Calendar.DATE))
//                        assignment_name.validateWith(RegexpValidator("Only Integer Valid!", "\\d+"))
                    if (assignment_name.text.toString().trim().isEmpty()) {
                        assignment_name.error = getString(R.string.required)
                        check_add_assignment = false
                    }
                    if (!assignment_max_score.validateWith(RegexpValidator(getString(R.string.required), "\\d+"))) {
                        check_add_assignment = false
                    }
//                    if (datePicker.year < nowToday.get(Calendar.YEAR) ||
//                            datePicker.month < nowToday.get(Calendar.MONTH) ||
//                            datePicker.dayOfMonth < nowToday.get(Calendar.DATE)){
                    if (validDate(datePicker, nowToday)){
                        check_add_assignment = false
                        datePicker.error = "กรุณาระบุวันที่ให้ถูกต้อง"
                        Toast.makeText(dialog.context, "กรุณาระบุวันที่ให้ถูกต้อง", Toast.LENGTH_SHORT).show()
                    }
                    if (check_add_assignment){
                        var assignmentID: String = assignment_name.text.toString()
                        var startDate: String = nowToday.get(Calendar.YEAR).toString() + "-"
                        startDate += (nowToday.get(Calendar.MONTH) + 1).toString() + "-"
                        startDate += nowToday.get(Calendar.DATE).toString()
                        var deadLine: String = datePicker.year.toString() + "-"
                        deadLine += (datePicker.month + 1).toString() + "-"
                        deadLine += datePicker.dayOfMonth.toString()
//                        println("MONTH = " + (datePicker.month + 1).toString())
//                        println("DEADLINE =" + deadLine)
                        var maxScore: String = assignment_max_score.text.toString()
//                        startDate += if (datePicker.month < 10) "0" + datePicker.month.toString() else datePicker.month.toString()
//                        startDate += if (datePicker.dayOfMonth < 10) "-0" + datePicker.dayOfMonth.toString()
                        InsertAssignment(this@AssignmentActivity, insertAdress, courseID!!, assignmentID, startDate, deadLine, maxScore).execute()
                        DownloaderAssignment(this@AssignmentActivity, urlAdress, recyclerView, courseID!!, FLAG_ASSIGNMENT, "", teacherID!!).execute()
                        dialog.dismiss()
                    }
                    else{
                        dialog.noAutoDismiss()
                    }
                    check_add_assignment = true
                }
//                val dateDialog: DatePickerDialog = DatePickerDialog(AssignmentActivity())
//                dateDialog.setMinDateIsToday()
                negativeButton(R.string.cancel){
                    dismiss()
                }
            }
            val datePicker: DatePicker = dialog.findViewById(R.id.date_picker) as DatePicker
            datePicker.setPopupTitle("ระบุ วัน/เดือน/ปี")
            datePicker.setOnDateChangedListener {
                datePicker.error = null
//                Toast.makeText(dialog.context, "กรุณาระบุวันที่ให้ถูกต้อง", Toast.LENGTH_SHORT).show()
            }
        }

        DownloaderAssignment(this@AssignmentActivity, urlAdress, recyclerView, courseID!!, FLAG_ASSIGNMENT, "", teacherID!!).execute()

        val ptrFrame : PtrClassicFrameLayout = findViewById(R.id.ptrLoading)
        ptrFrame.setLastUpdateTimeRelateObject(this)
        ptrFrame.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout) {
                frame.postDelayed({ ptrFrame.refreshComplete() }, 1800)
                DownloaderAssignment(this@AssignmentActivity, urlAdress, recyclerView, courseID!!, FLAG_ASSIGNMENT, "", teacherID!!).execute()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header)
            }
        })

        // the following are default settings
        ptrFrame.setResistance(1.7f);
        ptrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        ptrFrame.setDurationToClose(200);
        ptrFrame.setDurationToCloseHeader(1000);
        // default is false
        ptrFrame.setPullToRefresh(false);
        // default is true
        ptrFrame.setKeepHeaderWhenRefresh(true);

        // scroll then refresh
        // comment in base fragment
        ptrFrame.postDelayed({
            // ptrFrame.autoRefresh();
        }, 150)

//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.content_frame, AssignmentFragment(), TAG_ASSIGNMENT_FRAGMENT)
//                .commit()
    }

    fun validDate(datePicker: DatePicker, nowToday: Calendar): Boolean{
        if (datePicker.year < nowToday.get(Calendar.YEAR)){
            return true
        }
        else if (datePicker.month < nowToday.get(Calendar.MONTH)){
            if (datePicker.year <= nowToday.get(Calendar.YEAR)){
                return true
            }
        }
        else if (datePicker.dayOfMonth < nowToday.get(Calendar.DATE)){
            if (datePicker.month <= nowToday.get(Calendar.MONTH)){
                if (datePicker.year <= nowToday.get(Calendar.YEAR)){
                    return true
                }
            }
        }
        return false
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        finish()
//        return super.onNavigationItemSelected(item)
//    }

    override fun onBackPressed() {
//        if (supportFragmentManager.findFragmentByTag(TAG_ASSIGNMENT_FRAGMENT)) {
            val intent = Intent(this, FeatureActivity::class.java)
            intent.putExtra("course", courseID)
            intent.putExtra("teacher", teacherID)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
//        }
//        else{
//            supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.content_frame, AssignmentFragment(), TAG_ASSIGNMENT_FRAGMENT)
//                    .commit()
//        }
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.setTitle(courseID)
        supportActionBar?.setSubtitle("Assignment")
    }

}

class AssignmentFragment : Fragment(){

    val FLAG_ASSIGNMENT = "assign"

    var urlAdress: String = "http://10.0.2.2/Project204321/select_assignment.php"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.content_assignment, container, false)

        val recyclerView = view.findViewById(R.id.recycleView_assign) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)

        val fab = view.findViewById(R.id.add_assignment) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        DownloaderAssignment(view.context, urlAdress, recyclerView, courseID!!, FLAG_ASSIGNMENT, "", teacherID!!).execute()
        return view
    }

    override fun onResume() {
        super.onResume()
        activity?.setTitle("Assignment")
    }
}
