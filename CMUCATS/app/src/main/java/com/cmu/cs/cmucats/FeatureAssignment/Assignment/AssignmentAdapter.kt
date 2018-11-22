package com.cmu.cs.cmucats.FeatureAssignment.Assignment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.cmu.cs.cmucats.R
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.cmu.cs.cmucats.FeatureAssignment.MySQL.DELETE.DeleteAssignment
import com.cmu.cs.cmucats.FeatureAssignment.MySQL.INSERT_UPDATE.EditShowAssignment
import com.cmu.cs.cmucats.FeatureAssignment.MySQL.INSERT_UPDATE.EditUpdateAssignment
import com.cmu.cs.cmucats.FeatureAssignment.Student.AssignmentStudentActivity
import com.rengwuxian.materialedittext.MaterialEditText
import com.rengwuxian.materialedittext.validation.RegexpValidator
import nectec.thai.widget.date.DatePicker
import java.util.Calendar


class AssignmentAdapter(val assignList: ArrayList<Assignment>, context: Context, private var courseID: String, private var teacherID: String): RecyclerView.Adapter<AssignmentAdapter.ViewHolder>(), PopupMenu.OnMenuItemClickListener {

    private var mContext: Context = context
    private var activity = mContext as Activity

    val TAG_ASSIGN_STU_FRAGMENT = "tag_assign_stu_fragment"
//    val deleteAdress: String = "http://10.0.2.2/Project204321/delete_assignment.php"
//    val editShowAdress: String = "http://10.0.2.2/Project204321/update_show_assignment.php"
//    val editEditAdress: String = "http://10.0.2.2/Project204321/update_edit_assignment.php"
    val deleteAdress: String = "http://192.168.1.110/Project204321/delete_assignment.php"
    val editShowAdress: String = "http://192.168.1.110/Project204321/update_show_assignment.php"
    val editEditAdress: String = "http://192.168.1.110/Project204321/update_edit_assignment.php"

    private var assignmentID: String? = null

//    val datePickerCallback = object : DateView.DatePickerCallback {
//        override fun onPicked(view: DateView, calendar: Calendar) {
//            val button = activity.findViewById(R.id.spinner_date) as Button
//            button.setText(DatePrinter.print(calendar))
//        }
//
//        override fun onCancel() {
//            if (BuildConfig.DEBUG) Toast.makeText(activity, "onCancle", Toast.LENGTH_SHORT).show()
//        }
//    }

    //มีหน้าที่เพื่อให้เราสร้าง view ต่างๆแล้วเก็บไว้ใน ViewHolder อีกที
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_assign, parent, false)
        return ViewHolder(v)
    }

    //มีหน้าที่บอกว่าเรามีข้อมูลที่ต้องการจะแสดงผลทั้งหมดกี่ชุด
    override fun getItemCount(): Int {
        return assignList.size
    }

    //มีหน้าที่จัดการข้อมูลที่ใส่เข้าไป เพื่อนำไปแสดงผล บน view ตามที่ต้องการ
    //เช่น ใส่ค่า String เข้าไป เพื่อนำไปแสดงผลบน textView หรือตอนที่มีการ scroll ข้อมูลใหม่เข้ามาแสดงผลบนหน้าจอ
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assignment: Assignment = assignList[position]
        holder.textAssignID.text = assignment.id

        //set Click option menu
