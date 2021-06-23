package com.example.theprofileorderexperiment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val rootLayout = view.findViewById<LinearLayout>(R.id.root_layout)
        showName(rootLayout)
        showProfilePhoto(rootLayout)
        showGender(rootLayout)
        showAbout(rootLayout)
        showSchool(rootLayout)
        showHobbies(rootLayout)
        return view
    }

    private fun showGender(rootLayout: LinearLayout) {
        val genderLayout = LayoutInflater.from(context).inflate(R.layout.layout_gender, null)
        rootLayout.addView(genderLayout)
    }

    private fun showAbout(rootLayout: LinearLayout) {
        val aboutLayout = LayoutInflater.from(context).inflate(R.layout.layout_about, null)
        rootLayout.addView(aboutLayout)
    }

    private fun showHobbies(rootLayout: LinearLayout) {
        val hobbiesLayout = LayoutInflater.from(context).inflate(R.layout.layout_hobbies, null)
        rootLayout.addView(hobbiesLayout)
    }

    private fun showSchool(rootLayout: LinearLayout) {
        val schoolLayout = LayoutInflater.from(context).inflate(R.layout.layout_school, null)
        rootLayout.addView(schoolLayout)
    }

    private fun showProfilePhoto(rootLayout: LinearLayout) {
        val profilePhotoLayout = LayoutInflater.from(context).inflate(R.layout.layout_profile_picture, null)
        rootLayout.addView(profilePhotoLayout)
    }

    private fun showName(rootLayout: LinearLayout) {
        val nameLayout = LayoutInflater.from(context).inflate(R.layout.layout_name, null)
        rootLayout.addView(nameLayout)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}