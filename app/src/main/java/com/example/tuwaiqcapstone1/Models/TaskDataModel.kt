package com.example.tuwaiqcapstone1.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class TaskDataModel(
 var task_Name:String,
 var task_Status:Boolean,
 var creation_Date: Date,
 var due_Date:Date,
 var task_Description:String,
 @PrimaryKey(autoGenerate = true)
 val id:Int

)