//        holder.textCourseOption
        holder.textAssignOption.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View) {
                assignmentID = holder.textAssignID.text.toString()
                showPopup(v)
            }
        })

        holder.assignment_layout.setOnClickListener(object :View.OnClickListener {
            override fun onClick(p0: View?) {
                Toast.makeText(mContext, "onClick: clicked on: ", Toast.LENGTH_SHORT).show()

                val intent = Intent(mContext, AssignmentStudentActivity::class.java)
                intent.putExtra("course", courseID)
                intent.putExtra("assign", holder.textAssignID.text)
                intent.putExtra("teacher", teacherID)
                mContext.startActivity(intent)
                activity.finish()
                activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                //********************************หาตั้งนานกว่าจะเปลี่ยน fragment*******************
//                val activity = mContext as AppCompatActivity
//                activity.supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.content_frame, AssignmentStudentActivity(), TAG_ASSIGN_STU_FRAGMENT)
//                        .addToBackStack(null)
//                        .commit()
                //************************************************************************
            }
        })
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){//, View.OnClickListener{

        val textAssignID = itemView.findViewById(R.id.textAssignID) as TextView
        val assignment_layout = itemView.findViewById(R.id.assignment_layout) as ConstraintLayout
        val textAssignOption = itemView.findViewById(R.id.textAssignOption) as TextView
//        var itemClickListener: ItemClickListener? = null
//
//        init{
//            itemView.setOnClickListener(this)
//        }
//
//        fun setOnClickListener(itemClickListener: ItemClickListener){
//            this.itemClickListener = itemClickListener
//        }
//
//
//        override fun onClick(view: View?) {
//            itemClickListener!!.onClick(view!!, adapterPosition)
//        }
    }

    fun showPopup(v: View){
        var popup: PopupMenu = PopupMenu(mContext, v)
        popup.setOnMenuItemClickListener(this)
        popup.inflate(R.menu.popup_menu)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.update -> {
                var check_add_assignment: Boolean = true
                Toast.makeText(mContext, "Update clicked", Toast.LENGTH_SHORT).show()
                val dialog = MaterialDialog(mContext).show {
                    title(text = "Add Assignment")
                    customView(R.layout.edit_add_assignment)
                    positiveButton(R.string.submit){dialog ->
                        val customView = dialog.getCustomView()!!
                        val assignment_name: MaterialEditText = customView.findViewById(R.id.edit_assignment_name)
                        val assignment_max_score: MaterialEditText = customView.findViewById(R.id.edit_assignment_max_score)
                        val datePicker: DatePicker = customView.findViewById(R.id.date_picker) as DatePicker

//                        println(datePicker.year.toString() + datePicker.month.toString() + datePicker.dayOfMonth.toString())
                        val nowToday: Calendar = Calendar.getInstance()
                        if (assignment_name.text.toString().trim().isEmpty()) {
                            assignment_name.error = mContext.getString(R.string.required)
                            check_add_assignment = false
                        }
                        if (!assignment_max_score.validateWith(RegexpValidator(mContext.getString(R.string.required), "\\d+"))) {
                            check_add_assignment = false
                        }
//                        if (datePicker.year < nowToday.get(Calendar.YEAR) ||
//                                datePicker.month < nowToday.get(Calendar.MONTH) ||
//                                datePicker.dayOfMonth < nowToday.get(Calendar.DATE)){
                        if (validDate(datePicker, nowToday)){
                            check_add_assignment = false
                            datePicker.error = "กรุณาระบุวันที่ให้ถูกต้อง"
                            Toast.makeText(dialog.context, "กรุณาระบุวันที่ให้ถูกต้อง", Toast.LENGTH_SHORT).show()
                        }
                        if (check_add_assignment){
                            var assignmentID_new: String = assignment_name.text.toString()
                            var startDate: String = nowToday.get(Calendar.YEAR).toString() + "-"
                            startDate += nowToday.get(Calendar.MONTH).toString() + "-"
                            startDate += nowToday.get(Calendar.DATE).toString()
                            var deadLine: String = datePicker.year.toString() + "-"
                            deadLine += (datePicker.month + 1).toString() + "-"
                            deadLine += datePicker.dayOfMonth.toString()
                            var maxScore: String = assignment_max_score.text.toString()

                            EditUpdateAssignment(customView.context, editEditAdress, courseID, assignmentID!!, assignmentID_new, deadLine, maxScore).execute()
                            val intent = Intent(mContext, AssignmentActivity::class.java)
                            intent.putExtra("course", courseID)
                            intent.putExtra("teacher", teacherID)
                            mContext.startActivity(intent)
                            activity.finish()
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
//                datePicker.updateDate(2019,0,17)
                datePicker.setOnDateChangedListener {
                    datePicker.error = null
                }
//                var edit_assignment_name = dialog.findViewById(R.id.edit_assignment_name) as MaterialEditText
                EditShowAssignment(dialog.getCustomView()!!.context, editShowAdress, dialog, courseID, assignmentID!!).execute()



//                var ee = dialog.findViewById(R.id.edit_assignment_name) as EditText
//                ee.setText("LECCCC1")
//                dialog.show()
//                val prin = ee.text
//                val datePicker = this.activity.findViewById(R.id.date_picker) as DatePicker
//                datePicker.setPopupTitle("ระบุ วัน/เดือน/ปี เกิด")
//                datePicker.setUndefinedAsDefault()
                return true
            }
            R.id.delete -> {
                DeleteAssignment(mContext, deleteAdress, courseID, assignmentID!!).execute()
                val intent = Intent(mContext, AssignmentActivity::class.java)
                intent.putExtra("course", courseID)
                intent.putExtra("teacher", teacherID)
                mContext.startActivity(intent)
                activity.finish()
//                Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return false
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
}