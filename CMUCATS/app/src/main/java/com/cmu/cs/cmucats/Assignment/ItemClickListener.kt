package com.cmu.cs.cmucats.Assignment

import android.view.View

interface ItemClickListener {
    fun onClick(view: View, position: Int)
}