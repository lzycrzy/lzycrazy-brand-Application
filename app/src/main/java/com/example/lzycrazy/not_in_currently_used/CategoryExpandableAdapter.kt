package com.example.lzycrazy.not_in_currently_used

import android.R
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class CategoryExpandableAdapter(
    private val context: Context,
    private val titleList: List<String>,
    private val dataList: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int = titleList.size
    override fun getChildrenCount(groupPosition: Int): Int = dataList[titleList[groupPosition]]?.size ?: 0
    override fun getGroup(groupPosition: Int): Any = titleList[groupPosition]
    override fun getChild(groupPosition: Int, childPosition: Int): Any = dataList[titleList[groupPosition]]?.get(childPosition) ?: ""
    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()
    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()
    override fun hasStableIds(): Boolean = false
    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        val textView = TextView(context)
        textView.text = getGroup(groupPosition).toString()
        textView.setTypeface(null, Typeface.BOLD)
        textView.textSize = 18f
        textView.setPadding(64, 32, 64, 32)
        textView.setTextColor(Color.BLACK)
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
            if (isExpanded) R.drawable.arrow_down_float else R.drawable.ic_media_play, 0)
        textView.compoundDrawablePadding = 24
        return textView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        val textView = TextView(context)
        textView.text = getChild(groupPosition, childPosition).toString()
        textView.setPadding(0, 24, 0, 24)
        textView.gravity = Gravity.CENTER
        textView.setTextColor(Color.GRAY)
        textView.textSize = 16f
        return textView
    }
}

class PropertyDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.lzycrazy.R.layout.fragment_property_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Map image click handler
        val mapImage = view.findViewById<ImageView>(com.example.lzycrazy.R.id.mapImage)
        mapImage.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:28.523445,77.221012?q=The Duke Of Wellington")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")

            // Check if Google Maps is installed
            if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(mapIntent)
            } else {
                Toast.makeText(requireContext(), "Google Maps app not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}