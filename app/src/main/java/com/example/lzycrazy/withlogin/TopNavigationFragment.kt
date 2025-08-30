//package com.example.lzycrazy.withlogin
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
//import com.example.lzycrazy.R
//
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
//class TopNavigationFragment : Fragment() {
//
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_top_navigation, container, false)
//
//        // ✅ handle message icon click -> navigate to ChatsFragment
//        val messageIcon = view.findViewById<ImageView>(R.id.messageIcon)
//        messageIcon.setOnClickListener {
//            requireParentFragment()
//                .findNavController()
//                .navigate(R.id.action_homeFragment_to_chatsFragment)
//        }
//
//        return view
//    }
//
//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            TopNavigationFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}

package com.example.lzycrazy.withlogin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.lzycrazy.R

class TopNavigationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the message icon
        val messageIcon: ImageView = view.findViewById(R.id.messageIcon)

        // Handle click
        messageIcon.setOnClickListener {
            Log.d("TopNavigationFragment", "Message icon clicked ✅")

            try {
                // Use parent fragment's NavController
                val navController = requireParentFragment()
                    .parentFragmentManager
                    .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

                navController.navController.navigate(R.id.action_homeFragment_to_chatsFragment)

            } catch (e: Exception) {
                Log.e("TopNavigationFragment", "Navigation failed ❌", e)
            }
        }
    }
}



