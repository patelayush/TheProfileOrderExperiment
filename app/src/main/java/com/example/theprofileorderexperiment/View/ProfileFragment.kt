package com.example.theprofileorderexperiment.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.theprofileorderexperiment.Model.DataModel.User
import com.example.theprofileorderexperiment.R
import com.example.theprofileorderexperiment.ViewModel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user: User? = requireArguments().getParcelable("user")
        val viewModel = activity?.let { ViewModelProvider(it).get(MainViewModel::class.java) }
        activity?.let {
            viewModel?.getConfig(it)?.observe(it, Observer { profileOrder ->
                if (profileOrder != null) {
                    for (item in profileOrder) {
                        when (item) {
                            "name" -> showName(root_layout, user)
                            "photo" -> showProfilePhoto(root_layout, user)
                            "gender" -> showGender(root_layout, user)
                            "about" -> showAbout(root_layout, user)
                            "school" -> showSchool(root_layout, user)
                            "hobbies" -> showHobbies(root_layout, user)
                        }
                    }
                }
            })
        }
    }

    private fun showGender(rootLayout: LinearLayout, user: User?) {
        val genderLayout = LayoutInflater.from(context).inflate(R.layout.layout_gender, null)
        val genderTextView = genderLayout.findViewById<TextView>(R.id.genderTextView)
        if (user?.gender.equals("m")) {
            genderTextView.text = "Male"
            rootLayout.addView(genderLayout)
        } else if (user?.gender.equals("f")) {
            genderTextView.text = "Female"
            rootLayout.addView(genderLayout)
        }
    }

    private fun showAbout(rootLayout: LinearLayout, user: User?) {
        val aboutLayout = LayoutInflater.from(context).inflate(R.layout.layout_about, null)
        val aboutTextView = aboutLayout.findViewById<TextView>(R.id.aboutTextView)
        if (user?.about != null) {
            aboutTextView.text = user?.about
            rootLayout.addView(aboutLayout)
        }
    }

    private fun showHobbies(rootLayout: LinearLayout, user: User?) {
        val hobbiesLayout = LayoutInflater.from(context).inflate(R.layout.layout_hobbies, null)
        val hobbiesTextView = hobbiesLayout.findViewById<TextView>(R.id.hobbiesTextView)
        if (user?.hobbies != null && user.hobbies.isNotEmpty()) {
            var index = 0
            for (hobby in user.hobbies) {
                if (index == 0) {
                    hobbiesTextView.append(hobby)
                } else {
                    hobbiesTextView.append(", $hobby")
                }
                index++
            }
            rootLayout.addView(hobbiesLayout)
        }
    }

    private fun showSchool(rootLayout: LinearLayout, user: User?) {
        val schoolLayout = LayoutInflater.from(context).inflate(R.layout.layout_school, null)
        val schoolTextView = schoolLayout.findViewById<TextView>(R.id.schoolTextView)
        if (user?.school != null) {
            schoolTextView.text = user.school
            rootLayout.addView(schoolLayout)
        }
    }

    private fun showProfilePhoto(rootLayout: LinearLayout, user: User?) {
        val profilePhotoLayout =
            LayoutInflater.from(context).inflate(R.layout.layout_profile_picture, null)
        val profileImageView = profilePhotoLayout.findViewById<ImageView>(R.id.profile_iv)
        if (user?.photo != null) {
            Picasso.get().load(user.photo).into(profileImageView)
            rootLayout.addView(profilePhotoLayout)
        }
    }

    private fun showName(rootLayout: LinearLayout, user: User?) {
        val nameLayout = LayoutInflater.from(context).inflate(R.layout.layout_name, null)
        val nameTextView = nameLayout.findViewById<TextView>(R.id.nameTextView)
        nameTextView.text = user?.name
        rootLayout.addView(nameLayout)
    }
}