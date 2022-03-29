package com.ws.todomate.todoList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ws.todomate.R
import com.ws.todomate.calendar.AdapterDay

class AdapterTodo(val list : MutableList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var converView = convertView
        if(converView==null) {
            converView = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_todo, parent, false)
        }
        val textArea = converView?.findViewById<TextView>(R.id.todo_textArea)
        textArea!!.text = list[position]
        return converView!!
    }

}