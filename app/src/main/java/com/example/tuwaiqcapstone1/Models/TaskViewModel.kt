package com.example.tuwaiqcapstone1.Models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tuwaiqcapstone1.Repository.TaskRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class TaskViewModel:ViewModel() {


    private val repository=TaskRepository.get()
    var taskcontent=repository.getTask(false)
    var taskcompletecontent=repository.getCompleteTask(true)


    var selectmutablelivedata=MutableLiveData<TaskDataModel>()


   //https://developer.android.com/reference/kotlin/java/time/format/DateTimeFormatter

 @RequiresApi(Build.VERSION_CODES.O)
 var date:LocalDate= LocalDate.now()
 @RequiresApi(Build.VERSION_CODES.O)
 val formatter:DateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd")
    @RequiresApi(Build.VERSION_CODES.O)
    var dateastext:String=date.format(formatter)

    fun addatask(task_name:String,Task_status:Boolean,due_date:String,task_description:String)
    {
       viewModelScope.launch {
          repository.addTask(TaskDataModel(task_name,Task_status,dateastext,due_date,task_description))
       }
    }



    fun updatetask(taskmodel:TaskDataModel)
    {
        viewModelScope.launch {
           repository.updateTask(taskmodel)
        }
    }


    fun deletetask(taskmodel: TaskDataModel)
    {
        viewModelScope.launch {
           repository.deleteTask(taskmodel)
        }
    }






}