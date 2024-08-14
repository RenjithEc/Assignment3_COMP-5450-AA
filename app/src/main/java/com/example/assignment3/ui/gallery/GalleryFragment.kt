package com.example.assignment3.ui.gallery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.assignment3.R
import com.example.assignment3.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    interface OnItemSelectedListener {
        fun onItemSelected(item: String)
    }

    private var itemSelectedListener: OnItemSelectedListener? = null
    private lateinit var textViewSelectedItem: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        textViewSelectedItem = view.findViewById(R.id.textView2)
        val dialogButton : Button = view.findViewById(R.id.button)
        dialogButton.setOnClickListener {
            showDialog()
        }

        return view
    }

    private fun showDialog(){
        var selectedItem: String="";
        val itemsArray =  arrayOf("A+","A-","B+","B-", "O+", "O-","AB+","AB-");
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder
            .setTitle("Select your Blood Group")
            .setPositiveButton("Submit") { dialog, which ->
                itemSelectedListener?.onItemSelected(selectedItem)
                Toast.makeText(requireContext(), "Blood Group successfully selected", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                Toast.makeText(requireContext(), "Blood Group not selected", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setSingleChoiceItems(
                itemsArray, 0
            ) { dialog, which ->
                selectedItem = itemsArray[which]
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(item: String) {
                textViewSelectedItem.text = "Selected Blood Group: $item"
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        itemSelectedListener = null
    }

}