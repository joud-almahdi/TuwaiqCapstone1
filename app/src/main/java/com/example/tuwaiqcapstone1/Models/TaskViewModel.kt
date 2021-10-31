package com.example.tuwaiqcapstone1.Models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tuwaiqcapstone1.Repository.TaskRepository
import kotlinx.coroutines.launch
import java.util.*

class TaskViewModel:ViewModel() {


    private val repository=TaskRepository.get()
    var taskcontent=repository.getTask()


    var selectmutablelivedata=MutableLiveData<TaskDataModel>()




    fun addatask(task_name:String,Task_status:Boolean,due_date:String,task_description:String)
    {
       viewModelScope.launch {
          repository.addTask(TaskDataModel(task_name,Task_status,Calendar.getInstance().time.toString(),due_date,task_description))
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