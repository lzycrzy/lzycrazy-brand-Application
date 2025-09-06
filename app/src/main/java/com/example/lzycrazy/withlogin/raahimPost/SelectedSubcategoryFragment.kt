package com.example.lzycrazy.withlogin.raahimPost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lzycrazy.R

class SelectedSubcategoryFragment : Fragment() {

    companion object {
        private const val ARG_CAT = "arg_cat"
        private const val ARG_SUB = "arg_sub"

        fun newInstance(category: String, subcategory: String) =
            SelectedSubcategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CAT, category)
                    putString(ARG_SUB, subcategory)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_selected_subcategory, container, false)
        val tv: TextView = v.findViewById(R.id.tvSelected)
        val cat = arguments?.getString(ARG_CAT).orEmpty()
        val sub = arguments?.getString(ARG_SUB).orEmpty()
        tv.text = "Selected: $cat → $sub"
        return v
    }
}
