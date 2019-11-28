package com.example.pagehorizenation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var data = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var time = simpleDateFormat.format(simpleDateFormat.parse("2019-11-27 14:00:00"))
        Log.e("test","---$time")

        for(i in 1..12){
            data.add("")
        }

        page_recycle_view.setSpanCount(2,4)
        page_recycle_view.setIndicator(page_indicator_view)
        page_recycle_view.adapter = PageAdapter(this,data,object : PageRecycleView.CallBack{
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                    return viewHolder(LayoutInflater.from(this@MainActivity).inflate(R.layout.item,parent,false))
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            }

            override fun onItemClickListener(view: View, position: Int) {
                Toast.makeText(this@MainActivity,"点击Item：$position",Toast.LENGTH_LONG).show()
            }

        })
    }

    inner class viewHolder(view:View) : RecyclerView.ViewHolder(view)

}
