package com.example.tuwaiqcapstone1

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tuwaiqcapstone1.Models.TaskDataModel
import com.example.tuwaiqcapstone1.Models.TaskViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class AddFragment : Fragment() {

    val usedviewmodel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val nameedittext:EditText=view.findViewById(R.id.TaskNameInAddFragment)
        //https://www.geeksforgeeks.org/datepicker-in-kotlin/#
        val duedateedittext:DatePicker=view.findViewById(R.id.DueDateInAddFragment)
        val statusedit:CheckBox=view.findViewById(R.id.StatusInAddFragment)
        val descriptionedit:EditText=view.findViewById(R.id.TaskDescriptionInAddFragment)
        val addtaskbutton: Button =view.findViewById(R.id.AddButtonInAddFragment)
//https://www.ictdemy.com/kotlin/oop/date-and-time-in-kotlin-modifying-and-intervals


        addtaskbutton.setOnClickListener {
            var name=nameedittext.text.toString()


            val dudatetobeinserted=LocalDate.of(duedateedittext.year,duedateedittext.month+1,duedateedittext.dayOfMonth)
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            var dateastext:String=dudatetobeinserted.format(formatter)


            var dudate=dateastext
            var status=statusedit.isChecked
            var description=descriptionedit.text.toString()

                if(name.isNotEmpty())
                {
                    usedviewmodel.addatask(name,status,dudate,description)
                    findNavController().popBackStack()
                }


        }

    }


}