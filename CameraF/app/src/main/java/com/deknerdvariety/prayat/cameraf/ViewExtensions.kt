package com.deknerdvariety.prayat.cameraf

import android.support.v7.widget.SwitchCompat
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.SeekBar


internal infix fun SwitchCompat.onCheckedChanged(function: (CompoundButton, Boolean) -> Unit) {
    setOnCheckedChangeListener(function)
}

internal infix fun View.onClick(function: () -> Unit) {
    setOnClickListener { function() }
}

internal infix fun SeekBar.onProgressChanged(zoomUpdated: () -> Unit) {
    setOnSeekBarChangeListener(object : OnProgressChanged() {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            zoomUpdated()
        }
    })
}


