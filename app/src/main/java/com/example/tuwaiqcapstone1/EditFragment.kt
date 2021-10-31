package com.example.tuwaiqcapstone1


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tuwaiqcapstone1.Models.TaskDataModel
import com.example.tuwaiqcapstone1.Models.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.app.Dialog
import android.widget.*
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat


class EditFragment : Fragment() {

    val usedviewmodel:TaskViewModel by activityViewModels()
    lateinit var listoftasks: TaskDataModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var edittaskname:EditText=view.findViewById(R.id.TaskNameInEditFragment)
        var editduedate: DatePicker =view.findViewById(R.id.DueDateInEditFragment)
        var editdesc:EditText=view.findViewById(R.id.TaskDescriptionInEditFragment)
        var check:CheckBox=view.findViewById(R.id.StatusInEditFragment)
        var editbutton: Button =view.findViewById(R.id.EditButtonInEditFragment)

        usedviewmodel.selectmutablelivedata.observe(viewLifecycleOwner, Observer {
                items->
            print(items)
            edittaskname.setText(items.task_Name)

            var stringdate= SimpleDateFormat("dd/MM/yyyy").parse(items.due_Date)
            editdesc.setText(items.task_Description)
            check.isChecked=items.task_Status
            //****************************
            listoftasks=items

        })

        editbutton.setOnClickListener {
            listoftasks.task_Name=edittaskname.text.toString()
            var dudate:String="${editduedate.dayOfMonth}/${editduedate.month+1}/${editduedate.year}"
            listoftasks.due_Date=dudate.toString()
            listoftasks.task_Description=editdesc.text.toString()
            listoftasks.task_Status=check.isChecked
            usedviewmodel.updatetask(listoftasks)

            findNavController().popBackStack()








        }





    }


}