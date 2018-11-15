package com.deknerdvariety.prayat.schedule

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fragment.Teach_schedule_fragment
import kotlinx.android.synthetic.main.timetable_layout.*
import android.R.attr.y
import android.R.attr.x
import android.graphics.Point
import android.widget.*
import android.support.design.widget.CoordinatorLayout.Behavior.setTag
import android.widget.TextView
import android.support.design.widget.CoordinatorLayout.Behavior.setTag
import android.view.*
import android.widget.TableLayout
import android.widget.RelativeLayout
import com.deknerdvariety.prayat.schedule.R.id.*


class timetable : AppCompatActivity(), HorizontalScroll.ScrollViewListener, VerticalScroll.ScrollViewListener {

    var SCREEN_HEIGHT: Int = 0
    var SCREEN_WIDTH: Int = 0

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


    val dateLabel = listOf<String>("Mon", "Tur", "Wed", "Thr", "Fri")
    val timeLable = listOf<String>("8.00", "", "9.00", "", "10.00", "", "11.00", "", "12.00", "", "13.00", "", "14.00", "", "15.00", "", "16.00", "", "17.00", "", "18.00")
    val labelList = ArrayList<View>()

    val course_list = Teach_schedule_fragment.course_ary


//    var semester:String? = null
//    var idCourse:String? = null
//    var startTime:String? = null
//    var firstDay:String? = null
//    var stopTime:String? = null
//    var secondDay:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timetable_layout)

        relativeLayoutMain = findViewById(R.id.relativeLayoutMain)
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
            addColumnsToTableB("${dateLabel[i]}", i)
        }
