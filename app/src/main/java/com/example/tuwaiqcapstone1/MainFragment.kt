package com.example.tuwaiqcapstone1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tuwaiqcapstone1.Adapter.TaskAdapter
import com.example.tuwaiqcapstone1.Models.TaskDataModel
import com.example.tuwaiqcapstone1.Models.TaskViewModel
 import androidx.lifecycle.LifecycleOwner
import  androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainFragment : Fragment() {


    val usedviewmodel:TaskViewModel by activityViewModels()
    var list= mutableListOf<TaskDataModel>()
    lateinit var addingbutton: FloatingActionButton



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycle:RecyclerView=view.findViewById(R.id.RecyclerViewInMainFragment)
        addingbutton=view.findViewById(R.id.FloatingAddButtonInMainFragment)
        val taskadapter= TaskAdapter(list,usedviewmodel)

        recycle.adapter=taskadapter
        usedviewmodel.taskcontent.observe(viewLifecycleOwner, Observer { it?.let { items ->list.clear()
            list.addAll(items)

            taskadapter.notifyDataSetChanged()  } })


            addingbutton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_addFragment)

            }


    }


}