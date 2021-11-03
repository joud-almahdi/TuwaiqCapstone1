package com.example.tuwaiqcapstone1.Repository

import android.content.Context
import androidx.room.Room
import com.example.tuwaiqcapstone1.Database.Database
import com.example.tuwaiqcapstone1.Database.TaskDao
import com.example.tuwaiqcapstone1.Models.TaskDataModel

class TaskRepository(context:Context) {

    val database:Database=Room.databaseBuilder(context,Database::class.java,"database").build()
    val dao:TaskDao=database.dao()


   fun getTask(status:Boolean)=dao.gettask(status)
    fun getCompleteTask(status:Boolean)=dao.getcompletetask(status)
    suspend fun addTask(model:TaskDataModel)=dao.addtask(model)
    suspend fun updateTask(model: TaskDataModel)=dao.updatetask(model)
    suspend fun deleteTask(model: TaskDataModel)=dao.deletetask(model)





    companion object
    {

    var instance:TaskRepository?=null

        fun init(context: Context)
        {
            if(instance==null)
            {

                instance= TaskRepository(context)
            }
        }
        fun get():TaskRepository
        {
            return instance?:throw Exception ("Error in database Creation")
        }
    }
}