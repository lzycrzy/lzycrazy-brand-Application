package com.example.lzycrazy

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

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
            if (isExpanded) android.R.drawable.arrow_down_float else android.R.drawable.ic_media_play, 0)
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
