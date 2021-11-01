package com.example.tuwaiqcapstone1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tuwaiqcapstone1.Models.TaskDataModel
import com.example.tuwaiqcapstone1.Models.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.app.Dialog
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.app.AlertDialog


class DetailFragment : Fragment() {

val usedviewmodel:TaskViewModel by activityViewModels()
    lateinit var listoftasks: TaskDataModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var title:TextView=view.findViewById(R.id.TaskNameInDetailFragment)
        var detailedstate:TextView=view.findViewById(R.id.CompletionStatusInDetailFragment)
        var detailedcreationframe:TextView=view.findViewById(R.id.CreationDateInDetailFragment)
        var detaileddueframe:TextView=view.findViewById(R.id.DueDateInDetailFragment)
        var detaildescription:TextView=view.findViewById(R.id.TaskDescriptionInDetailFragment)
        var editbutton:FloatingActionButton=view.findViewById(R.id.FloatingEditButtonInDetailFragment)
        var deletebutton:FloatingActionButton=view.findViewById(R.id.FloatingDeleteButtonInDetailFragment)
       usedviewmodel.selectmutablelivedata.observe(viewLifecycleOwner, Observer { it?.let {
           tasks->
            title.text=tasks.task_Name
           if (tasks.task_Status==true)
           {
               detailedstate.setText("Completed")
           }
           else
           {
               detailedstate.setText("Incompleted")
           }

           detaileddueframe.text=tasks.due_Date
           detailedcreationframe.text=tasks.creation_Date
           detaildescription.text=tasks.task_Description
            listoftasks=tasks

       } })
        editbutton.setOnClickListener {
            usedviewmodel.selectmutablelivedata.postValue(listoftasks)
            findNavController().navigate(R.id.action_detailFragment_to_editFragment)

        }


        //https://www.javatpoint.com/kotlin-android-alertdialog

        deletebutton.setOnClickListener {

            val alertbuilder= AlertDialog.Builder(requireContext())

            alertbuilder.setTitle("Delete Notice")
            alertbuilder.setMessage("This task will be deleted.This cannot be undone.\n Are you sure?")
            alertbuilder.setPositiveButton("Yes"){dialogInterface, which ->
                usedviewmodel.deletetask(listoftasks)
                findNavController().popBackStack()
            }

            alertbuilder.setNegativeButton("No"){dialogInterface, which ->

            }
            val thedialog: AlertDialog =alertbuilder.create()
            thedialog.setCancelable(false)
            thedialog.show()
        }
    }


}