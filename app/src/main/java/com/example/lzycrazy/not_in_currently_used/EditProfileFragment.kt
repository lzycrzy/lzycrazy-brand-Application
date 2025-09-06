package com.example.lzycrazy.not_in_currently_used

import android.R
import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.lzycrazy.not_in_currently_used.ThumbnailAdapter
import com.example.lzycrazy.databinding.FragmentEditProfileBinding

class EditProfileDialogFragment : DialogFragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // Set the dialog width to match the design
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(R.color.transparent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.apply {
            setDimAmount(0.5f) // Slight blur/dim
            setBackgroundDrawableResource(R.color.transparent)
        }
        return dialog
    }

}

class PropertyImagesFragment : Fragment(com.example.lzycrazy.R.layout.fragment_property_images) {

    private lateinit var viewPager: ViewPager2
    private lateinit var thumbnailsRecyclerView: RecyclerView

    private val imageList = listOf(
        com.example.lzycrazy.R.drawable.sample_property1,
        com.example.lzycrazy.R.drawable.sample_property2,
        com.example.lzycrazy.R.drawable.sample_property3,
        com.example.lzycrazy.R.drawable.sample_property4,
        com.example.lzycrazy.R.drawable.sample_property5
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(com.example.lzycrazy.R.id.imageViewPager)
        thumbnailsRecyclerView = view.findViewById(com.example.lzycrazy.R.id.imageThumbnailsRecyclerView)

        // Setup ViewPager
        viewPager.adapter = ImageSliderAdapter(imageList)

        // Setup thumbnails RecyclerView
        thumbnailsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        thumbnailsRecyclerView.adapter = ThumbnailAdapter(imageList) { position ->
            viewPager.currentItem = position
        }
    }
}