//
        for (i in 0..20) {
            initializeRowForTableD(i)
            addRowToTableC("${timeLable[i]}", i)
            for (j in 0 until tableColumnCountB) {
                var label = addColumnToTableAtD(i, "")
                labelList.add(label)
            }
        }

        for (i in 0..course_list.size - 1) {
            calculateSchedule(i)
        }


    }

    private fun calTime(startTime: String, stopTime: String, startList: Int, idCourse: String) {
        var changeStartTime = startTime.toFloat()
        var changeStopTime = stopTime.toFloat()

        var diff = changeStopTime - changeStartTime

        println("jjjjjjjjjjjjjjjjjjjjjjjjjj-> $diff")

        if (diff < 1.1) {
            labelList[startList].setBackgroundColor(resources.getColor(R.color.colorToPay))
            labelList[startList + 5].setBackgroundColor(resources.getColor(R.color.colorToPay))
            //findViewById<TextView>(R.id.eight1).setText(idCourse)
            labelList[startList + 3].setBackgroundColor(resources.getColor(R.color.colorToPay))
            labelList[startList + 5 + 3].setBackgroundColor(resources.getColor(R.color.colorToPay))
        } else if (1.1 < diff && diff < 1.4) {
            labelList[startList].setBackgroundColor(resources.getColor(R.color.colorToPay))
            labelList[startList + 5].setBackgroundColor(resources.getColor(R.color.colorToPay))
            labelList[startList + 5 + 5].setBackgroundColor(resources.getColor(R.color.colorToPay))

            labelList[startList + 3].setBackgroundColor(resources.getColor(R.color.colorToPay))
            labelList[startList + 5 + 3].setBackgroundColor(resources.getColor(R.color.colorToPay))
            labelList[startList + 5 + 5 + 3].setBackgroundColor(resources.getColor(R.color.colorToPay))
        } else if (1.4 < diff && diff < 2.1) {
            //updateCompleteRow(startList % 5, idCourse)

            labelList[startList].setBackgroundColor(resources.getColor(R.color.colorToPay))
            labelList[startList + 5].setBackgroundColor(resources.getColor(R.color.colorToPay))
            labelList[startList + 5 + 5].setBackgroundColor(resources.getColor(R.color.colorToPay))
            labelList[startList + 5 + 5 + 5].setBackgroundColor(resources.getColor(R.color.colorToPay))

            labelList[startList + 3].setBackgroundColor(resources.getColor(R.color.colorToPay))
            labelList[startList + 5 + 3].setBackgroundColor(resources.getColor(R.color.colorToPay))
            labelList[startList + 5 + 5 + 3].setBackgroundColor(resources.getColor(R.color.colorToPay))
            labelList[startList + 5 + 5 + 5 + 3].setBackgroundColor(resources.getColor(R.color.colorToPay))
        }
    }

    private fun calculateSchedule(i: Int) {
        val semester = course_list[i].semester!!
        val idCourse = course_list[i].idCourse!!
        val startTime = course_list[i].startTime!!
        val stopTime = course_list[i].stopTime!!
        val firstDay = course_list[i].startDate!!
        val secondDay = course_list[i].stopDate!!

        val timeStart = listOf<String>("08.00", "08.30", "09.00", "09.30", "10.00", "10.30", "11.00", "11.30", "12.00", "12.30", "13.00", "13.30", "14.00", "14.30", "15.00", "15.30", "16.00")
        val timeStartvalueMon = listOf<Int>(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95)
        val timeStartvalueTue = timeStartvalueMon.map { it -> it + 1 }
        val timeStartvalueWed = timeStartvalueMon.map { it -> it + 2 }
        //val value_of_day = listOf<Int>(0,1,2,3,4)

        if (firstDay!!.toLowerCase().equals("monday")) {
            if (startTime.equals(timeStart[0])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[0], idCourse)
                //updateCompleteRow(timeStartvalueMon[0]% 5, idCourse)
            } else if (startTime.equals(timeStart[1])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[1], idCourse)
            } else if (startTime.equals(timeStart[2])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[2], idCourse)
            } else if (startTime.equals(timeStart[3])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[3], idCourse)
            } else if (startTime.equals(timeStart[4])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[4], idCourse)
            } else if (startTime.equals(timeStart[5])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[5], idCourse)
            } else if (startTime.equals(timeStart[6])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[6], idCourse)
            } else if (startTime.equals(timeStart[7])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[7], idCourse)
            } else if (startTime.equals(timeStart[8])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[8], idCourse)
            } else if (startTime.equals(timeStart[9])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[9], idCourse)
            } else if (startTime.equals(timeStart[10])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[10], idCourse)
            } else if (startTime.equals(timeStart[11])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[11], idCourse)
            } else if (startTime.equals(timeStart[12])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[12], idCourse)
            } else if (startTime.equals(timeStart[13])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[13], idCourse)
            } else if (startTime.equals(timeStart[14])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[14], idCourse)
            } else if (startTime.equals(timeStart[15])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[15], idCourse)
            } else if (startTime.equals(timeStart[16])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[16], idCourse)
            } else if (startTime.equals(timeStart[17])) {
                calTime(startTime!!, stopTime!!, timeStartvalueMon[17], idCourse)
            }
        } else if (firstDay!!.toLowerCase().equals("tuesday")) {
            if (startTime.equals(timeStart[0])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[0], idCourse)
            } else if (startTime.equals(timeStart[1])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[1], idCourse)
            } else if (startTime.equals(timeStart[2])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[2], idCourse)
            } else if (startTime.equals(timeStart[3])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[3], idCourse)
            } else if (startTime.equals(timeStart[4])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[4], idCourse)
            } else if (startTime.equals(timeStart[5])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[5], idCourse)
            } else if (startTime.equals(timeStart[6])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[6], idCourse)
            } else if (startTime.equals(timeStart[7])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[7], idCourse)
            } else if (startTime.equals(timeStart[8])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[8], idCourse)
            } else if (startTime.equals(timeStart[9])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[9], idCourse)
            } else if (startTime.equals(timeStart[10])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[10], idCourse)
            } else if (startTime.equals(timeStart[11])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[11], idCourse)
            } else if (startTime.equals(timeStart[12])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[12], idCourse)
            } else if (startTime.equals(timeStart[13])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[13], idCourse)
            } else if (startTime.equals(timeStart[14])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[14], idCourse)
            } else if (startTime.equals(timeStart[15])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[15], idCourse)
            } else if (startTime.equals(timeStart[16])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[16], idCourse)
            } else if (startTime.equals(timeStart[17])) {
                calTime(startTime!!, stopTime!!, timeStartvalueTue[17], idCourse)
            }
        } else {
            if (startTime.equals(timeStart[0])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[0], idCourse)
            } else if (startTime.equals(timeStart[1])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[1], idCourse)
            } else if (startTime.equals(timeStart[2])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[2], idCourse)
            } else if (startTime.equals(timeStart[3])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[3], idCourse)
            } else if (startTime.equals(timeStart[4])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[4], idCourse)
            } else if (startTime.equals(timeStart[5])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[5], idCourse)
            } else if (startTime.equals(timeStart[6])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[6], idCourse)
            } else if (startTime.equals(timeStart[7])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[7], idCourse)
            } else if (startTime.equals(timeStart[8])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[8], idCourse)
            } else if (startTime.equals(timeStart[9])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[9], idCourse)
            } else if (startTime.equals(timeStart[10])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[10], idCourse)
            } else if (startTime.equals(timeStart[11])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[11], idCourse)
            } else if (startTime.equals(timeStart[12])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[12], idCourse)
            } else if (startTime.equals(timeStart[13])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[13], idCourse)
            } else if (startTime.equals(timeStart[14])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[14], idCourse)
            } else if (startTime.equals(timeStart[15])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[15], idCourse)
            } else if (startTime.equals(timeStart[16])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[16], idCourse)
            } else if (startTime.equals(timeStart[17])) {
                calTime(startTime!!, stopTime!!, timeStartvalueWed[17], idCourse)
            }
        }
    }

    @Synchronized
    private fun addRowToTableC(text: String, position: Int) {
        val tableRow1 = TableRow(applicationContext)
        val layoutParamsTableRow1 = TableRow.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT / 20)
        tableRow1.setPadding(0, 0, 0, 0)
        tableRow1.layoutParams = layoutParamsTableRow1
        val label_date = TextView(applicationContext)
        label_date.text = text
        label_date.gravity = 17
        label_date.textSize = resources.getDimension(R.dimen.cell_text_size)
        tableRow1.addView(label_date)

        val tableRow = TableRow(applicationContext)
        val layoutParamsTableRow = TableRow.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT / 20)
        tableRow.setPadding(0, 0, 0, 0)
        tableRow.layoutParams = layoutParamsTableRow
        tableRow.addView(tableRow1)
        this.tableLayoutC!!.addView(tableRow, tableRowCountC)
        tableRowCountC++
    }

    @Synchronized
    public fun addColumnToTableAtD(rowPos: Int, text: String): View {
        val tableRowAdd = this.tableLayoutD!!.getChildAt(rowPos) as TableRow
        tableRow = TableRow(applicationContext)
        val layoutParamsTableRow = TableRow.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT / 20)
        tableRow!!.setPadding(0, 0, 0, 0)
        tableRow!!.background = resources.getDrawable(R.drawable.cell_background)
        tableRow!!.layoutParams = layoutParamsTableRow
        val layoutParamsTextView = TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SCREEN_HEIGHT / 20)
        val label_date = TextView(applicationContext)
        label_date.setLayoutParams(layoutParamsTextView)
        label_date.gravity = 17
        label_date.text = text
        label_date.textSize = resources.getDimension(R.dimen.cell_text_size)
