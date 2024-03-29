package com.example.spacexapp.activity

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import com.example.spacexapp.R


class SplashScreenActivity : AppCompatActivity() {


    private val mDelayHandler by lazy {Handler()}

    companion object{
        private const val SPLASH_DELAY: Long = 3000
    }

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        onStartAnimation()

        //Navigate with delay
        mDelayHandler.postDelayed(mRunnable, SPLASH_DELAY)

    }

    private fun onStartAnimation() {

        val valueAnimator = ValueAnimator.ofFloat(0f, -getScreenHeight())

        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            rocket.translationY = value
        }

        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 2500
        valueAnimator.start()
    }


    public override fun onDestroy() {
        mDelayHandler.removeCallbacks(mRunnable)
        super.onDestroy()
    }


    private fun getScreenHeight(): Float {
        //Get screen height
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels.toFloat()
    }


}