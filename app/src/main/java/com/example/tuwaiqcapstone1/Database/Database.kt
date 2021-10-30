package com.example.tuwaiqcapstone1.Database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tuwaiqcapstone1.Models.TaskDataModel

@Database(entities = [TaskDataModel::class],version = 1)
 abstract class Database:RoomDatabase() {

     abstract fun dao():TaskDao

}