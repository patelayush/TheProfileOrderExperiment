package com.example.theprofileorderexperiment.View

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.theprofileorderexperiment.Model.DataModel.User
import com.example.theprofileorderexperiment.R
import com.example.theprofileorderexperiment.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var users: Array<User>? = null
        val viewModel = ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)
        viewModel.getConfig(this@MainActivity)
        viewModel.getUsers(this@MainActivity).observe(this, Observer { usersResponse ->
            if (usersResponse != null && usersResponse.isNotEmpty()) {
                users = usersResponse
                if (savedInstanceState == null) {
                    loader_layout.visibility = View.GONE
                    root_layout.visibility = View.VISIBLE
                    val bundle = Bundle()
                    bundle.putParcelable("user", users!![index++])
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        add<ProfileFragment>(
                            R.id.fragment_container_view, args = bundle
                        )
                    }
                }
            }
        })
        nextButton.setOnClickListener {
            val bundle = Bundle()
            if (users != null) {
                if (index < users!!.size) {
                    bundle.putParcelable("user", users!![index++])
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<ProfileFragment>(
                            R.id.fragment_container_view, args = bundle
                        )
                    }
                } else {
                    Toast.makeText(this, "Reached the end of the list", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        index--
        if (index == 0) {
            finish()
        }
    }
}