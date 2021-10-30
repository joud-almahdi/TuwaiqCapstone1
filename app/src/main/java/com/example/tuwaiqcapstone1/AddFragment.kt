package com.example.tuwaiqcapstone1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tuwaiqcapstone1.Models.TaskDataModel
import com.example.tuwaiqcapstone1.Models.TaskViewModel


class AddFragment : Fragment() {

    val usedviewmodel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val nameedittext:EditText=view.findViewById(R.id.TaskNameInAddFragment)
        val duedateedittext:EditText=view.findViewById(R.id.DueDateInAddFragment)
        val statusedit:CheckBox=view.findViewById(R.id.StatusInAddFragment)
        val descriptionedit:EditText=view.findViewById(R.id.TaskDescriptionInAddFragment)
        val addtaskbutton: Button =view.findViewById(R.id.AddButtonInAddFragment)

        addtaskbutton.setOnClickListener {
            var name=nameedittext.text.toString()
            var dudate=duedateedittext.text.toString()
            var status=statusedit.isChecked
            var description=descriptionedit.text.toString()


                usedviewmodel.addatask(name,status,dudate,description)
                findNavController().popBackStack()

        }

    }


}