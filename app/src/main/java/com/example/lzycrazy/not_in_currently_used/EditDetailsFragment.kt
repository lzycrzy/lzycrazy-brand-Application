package com.example.lzycrazy.not_in_currently_used

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD:app/src/main/java/com/example/lzycrazy/EditDetailsFragment.kt
=======
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
>>>>>>> 3818ee7e7ac955f3076add2364d7e5ed85f9236b:app/src/main/java/com/example/lzycrazy/not_in_currently_used/EditDetailsFragment.kt
import androidx.fragment.app.Fragment
import com.example.lzycrazy.R

class EditDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate your XML for Edit Details screen
        return inflater.inflate(R.layout.fragment_edit_details, container, false)
    }
}