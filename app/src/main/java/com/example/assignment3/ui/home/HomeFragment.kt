package com.example.assignment3.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.assignment3.R
import com.example.assignment3.ui.gallery.NotificationActivity


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val name : EditText = view.findViewById(R.id.editText1)
        val age : EditText = view.findViewById(R.id.editText2)
        val result: TextView = view.findViewById(R.id.textView6)
        val saveButton : Button = view.findViewById(R.id.saveButton)
        var value : String = ""

        var flag : String = "A+"
        val spinnerValue : Spinner = view.findViewById(R.id.spinner2)
//        var options = arrayOf("Sum","Subtract","Multiply","Divide")
        var options = arrayOf("A+","A-","B+","B-", "O+", "O-","AB+","AB-")

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerValue.adapter = adapter
        }

//        spinnerValue.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        spinnerValue.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                flag = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        saveButton.setOnClickListener {
            value = "Name: " + name.text.toString() + "\nAge: " + age.text.toString() + "\nBlood Group: " + flag
            result.text="Saved Information:\n\n$value"

            val intent = Intent(activity, NotificationActivity::class.java).apply {
                putExtra("value", value)
            }

            // Start SecondActivity
            startActivity(intent)
        }


        return view
    }


}