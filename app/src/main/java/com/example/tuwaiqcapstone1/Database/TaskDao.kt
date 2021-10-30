package com.example.tuwaiqcapstone1.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tuwaiqcapstone1.Models.TaskDataModel

@Dao
interface TaskDao {

    @Insert
    suspend fun addtask(taskmodel:TaskDataModel)

    @Query("SELECT * FROM TaskDataModel")
    fun gettask(): LiveData<List<TaskDataModel>>


    @Update
    suspend fun updatetask(taskmodel:TaskDataModel)

    @Delete
    suspend fun deletetask(taskmodel:TaskDataModel)


}