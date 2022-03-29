package com.ws.todomate.calendar

import android.graphics.Color
import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ws.todomate.R
import java.util.*

class AdapterDay(val tempMonth:Int, val dayList: MutableList<Date>): RecyclerView.Adapter<AdapterDay.DayView>() {
    val ROW = 7

    inner class DayView(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_day, parent, false)
        return DayView(view)
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        holder.layout.findViewById<LinearLayout>(R.id.item_day_layout).setOnClickListener {
            val month = (dayList[position].month + 1).toString()
            val date = dayList[position].date.toString()
            val year = (dayList[position].year+1900).toString()
            Toast.makeText(holder.layout.context,"${year}년 ${month}월 ${date}일" , Toast.LENGTH_SHORT).show()
        }
        holder.layout.findViewById<TextView>(R.id.item_day_text).text = dayList[position].date.toString()

        //토,일 글자 색깔
        holder.layout.findViewById<TextView>(R.id.item_day_text).setTextColor(when(position % 7) {
            0 -> Color.RED
            6 -> Color.BLUE
            else -> Color.BLACK
        })

        //다음달, 지난달 글자 색깔
        if(tempMonth != dayList[position].month) {
            holder.layout.findViewById<TextView>(R.id.item_day_text).alpha = 0.3f
        }
    }

    override fun getItemCount(): Int {
        return ROW * 5
    }
}