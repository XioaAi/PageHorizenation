package com.example.pagehorizenation

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AutoGridLayoutManager(
    context: Context?,
    spanCount: Int,
    orientation: Int,
    reverseLayout: Boolean
) : GridLayoutManager(context, spanCount, orientation, reverseLayout) {

    var measureWidth = 0
    var measureHeight = 0

    private var totalPage = 0

    fun setTotalPage(totalPage:Int){
        this.totalPage = totalPage
    }

    override fun onMeasure(
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State,
        widthSpec: Int,
        heightSpec: Int
    ) {
        if(measureHeight<=0 && state.itemCount>0){
            var view = recycler.getViewForPosition(0)
            measureChild(view,widthSpec,heightSpec)
            measureWidth = view.width
            measureHeight = view.height * spanCount
            setMeasuredDimension(measureWidth,measureHeight)
        }
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        if(totalPage>1){
            return super.scrollHorizontallyBy(dx, recycler, state)
        }
        return 0

    }

}