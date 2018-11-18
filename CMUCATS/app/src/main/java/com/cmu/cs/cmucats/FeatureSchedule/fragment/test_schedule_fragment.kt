package com.cmu.cs.cmucats.FeatureSchedule.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmu.cs.cmucats.R
import kotlinx.android.synthetic.main.test_schedule_fragment.*


class test_schedule_fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.test_schedule_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        test_btn.setOnClickListener { view ->
            Snackbar.make(view, "add test", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
        }
    }
}