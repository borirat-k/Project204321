package com.deknerdvariety.prayat.schedule

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fragment.Teach_schedule_fragment
import kotlinx.android.synthetic.main.timetable_layout.*
import android.R.attr.y
import android.R.attr.x
import android.graphics.Point
import android.view.Display
import android.view.WindowManager
import com.deknerdvariety.prayat.schedule.R.id.relativeLayoutMain
import android.view.ViewGroup
import android.view.Gravity
import android.widget.*
import android.support.design.widget.CoordinatorLayout.Behavior.setTag
import android.widget.TextView
import android.support.design.widget.CoordinatorLayout.Behavior.setTag
import android.widget.TableLayout
import android.widget.RelativeLayout








class timetable :AppCompatActivity(),HorizontalScroll.ScrollViewListener, VerticalScroll.ScrollViewListener  {

    var SCREEN_HEIGHT:Int= 0
    var SCREEN_WIDTH:Int = 0

    var relativeLayoutMain: RelativeLayout? = null

    var relativeLayoutA: RelativeLayout? = null
    var relativeLayoutB: RelativeLayout? = null
    var relativeLayoutC: RelativeLayout? = null
    var relativeLayoutD: RelativeLayout? = null

    var tableLayoutA: TableLayout? = null
    var tableLayoutB: TableLayout? = null
    var tableLayoutC: TableLayout? = null
    var tableLayoutD: TableLayout? = null

    var tableRow: TableRow? = null
    var tableRowB: TableRow? = null

    var horizontalScrollViewB: HorizontalScroll? = null
    var horizontalScrollViewD: HorizontalScroll? = null

    var scrollViewC: VerticalScroll? = null
    var scrollViewD: VerticalScroll? = null

    var tableColumnCountB = 0
    var tableRowCountC = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timetable_layout)

        relativeLayoutMain= findViewById(R.id.relativeLayoutMain)
        getScreenDimension()
        initializeRelativeLayout()
        initializeScrollers()
        initializeTableLayout()
        horizontalScrollViewB!!.setScrollViewListener(this)
        horizontalScrollViewD!!.setScrollViewListener(this)
        scrollViewC!!.setScrollViewListener(this)
        scrollViewD!!.setScrollViewListener(this)

        addRowToTableA()
        initializeRowForTableB()


        for (i in 0..4) {
            addColumnsToTableB("Head$i", i)
        }
        for (i in 0..15) {
            initializeRowForTableD(i)
            addRowToTableC("Row$i",i)
            for (j in 0 until tableColumnCountB) {
                addColumnToTableAtD(i, "D $i $j")
            }
        }

    }

    @Synchronized
    private fun addRowToTableC(text: String,position:Int) {
        val tableRow1 = TableRow(applicationContext)
        val layoutParamsTableRow1 = TableRow.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT / 16)
        tableRow1.setPadding(3, 3, 3, 4)
        tableRow1.layoutParams = layoutParamsTableRow1
        val label_date = TextView(applicationContext)
        if(position%2==0){
            label_date.text = text
        }
        label_date.gravity = 17
        label_date.textSize = resources.getDimension(R.dimen.cell_text_size)
        tableRow1.addView(label_date)

        val tableRow = TableRow(applicationContext)
        val layoutParamsTableRow = TableRow.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT / 16)
        tableRow.setPadding(0, 0, 0, 0)
        tableRow.layoutParams = layoutParamsTableRow
        tableRow.addView(tableRow1)
        this.tableLayoutC!!.addView(tableRow, tableRowCountC)
        tableRowCountC++
    }

    @Synchronized
    private fun addColumnToTableAtD(rowPos: Int, text: String) {
        val tableRowAdd = this.tableLayoutD!!.getChildAt(rowPos) as TableRow
        tableRow = TableRow(applicationContext)
        val layoutParamsTableRow = TableRow.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT / 16)
        tableRow!!.setPadding(3, 3, 3, 4)
