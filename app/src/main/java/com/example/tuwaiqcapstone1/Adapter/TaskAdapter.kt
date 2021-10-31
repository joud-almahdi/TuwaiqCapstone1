package com.example.tuwaiqcapstone1.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tuwaiqcapstone1.Models.TaskDataModel
import com.example.tuwaiqcapstone1.Models.TaskViewModel
import com.example.tuwaiqcapstone1.R
import android.graphics.Paint

class TaskAdapter(val list:MutableList<TaskDataModel>,val viewmodel:TaskViewModel):RecyclerView.Adapter<ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layout=LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
    return ViewHolder(layout)

  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val thetasksfound=list[position]
    holder.tasknameinlayout.text=thetasksfound.task_Name
    holder.taskduedateinlayout.text=thetasksfound.due_Date.toString()
    if (thetasksfound.task_Status==true)
    {
      holder.completionimage.setImageResource(R.drawable.checked)
    }
    else
    {
      holder.completionimage.setImageResource(R.drawable.unchecked)
    }

    holder.itemView.setOnClickListener {
        viewmodel.selectmutablelivedata.postValue(thetasksfound)
      holder.itemView.findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
    }

    holder.completionimage.setOnClickListener{

      if (thetasksfound.task_Status==false)
      {
        thetasksfound.task_Status=true
        holder.completionimage.setImageResource(R.drawable.checked)

      }
      else
      {
        thetasksfound.task_Status=false
        holder.completionimage.setImageResource(R.drawable.unchecked)
      }



      viewmodel.updatetask(thetasksfound)


    }





  }

  override fun getItemCount(): Int {
    return list.size
  }
}




class ViewHolder(view: View):RecyclerView.ViewHolder(view)
{
  val tasknameinlayout: TextView =view.findViewById(R.id.TaskNameInListLayout)
  val taskduedateinlayout:TextView=view.findViewById(R.id.TaskDueDateInItemLayout)
    val completionimage:ImageView=view.findViewById(R.id.TaskCompletionImageInItemLayout)
}