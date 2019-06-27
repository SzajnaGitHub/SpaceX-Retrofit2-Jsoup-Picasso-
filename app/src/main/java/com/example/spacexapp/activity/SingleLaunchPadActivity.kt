package com.example.spacexapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spacexapp.task.ImageDownloadTask
import com.example.spacexapp.R
import kotlinx.android.synthetic.main.activity_single_launchad.*

class SingleLaunchPadActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_launchad)

        //val init
        val imageUrl = intent.getStringExtra("lwikipedia")
        val longitude: Double = intent.getDoubleExtra("llongitude", 0.0)
        val latitude: Double = intent.getDoubleExtra("llatitude", 0.0)
        val name: String = intent.getStringExtra("lname")

        //textView initialization
        nameText.text = name
        status.text = intent.getStringExtra("lstatus")
        details.text = intent.getStringExtra("ldetails")
        region.text = intent.getStringExtra("lregion")
        longitudeText.text = longitude.toString()
        latitudeText.text = latitude.toString()
        attemptedLaunches.text = intent.getStringExtra("lattemptedLaunches")
        successfulLaunches.text = intent.getStringExtra("lsuccessfulLaunches")


        goToMapButton?.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("singleLatitude", latitude)
                intent.putExtra("singleLongitude", longitude)
                intent.putExtra("singleName", name)
            startActivity(intent)
        }


        ImageDownloadTask(imageView, singleLaunchPadProgressBar).execute(imageUrl)


    }
}