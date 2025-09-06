package com.example.lzycrazy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MarketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MarketFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // Declare views that will be initialized in onViewCreated

    private lateinit var videoThumbnail1: ImageView // Renamed for clarity
    private lateinit var videoThumbnail2: ImageView

    // Data for sliders - this is fine here
    private val sliderImages1 = listOf(
        R.drawable.slider1,
        R.drawable.slider2,
        R.drawable.slider3
    )

    private val sliderImages2 = listOf(
        R.drawable.slider4,
        R.drawable.placeholder,
        R.drawable.placeholder
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // This view will be passed to onViewCreated
        return inflater.inflate(R.layout.fragment_market, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoThumbnail1 = view.findViewById(R.id.videoThumbnail) // Corrected ID usage
        videoThumbnail2 = view.findViewById(R.id.videoThumbnail2)

        // Set click listeners
        videoThumbnail1.setOnClickListener {
            Toast.makeText(requireContext(), "Play video 1", Toast.LENGTH_SHORT).show()
        }

        videoThumbnail2.setOnClickListener {
            Toast.makeText(requireContext(), "Play video 2", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MarketFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MarketFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}