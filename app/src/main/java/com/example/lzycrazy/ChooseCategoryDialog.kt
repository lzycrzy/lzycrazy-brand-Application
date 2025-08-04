package com.example.lzycrazy
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class ChooseCategoryDialogFragment : DialogFragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.dialog_choose_category, container, false)

            // Close icon click
            view.findViewById<ImageView>(R.id.closeIcon).setOnClickListener {
                dismiss()
            }

            // Find the "Check" button and open CheckDialogFragment
            val checkButton = view.findViewById<TextView>(R.id.btnCheckButton) // Make sure this ID matches
            checkButton.setOnClickListener {
                dismiss()
                CheckDialogFragment().show(parentFragmentManager, "CheckDialog")
            }

            return view
        }

        override fun onStart() {
            super.onStart()
            dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }


