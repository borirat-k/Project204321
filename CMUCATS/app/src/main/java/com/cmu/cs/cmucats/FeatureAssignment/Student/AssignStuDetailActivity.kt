package com.cmu.cs.cmucats.FeatureAssignment.Student

import android.os.Bundle
import com.cmu.cs.cmucats.R
import com.klinker.android.sliding.MultiShrinkScroller
import com.klinker.android.sliding.SlidingActivity

private var stuID: String? = null

class AssignStuDetailActivity: SlidingActivity() {

    override fun init(savedInstanceState: Bundle?) {
        val bundle: Bundle = intent.extras
        stuID = bundle.getString("stuID")
        setTitle(stuID)
        setPrimaryColors(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark)
        )
        setContent(R.layout.content_assign_stu_detail)
    }

    override fun configureScroller(scroller: MultiShrinkScroller?) {
        super.configureScroller(scroller)
        scroller!!.intermediateHeaderHeightRatio = 0.7F
    }
}