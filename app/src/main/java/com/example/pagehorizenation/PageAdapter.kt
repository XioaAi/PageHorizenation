package com.example.pagehorizenation

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PageAdapter(var context: Context,var data: ArrayList<*>, var callBack: PageRecycleView.CallBack) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var spanColumn = 2
    private var pageMargen = 0

    fun setSpanColumn(spanColumn : Int){
        this.spanColumn = spanColumn
    }

    fun setPageMargen(pageMargen:Int){
        this.pageMargen = pageMargen
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var itemHolder = callBack.onCreateViewHolder(parent,viewType)
        itemHolder.itemView.measure(0, 0)
        itemHolder.itemView.layoutParams.width = (context.resources.displayMetrics.widthPixels - pageMargen*(spanColumn-1)) /spanColumn
        (itemHolder.itemView.layoutParams as? ViewGroup.MarginLayoutParams)?.rightMargin = pageMargen
        itemHolder.itemView.layoutParams.height = 200
        return  itemHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        callBack.onBindViewHolder(holder, position)

        holder.itemView.setOnClickListener {
            callBack.onItemClickListener(holder.itemView,position)
        }
    }

}