package com.example.tuwaiqcapstone1.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tuwaiqcapstone1.Models.TaskDataModel

@Dao
interface TaskDao {

    @Insert
    suspend fun addtask(taskmodel:TaskDataModel)

    @Query("SELECT * FROM TaskDataModel WHERE task_Status == :status")
    fun gettask(status:Boolean): LiveData<List<TaskDataModel>>


    @Query("SELECT * FROM TaskDataModel WHERE task_Status == :status")
    fun getcompletetask(status:Boolean): LiveData<List<TaskDataModel>>


    @Update
    suspend fun updatetask(taskmodel:TaskDataModel)

    @Delete
    suspend fun deletetask(taskmodel:TaskDataModel)


}