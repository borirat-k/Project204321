package com.cmu.cs.cmucats.FeatureAssignment.Student

import android.os.Bundle
import com.cmu.cs.cmucats.FeatureAssignment.MySQL.SELECT.DownloaderAssignStuDetail
import com.cmu.cs.cmucats.R
import com.klinker.android.sliding.MultiShrinkScroller
import com.klinker.android.sliding.SlidingActivity

private var courseID: String? = null
private var assignmentID: String? = null
private var studentID: String? = null

class AssignStuDetailActivity: SlidingActivity() {

//    var urlAdress: String = "http://10.0.2.2/Project204321/select_assign_stu_detail.php"
    var urlAdress: String = "http://192.168.0.102/Project204321/select_assign_stu_detail.php"



    override fun init(savedInstanceState: Bundle?) {
        val bundle: Bundle = intent.extras
        courseID = bundle.getString("course")
        assignmentID = bundle.getString("assign")
        studentID = bundle.getString("studetail")
        setTitle(studentID)
        setPrimaryColors(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark)
        )
        setContent(R.layout.content_assign_stu_detail)
        DownloaderAssignStuDetail(this@AssignStuDetailActivity, urlAdress, courseID!!, assignmentID!!, studentID!!).execute()
    }

    override fun configureScroller(scroller: MultiShrinkScroller?) {
        super.configureScroller(scroller)
        scroller!!.intermediateHeaderHeightRatio = 0.7F
    }


}