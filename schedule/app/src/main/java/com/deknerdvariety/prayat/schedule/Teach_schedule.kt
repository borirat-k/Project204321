package com.deknerdvariety.prayat.schedule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView



class Teach_schedule : Fragment() {
    var position = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.layout_teachs_chedule,container,false) as ViewGroup
        val greeting = rootView.findViewById(R.id.greeting) as TextView
        val bundle = arguments
        val linearLayout = rootView.findViewById(R.id.linear_layout) as LinearLayout
        position = bundle!!.getInt(PagerAdepterF.ARGS_POSITION);
        if(position == 0)
            greeting.setText("hello 1")
        else if(position == 1)
            greeting.setText("hello 2")


        return rootView

    }
}

