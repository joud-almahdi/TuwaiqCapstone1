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
import com.example.tuwaiqcapstone1.Models.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class DetailFragment : Fragment() {

val usedmodel:TaskViewModel by activityViewModels()

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

       usedmodel.selectmutablelivedata.observe(viewLifecycleOwner, Observer { it?.let {
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


       } })
        editbutton.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_editFragment)

        }
    }


}