//        tableRow!!.background = resources.getDrawable(R.drawable.cell_background)
        tableRow!!.layoutParams = layoutParamsTableRow
        val label_date = TextView(applicationContext)
        label_date.gravity = 17
        label_date.text = text
        label_date.textSize = resources.getDimension(R.dimen.cell_text_size)
        tableRow!!.tag = label_date
        this.tableRow!!.addView(label_date)
        tableRowAdd.addView(tableRow)
    }

    @Synchronized
    private fun initializeRowForTableD(pos: Int) {
        val tableRowD = TableRow(applicationContext)
        val layoutParamsTableRow = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, SCREEN_HEIGHT / 16)
        tableRowD.setPadding(0, 0, 0, 0)
        tableRowD.layoutParams = layoutParamsTableRow
        this.tableLayoutD!!.addView(tableRowD, pos)
    }

    @Synchronized
    private fun addColumnsToTableB(text: String, id: Int) {
        var tableRow = TableRow(applicationContext)
        val layoutParamsTableRow = TableRow.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT / 16)
        tableRow.setPadding(3, 3, 3, 4)
        tableRow.setLayoutParams(layoutParamsTableRow)
        val label_date = TextView(applicationContext)
        label_date.text = text
        label_date.textSize = resources.getDimension(R.dimen.cell_text_size)
        tableRow.addView(label_date)
        tableRow.setTag(id)
        tableRowB!!.addView(tableRow)
        tableColumnCountB++
    }

    private fun initializeRowForTableB() {
        tableRowB = TableRow(getApplicationContext())
        tableRowB!!.setPadding(0,0,0,0)
        this.tableLayoutB!!.addView(tableRowB)
    }

    private fun addRowToTableA() {
        var tableRow = TableRow(applicationContext)
        val layoutParamsTableRow = TableRow.LayoutParams((SCREEN_WIDTH / 5), SCREEN_HEIGHT / 16)
        tableRow.setLayoutParams(layoutParamsTableRow)
        val label_date = TextView(applicationContext)
        label_date.text = "Time/Day"
        label_date.gravity = Gravity.CENTER
        tableRow.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL)
        label_date.textSize = resources.getDimension(R.dimen.cell_text_size)
        tableRow.addView(label_date)
        this.tableLayoutA!!.addView(tableRow)
    }

    private fun initializeTableLayout() {
        tableLayoutA = TableLayout(applicationContext)
        tableLayoutA!!.setPadding(0, 0, 0, 0)
        tableLayoutB = TableLayout(applicationContext)
        tableLayoutB!!.setPadding(0, 0, 0, 0)
        tableLayoutB!!.id = R.id.tableLayoutB
        tableLayoutC = TableLayout(applicationContext)
        tableLayoutC!!.setPadding(0, 0, 0, 0)
        tableLayoutD = TableLayout(applicationContext)
        tableLayoutD!!.setPadding(0, 0, 0, 0)

        val layoutParamsTableLayoutA = TableLayout.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT / 16)
        tableLayoutA!!.layoutParams = layoutParamsTableLayoutA
        tableLayoutA!!.setBackgroundColor(resources.getColor(R.color.colorToGet))
        this.relativeLayoutA!!.addView(tableLayoutA)

        val layoutParamsTableLayoutB = TableLayout.LayoutParams(SCREEN_WIDTH - SCREEN_WIDTH / 5, SCREEN_HEIGHT / 16)
        tableLayoutB!!.layoutParams = layoutParamsTableLayoutB
        tableLayoutB!!.setBackgroundColor(resources.getColor(R.color.colorSecondary))
        this.horizontalScrollViewB!!.addView(tableLayoutB)

        val layoutParamsTableLayoutC = TableLayout.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT - SCREEN_HEIGHT / 1)
        tableLayoutC!!.layoutParams = layoutParamsTableLayoutC
        tableLayoutC!!.setBackgroundColor(resources.getColor(R.color.colorSecondary))
        this.scrollViewC!!.addView(tableLayoutC)

        val layoutParamsTableLayoutD = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT)
        tableLayoutD!!.layoutParams = layoutParamsTableLayoutD
        this.horizontalScrollViewD!!.addView(tableLayoutD)
    }

    private fun initializeScrollers() {
        horizontalScrollViewB = HorizontalScroll(applicationContext)
        horizontalScrollViewB!!.setPadding(0, 0, 0, 0)

        horizontalScrollViewD = HorizontalScroll(applicationContext)
        horizontalScrollViewD!!.setPadding(0, 0, 0, 0)

        scrollViewC = VerticalScroll(applicationContext)
        scrollViewC!!.setPadding(0, 0, 0, 0)

        scrollViewD = VerticalScroll(applicationContext)
        scrollViewD!!.setPadding(0, 0, 0, 0)

        horizontalScrollViewB!!.layoutParams = ViewGroup.LayoutParams(SCREEN_WIDTH - SCREEN_WIDTH / 5, SCREEN_HEIGHT / 16)
        scrollViewC!!.layoutParams = ViewGroup.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT - SCREEN_HEIGHT / 16)
        scrollViewD!!.layoutParams = ViewGroup.LayoutParams(SCREEN_WIDTH - SCREEN_WIDTH / 5, SCREEN_HEIGHT - SCREEN_HEIGHT / 16)
        horizontalScrollViewD!!.layoutParams = ViewGroup.LayoutParams(SCREEN_WIDTH - SCREEN_WIDTH / 5, SCREEN_HEIGHT - SCREEN_HEIGHT / 16)

        this.relativeLayoutB!!.addView(horizontalScrollViewB)
        this.relativeLayoutC!!.addView(scrollViewC)
        this.scrollViewD!!.addView(horizontalScrollViewD)
        this.relativeLayoutD!!.addView(scrollViewD)
    }

    private fun initializeRelativeLayout() {
        relativeLayoutA = RelativeLayout(applicationContext)
        relativeLayoutA!!.id = R.id.relativeLayoutA
        relativeLayoutA!!.setPadding(0, 0, 0, 0)

        relativeLayoutB = RelativeLayout(applicationContext)
        relativeLayoutB!!.id = R.id.relativeLayoutB
        relativeLayoutB!!.setPadding(0, 0, 0, 0)

        relativeLayoutC = RelativeLayout(applicationContext)
        relativeLayoutC!!.id = R.id.relativeLayoutC
        relativeLayoutC!!.setPadding(0, 0, 0, 0)

        relativeLayoutD = RelativeLayout(applicationContext)
        relativeLayoutD!!.id = R.id.relativeLayoutD
        relativeLayoutD!!.setPadding(0, 0, 0, 0)

        relativeLayoutA!!.layoutParams = RelativeLayout.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT / 16)
        this.relativeLayoutMain!!.addView(relativeLayoutA)


        val layoutParamsRelativeLayoutB = RelativeLayout.LayoutParams(SCREEN_WIDTH - SCREEN_WIDTH / 5, SCREEN_HEIGHT / 16)
        layoutParamsRelativeLayoutB.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutA)
        relativeLayoutB!!.layoutParams = layoutParamsRelativeLayoutB
        this.relativeLayoutMain!!.addView(relativeLayoutB)

        val layoutParamsRelativeLayoutC = RelativeLayout.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT - SCREEN_HEIGHT / 16)
        layoutParamsRelativeLayoutC.addRule(RelativeLayout.BELOW, R.id.relativeLayoutA)
        relativeLayoutC!!.layoutParams = layoutParamsRelativeLayoutC
        this.relativeLayoutMain!!.addView(relativeLayoutC)

        val layoutParamsRelativeLayoutD = RelativeLayout.LayoutParams(SCREEN_WIDTH - SCREEN_WIDTH / 5, SCREEN_HEIGHT - SCREEN_HEIGHT / 16)
        layoutParamsRelativeLayoutD.addRule(RelativeLayout.BELOW, R.id.relativeLayoutB)
        layoutParamsRelativeLayoutD.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutC)
        relativeLayoutD!!.layoutParams = layoutParamsRelativeLayoutD
        this.relativeLayoutMain!!.addView(relativeLayoutD)

    }

    private fun getScreenDimension() {
        val wm = applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        SCREEN_WIDTH = size.x
        SCREEN_HEIGHT = size.y
    }

    override fun onScrollChanged(scrollView: HorizontalScroll?, x: Int, y: Int, oldx: Int, oldy: Int) {
        if(scrollView == horizontalScrollViewB){
            horizontalScrollViewD!!.scrollTo(x,y)
        }
        else if(scrollView == horizontalScrollViewD){
            horizontalScrollViewB!!.scrollTo(x, y)
        }
    }

    override fun onScrollChanged(scrollView: VerticalScroll?, x: Int, y: Int, oldx: Int, oldy: Int) {
        if(scrollView == scrollViewC){
            scrollViewD!!.scrollTo(x,y)
        }
        else if(scrollView == scrollViewD){
            scrollViewC!!.scrollTo(x,y)
        }
    }
}