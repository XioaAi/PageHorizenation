package com.example.pagehorizenation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil

class PageRecycleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    RecyclerView(context, attrs) {

    private var spanCount = 2//行数
    private var spanColumn = 2//每页列数

    private var pageMargen = 14

    private var pageAdater: PageAdapter? = null

    private var autoGridLayoutManager:AutoGridLayoutManager

    private var mIndicatorView: PageIndicatorView? = null // 指示器布局

    private var scrollX = 0f // X轴当前的位置
    private var slideDistance = 0f // 滑动的距离
    private var totalPage = 0 // 总页数
    private var currentPage = 1 // 当前页

    private var mTouchSlop = 0

    init {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
        autoGridLayoutManager = AutoGridLayoutManager(context,spanCount, HORIZONTAL,false)
        layoutManager = autoGridLayoutManager
    }

    fun setSpanCount(spanCount:Int,spanColumn:Int){
        this.spanCount = spanCount
        this.spanColumn = spanColumn
        this.autoGridLayoutManager = AutoGridLayoutManager(context,spanCount, HORIZONTAL,false)
        layoutManager = autoGridLayoutManager
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        this.pageAdater = adapter as? PageAdapter
        pageAdater?.setPageMargen(pageMargen)
        pageAdater?.setSpanColumn(spanColumn)
        update()
    }

    // 更新页码指示器和相关数据
    private fun update() {
        // 计算列数
        val columnNums =
            ceil(pageAdater!!.data!!.size / 2.toDouble()).toInt()
        if (columnNums != totalPage) {
            mIndicatorView?.initIndicator(columnNums, pageMargen,spanColumn)
            // 页码减少且当前页为最后一页
            if (columnNums < totalPage && currentPage == totalPage) {
                currentPage = columnNums
                // 执行滚动
                smoothScrollBy(-width, 0)
            }
            mIndicatorView?.setSelectedPage(currentPage - 1)
            totalPage = columnNums
        }
        // 当页面为1时不显示指示器
        if (totalPage > 2) {
            mIndicatorView?.visibility = View.VISIBLE
        } else {
            mIndicatorView?.visibility = View.GONE
        }
        autoGridLayoutManager!!.setTotalPage(columnNums)
    }

    fun setIndicator(indicatorView: PageIndicatorView) {
        this.mIndicatorView = indicatorView
    }

    override fun onScrolled(dx: Int, dy: Int) {
        scrollX += dx.toFloat()

        slideDistance += dx.toFloat()

        mIndicatorView?.setSlideInstance(slideInstance = slideDistance)
        super.onScrolled(dx, dy)
    }

    interface CallBack {
        /**
         * 创建VieHolder
         */
        fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder

        /**
         * 绑定数据到ViewHolder
         */
        fun onBindViewHolder(holder: ViewHolder, position: Int)

        fun onItemClickListener(view: View, position: Int)

    }



}