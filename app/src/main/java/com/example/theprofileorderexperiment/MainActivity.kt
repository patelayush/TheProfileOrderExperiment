package com.example.theprofileorderexperiment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.OneShotPreDrawListener.add
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ProfileFragment>(R.id.fragment_container_view)
            }
        }
    }
}