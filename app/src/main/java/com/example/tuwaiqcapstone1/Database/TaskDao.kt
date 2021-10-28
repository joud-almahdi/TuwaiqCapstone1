package com.example.tuwaiqcapstone1.Database

import androidx.room.*
import com.example.tuwaiqcapstone1.Models.TaskDataModel

@Dao
interface TaskDao {

    @Insert
    suspend fun addtask(taskmodel:TaskDataModel)

    @Query("SELECT * FROM TaskDataModel")
    fun gettask():List<TaskDataModel>


    @Update
    suspend fun updatetask(taskmodel:TaskDataModel)

    @Delete
    suspend fun deletefun(taskmodel:TaskDataModel)


}