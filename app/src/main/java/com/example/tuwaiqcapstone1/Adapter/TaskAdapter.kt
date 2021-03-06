package com.example.tuwaiqcapstone1.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
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
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.os.Build
import android.util.Log
import android.view.View.VISIBLE
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TaskAdapter(val list:MutableList<TaskDataModel>,val viewmodel:TaskViewModel):RecyclerView.Adapter<ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layout=LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
    return ViewHolder(layout)

  }

  @SuppressLint("SetTextI18n")
  @RequiresApi(Build.VERSION_CODES.O)
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    //https://developer.android.com/reference/kotlin/android/graphics/Paint#strike_thru_text_flag
    val thetasksfound=list[position]
    holder.tasknameinlayout.text = thetasksfound.task_Name
    if(thetasksfound.due_Date!= "")
    {
      val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
      val useddate=LocalDate.parse(thetasksfound.due_Date,formatter)
      val currentdate:LocalDate= LocalDate.now()
      holder.taskduedateinlayout.visibility= VISIBLE
      holder.taskduedateinlayout.text = thetasksfound.due_Date


      if(useddate.isBefore(currentdate))
      {


        holder.tasknameinlayout.setTextColor(Color.RED)
        holder.taskduedateinlayout.setTextColor(Color.RED)
        holder.overudealert.visibility=VISIBLE
        holder.overudealert.setText("Overdue")
        holder.overudealert.setTextColor(Color.RED)
        Log.d("changingimages","Please don't let me edit my image")

      }


    }


    if (thetasksfound.task_Status==true)
    {
      holder.tasknameinlayout.paintFlags=STRIKE_THRU_TEXT_FLAG
      holder.taskduedateinlayout.paintFlags=STRIKE_THRU_TEXT_FLAG
      holder.overudealert.paintFlags=STRIKE_THRU_TEXT_FLAG
      holder.completionimage.setImageResource(R.drawable.checked)
    }
    else
    {
      holder.tasknameinlayout.paintFlags=0
      holder.taskduedateinlayout.paintFlags=0
      holder.overudealert.paintFlags=0
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
        holder.tasknameinlayout.paintFlags=STRIKE_THRU_TEXT_FLAG
        holder.taskduedateinlayout.paintFlags=STRIKE_THRU_TEXT_FLAG
        holder.overudealert.paintFlags=STRIKE_THRU_TEXT_FLAG
        holder.completionimage.setImageResource(R.drawable.checked)

      }
      else
      {
        thetasksfound.task_Status=false
        holder.tasknameinlayout.paintFlags=0
        holder.taskduedateinlayout.paintFlags=0
        holder.overudealert.paintFlags=0
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
  val overudealert:TextView=view.findViewById(R.id.OverdueTextInListLayout)
}