//            0->label_date.id = R.id.M800
//            1->label_date.id = R.id.Tu800
//            2->label_date.id = R.id.W800
//            3->label_date.id = R.id.Th800
//            4->label_date.id = R.id.F800
//            5->label_date.id = R.id.M830
//            6->label_date.id = R.id.Tu830
//            7->label_date.id = R.id.W830
//            8->label_date.id = R.id.Th830
//            9->label_date.id = R.id.F830
//            10->label_date.id = R.id.M900
//            11->label_date.id = R.id.Tu900
//            12->label_date.id = R.id.W900
//            13->label_date.id = R.id.Th900
//            14->label_date.id = R.id.F900
//            15->label_date.id = R.id.M930
//            16->label_date.id = R.id.Tu930
//            17->label_date.id = R.id.W930
//            18->label_date.id = R.id.Th930
//            19->label_date.id = R.id.F930
//            20->label_date.id = R.id.M100
//            21->label_date.id = R.id.Tu100
//            22->label_date.id = R.id.W100
//            23->label_date.id = R.id.Th100
//            24->label_date.id = R.id.F100
//            25->label_date.id = R.id.M103
//            26->label_date.id = R.id.Tu103
//            27->label_date.id = R.id.W103
//            28->label_date.id = R.id.Th103
//            29->label_date.id = R.id.F103
//            30->label_date.id = R.id.M110
//            31->label_date.id = R.id.Tu110
//            32->label_date.id = R.id.W110
//            33->label_date.id = R.id.Th110
//            34->label_date.id = R.id.F110
//            35->label_date.id = R.id.M113
//            36->label_date.id = R.id.Tu113
//            37->label_date.id = R.id.W113
//            38->label_date.id = R.id.Th113
//            39->label_date.id = R.id.F113
//            40->label_date.id = R.id.M120
//            41->label_date.id = R.id.Tu120
//            42->label_date.id = R.id.W120
//            43->label_date.id = R.id.Th120
//            44->label_date.id = R.id.F120
//            45->label_date.id = R.id.M123
//            46->label_date.id = R.id.Tu123
//            47->label_date.id = R.id.W123
//            48->label_date.id = R.id.Th123
//            49->label_date.id = R.id.F123
//            50->label_date.id = R.id.M130
//            51->label_date.id = R.id.Tu130
//            52->label_date.id = R.id.W130
//            53->label_date.id = R.id.Th130
//            54->label_date.id = R.id.F130
//            55->label_date.id = R.id.M133
//            56->label_date.id = R.id.Tu133
//            57->label_date.id = R.id.W133
//            58->label_date.id = R.id.Th133
//            59->label_date.id = R.id.F133
//            60->label_date.id = R.id.M140
//            61->label_date.id = R.id.Tu140
//            62->label_date.id = R.id.W140
//            63->label_date.id = R.id.Th140
//            64->label_date.id = R.id.F140
//            65->label_date.id = R.id.M143
//            66->label_date.id = R.id.Tu143
//            67->label_date.id = R.id.W143
//            68->label_date.id = R.id.Th143
//            69->label_date.id = R.id.F143
//            70->label_date.id = R.id.M150
//            71->label_date.id = R.id.Tu150
//            72->label_date.id = R.id.W150
//            73->label_date.id = R.id.Th150
//            74->label_date.id = R.id.F150
//            75->label_date.id = R.id.M153
//            76->label_date.id = R.id.Tu153
//            77->label_date.id = R.id.W153
//            78->label_date.id = R.id.Th153
//            79->label_date.id = R.id.F153
//            80->label_date.id = R.id.M160
//            81->label_date.id = R.id.Tu160
//            82->label_date.id = R.id.W160
//            83->label_date.id = R.id.Th160
//            84->label_date.id = R.id.F160
//            85->label_date.id = R.id.M163
//            86->label_date.id = R.id.Tu163
//            87->label_date.id = R.id.W163
//            88->label_date.id = R.id.Th163
//            89->label_date.id = R.id.F163
//            90->label_date.id = R.id.M170
//            91->label_date.id = R.id.Tu170
//            92->label_date.id = R.id.W170
//            93->label_date.id = R.id.Th170
//            94->label_date.id = R.id.F170
//            95->label_date.id = R.id.M173
//            96->label_date.id = R.id.Tu173
//            97->label_date.id = R.id.W173
//            98->label_date.id = R.id.Th173
//            99->label_date.id = R.id.F173
//            100->label_date.id = R.id.F180
//            101->label_date.id = R.id.M180
//            102->label_date.id = R.id.Tu180
//            103->label_date.id = R.id.W180
//            104->label_date.id = R.id.Th180
//            105->label_date.id = R.id.F180
//
//        }
        tableRow!!.tag = label_date
        this.tableRow!!.addView(label_date)
        tableRowAdd.addView(tableRow)
        return label_date
    }

    @Synchronized
    private fun initializeRowForTableD(pos: Int) {
        val tableRowD = TableRow(applicationContext)
        val layoutParamsTableRow = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, SCREEN_HEIGHT / 20)
        tableRowD.setPadding(0, 0, 0, 0)
        tableRowD.layoutParams = layoutParamsTableRow
        this.tableLayoutD!!.addView(tableRowD, pos)
    }

    @Synchronized
    private fun addColumnsToTableB(text: String, id: Int) {
        var tableRow = TableRow(applicationContext)
        val layoutParamsTableRow = TableRow.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT / 20)
        tableRow.setPadding(3, 0, 3, 0)
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
        tableRowB!!.setPadding(0, 0, 0, 0)
        this.tableLayoutB!!.addView(tableRowB)
    }

    private fun addRowToTableA() {
        var tableRow = TableRow(applicationContext)
        val layoutParamsTableRow = TableRow.LayoutParams((SCREEN_WIDTH / 5), SCREEN_HEIGHT / 20)
        tableRow.setLayoutParams(layoutParamsTableRow)
        val layoutParamsTextView = TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SCREEN_HEIGHT / 20)
        val label_date = TextView(applicationContext)
        label_date.setLayoutParams(layoutParamsTextView)
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

        val layoutParamsTableLayoutA = TableLayout.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT / 20)
        tableLayoutA!!.layoutParams = layoutParamsTableLayoutA
        tableLayoutA!!.setBackgroundColor(resources.getColor(R.color.colorToGet))
        this.relativeLayoutA!!.addView(tableLayoutA)

        val layoutParamsTableLayoutB = TableLayout.LayoutParams(SCREEN_WIDTH - SCREEN_WIDTH / 5, SCREEN_HEIGHT / 20)
        tableLayoutB!!.layoutParams = layoutParamsTableLayoutB
        tableLayoutB!!.setBackgroundColor(resources.getColor(R.color.colorSecondary))
        this.horizontalScrollViewB!!.addView(tableLayoutB)

        val layoutParamsTableLayoutC = TableLayout.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT - SCREEN_HEIGHT / 20)
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

        horizontalScrollViewB!!.layoutParams = ViewGroup.LayoutParams(SCREEN_WIDTH - SCREEN_WIDTH / 5, SCREEN_HEIGHT / 20)
        scrollViewC!!.layoutParams = ViewGroup.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT - SCREEN_HEIGHT / 20)
        scrollViewD!!.layoutParams = ViewGroup.LayoutParams(SCREEN_WIDTH - SCREEN_WIDTH / 5, SCREEN_HEIGHT - SCREEN_HEIGHT / 20)
        horizontalScrollViewD!!.layoutParams = ViewGroup.LayoutParams(SCREEN_WIDTH - SCREEN_WIDTH / 5, SCREEN_HEIGHT - SCREEN_HEIGHT / 20)

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

        relativeLayoutA!!.layoutParams = RelativeLayout.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT / 20)
        this.relativeLayoutMain!!.addView(relativeLayoutA)


        val layoutParamsRelativeLayoutB = RelativeLayout.LayoutParams(SCREEN_WIDTH - SCREEN_WIDTH / 5, SCREEN_HEIGHT / 20)
        layoutParamsRelativeLayoutB.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutA)
        relativeLayoutB!!.layoutParams = layoutParamsRelativeLayoutB
        this.relativeLayoutMain!!.addView(relativeLayoutB)

        val layoutParamsRelativeLayoutC = RelativeLayout.LayoutParams(SCREEN_WIDTH / 5, SCREEN_HEIGHT - SCREEN_HEIGHT / 20)
        layoutParamsRelativeLayoutC.addRule(RelativeLayout.BELOW, R.id.relativeLayoutA)
        relativeLayoutC!!.layoutParams = layoutParamsRelativeLayoutC
        this.relativeLayoutMain!!.addView(relativeLayoutC)

        val layoutParamsRelativeLayoutD = RelativeLayout.LayoutParams(SCREEN_WIDTH - SCREEN_WIDTH / 5, SCREEN_HEIGHT - SCREEN_HEIGHT / 20)
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
        if (scrollView == horizontalScrollViewB) {
            horizontalScrollViewD!!.scrollTo(x, y)
        } else if (scrollView == horizontalScrollViewD) {
            horizontalScrollViewB!!.scrollTo(x, y)
        }
    }

    override fun onScrollChanged(scrollView: VerticalScroll?, x: Int, y: Int, oldx: Int, oldy: Int) {
        if (scrollView == scrollViewC) {
            scrollViewD!!.scrollTo(x, y)
        } else if (scrollView == scrollViewD) {
            scrollViewC!!.scrollTo(x, y)
        }
    }

    private fun createCompleteColumn(value: String) {
        val i = 0
        val j = tableRowCountC - 1
        for (k in i..j) {
            addColumnToTableAtD(k, value)
        }
    }

    private fun createCompleteRow(value: String) {
        initializeRowForTableD(0)
        val i = 0
        val j = tableColumnCountB - 1
        val pos = tableRowCountC - 1
        for (k in i..j) {
            addColumnToTableAtD(pos, value)
        }
    }

}