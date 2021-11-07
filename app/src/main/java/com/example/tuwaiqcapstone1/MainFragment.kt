package com.example.tuwaiqcapstone1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tuwaiqcapstone1.Adapter.TaskAdapter
import com.example.tuwaiqcapstone1.Models.TaskDataModel
import com.example.tuwaiqcapstone1.Models.TaskViewModel
import  androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainFragment : Fragment() {
    var counter=0
    var Channelid:String="456"
    var notificationid:Int=1
    val usedviewmodel:TaskViewModel by activityViewModels()
    var list= mutableListOf<TaskDataModel>()
    var completedlist=mutableListOf<TaskDataModel>()
    lateinit var addingbutton: FloatingActionButton
    lateinit var listoftasks:TaskDataModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filterbutton:Button=view.findViewById(R.id.filterbutton)
        addingbutton=view.findViewById(R.id.FloatingAddButtonInMainFragment)

        //Uncompleted tasks
        val recycle:RecyclerView=view.findViewById(R.id.RecyclerViewInMainFragment)
        val taskadapter= TaskAdapter(list,usedviewmodel)
        recycle.adapter=taskadapter

       //Completed tasks
        val recyclecomplete:RecyclerView=view.findViewById(R.id.CompletedRecyclerViewInMainFragment)
        val completedadapter=TaskAdapter(completedlist,usedviewmodel)
        recyclecomplete.adapter=completedadapter

        //Show uncompleted





        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                usedviewmodel.deletetask(list[viewHolder.adapterPosition])
                list.removeAt(pos)
                taskadapter.notifyDataSetChanged()
            }
        }
        val TouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        TouchHelper.attachToRecyclerView(recycle)





        usedviewmodel.taskcontent.observe(viewLifecycleOwner, Observer { it?.let { items ->
            list.clear()
            list.addAll(items)



            var listafterreturn:MutableList<TaskDataModel> = getclosetoduedatetasks(list)
            listafterreturn.forEach {
                notificationid=counter

                createNotificationChannel("Notice","Due date is approaching",notificationid)

                counter++



            }
            taskadapter.notifyDataSetChanged()  } })











        //Show compeleted Tasks


        val swipeToDeleteCompletedCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                usedviewmodel.deletetask(completedlist[viewHolder.adapterPosition])
                completedlist.removeAt(pos)
                completedadapter.notifyDataSetChanged()
            }
        }

        val completeTouchHelper = ItemTouchHelper(swipeToDeleteCompletedCallback)
        completeTouchHelper.attachToRecyclerView(recyclecomplete)



        usedviewmodel.taskcompletecontent.observe(viewLifecycleOwner, Observer { it?.let { items ->
            completedlist.clear()
            completedlist.addAll(items)

            completedadapter.notifyDataSetChanged()  } })


















                //Filter tasks based on completion status
            filterbutton.setOnClickListener {

                if(recycle.visibility==VISIBLE)
                {
                    recycle.visibility= INVISIBLE
                    recyclecomplete.visibility= VISIBLE
                    filterbutton.setText("Show Incompleted Tasks")
                }
                else
                {
                    recycle.visibility= VISIBLE
                    recyclecomplete.visibility= INVISIBLE
                    filterbutton.setText("Show Completed Tasks")

                }

            }


        addingbutton.setOnClickListener {

                findNavController().navigate(R.id.action_mainFragment_to_addFragment)


            }


    }






    @RequiresApi(Build.VERSION_CODES.O)
    //Store all the uncompleted tasks that are close to their due date in a list to show a notification for each
    fun getclosetoduedatetasks(list:MutableList<TaskDataModel>):MutableList<TaskDataModel>
    {
        val daytocompareto=LocalDate.now()

       var returnedlist= mutableListOf<TaskDataModel>()

        list.forEach {
            if(it.due_Date!= "") {
                val theduedaytocompare = it.due_Date
                val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                var thefinalduedatetocompare = LocalDate.parse(theduedaytocompare, formatter)
                var daysbetween: Long = Duration.between(
                    daytocompareto.atStartOfDay(),
                    thefinalduedatetocompare.atStartOfDay()
                ).toDays()

                Log.d("daysbetween", daysbetween.toString())

                if (daysbetween.toString() == "0" && !it.task_Status) {
                    Log.d(" the list", "inside if")
                    returnedlist.add(it)
                }
            }

        }
        Log.d("The list", returnedlist.toString())
        return returnedlist

    }






        //Create the channel needed for setting notifications
    private fun createNotificationChannel(name:String,descriptionText:String,id:Int) {
        val notificationBuilder =
            NotificationCompat.Builder(requireContext(), Channelid)
                .setSmallIcon(R.drawable.exclamation)
                .setContentTitle("Due Date approaching")
                .setContentText("Some tasks are approaching their due date in 24 hours")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "SSSS"
            var descriptionText = "A text"
            var importance = NotificationManager.IMPORTANCE_DEFAULT
            var channel = NotificationChannel(Channelid, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getActivity()?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            with(NotificationManagerCompat.from(requireContext())) {
                // notificationId is a unique int for each notification that you must define
                notify(notificationid, notificationBuilder.build())
            }


        }

    }


















}