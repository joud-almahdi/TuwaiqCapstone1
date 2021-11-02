package com.example.tuwaiqcapstone1


import android.app.DatePickerDialog
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
import android.os.Build
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var edittaskname:EditText=view.findViewById(R.id.TaskNameInEditFragment)
        var editduedatebutton: Button =view.findViewById(R.id.DueDateInEditFragment)
        var editduedatetext: EditText =view.findViewById(R.id.DueDateEditTextInEditFragment)
        var editdesc:EditText=view.findViewById(R.id.TaskDescriptionInEditFragment)
        var check:CheckBox=view.findViewById(R.id.StatusInEditFragment)
        var editbutton: Button =view.findViewById(R.id.EditButtonInEditFragment)



        //https://developer.android.com/reference/kotlin/android/app/DatePickerDialog
        //https://androidride.com/open-calendar-on-button-click-in-android-example-kotlin-java/#

        val calendar=Calendar.getInstance()
        val year = Calendar.YEAR
        val month = calendar.get(Calendar.MONTH)
         val day = Calendar.DAY_OF_MONTH
        editduedatebutton.setOnClickListener{
            val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener
            { view, year, monthOfYear, dayOfMonth ->

            }, year, month, day)
            datePickerDialog.datePicker.minDate=calendar.timeInMillis
            datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                editduedatetext.setText((LocalDate.of(year,month+1,dayOfMonth)).toString())

            }
            datePickerDialog.show()
        }




        usedviewmodel.selectmutablelivedata.observe(viewLifecycleOwner, Observer {
                items->
            print(items)
            edittaskname.setText(items.task_Name)
            editduedatetext.setText(items.due_Date)
            editdesc.setText(items.task_Description)
            check.isChecked=items.task_Status
            //****************************
            listoftasks=items

        })

        editbutton.setOnClickListener {
            listoftasks.task_Name=edittaskname.text.toString()


            val dudatetobeinserted=editduedatetext.text.toString()
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            var dateastext:String=dudatetobeinserted.format(formatter)


            var dudate=dateastext
            listoftasks.due_Date=dudate.toString()
            listoftasks.task_Description=editdesc.text.toString()
            listoftasks.task_Status=check.isChecked
            if(edittaskname.text.isNotEmpty())
            {
                usedviewmodel.updatetask(listoftasks)

                findNavController().popBackStack()
            }









        }





    }


}