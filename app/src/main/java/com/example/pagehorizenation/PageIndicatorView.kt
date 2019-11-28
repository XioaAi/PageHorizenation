package com.example.pagehorizenation

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout

class PageIndicatorView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs) {

    private var columnCount: Int = 1//总共列数
    private var spanColumn:Int = 1//每页列数

    private var indicator: ProgressBar? = null
    private var pageMargin: Int = 0

    init {
        View.inflate(context,R.layout.progressbar,this)
        indicator = findViewById(R.id.essence_progress)
        gravity = Gravity.CENTER
    }

    /**
     * 初始化指示器，默认选中第一页
     *
     * @param count 指示器数量，即页数
     */
    fun initIndicator(count: Int, pageMargin: Int,spanColumn : Int) {
        columnCount = count
        this.spanColumn = spanColumn
        this.pageMargin = pageMargin
        setSelectedPage(0)
    }

    /**
     * 设置选中页
     *
     * @param selected 页下标，从0开始
     */
    fun setSelectedPage(selected: Int) {

        val start = (selected.toFloat() / columnCount) * 50

        indicator?.progress = start.toInt()
        indicator?.secondaryProgress = start.toInt() + 50
    }


    fun setSlideInstance(slideInstance: Float) {
        if (columnCount > 2) {
            val start =
                slideInstance / (((columnCount - spanColumn) * ((context.resources.displayMetrics.widthPixels - pageMargin*(spanColumn-1)) /spanColumn).toDouble()  + (columnCount - spanColumn+1)*pageMargin)/50)
            indicator?.progress = start.toInt()
            indicator?.secondaryProgress = start.toInt() + 50
        }
    }
}