package com.example.livedataapapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.*
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

private const val TAG = "FlightDetailActivity"
const val KEY_ID = "com.example.livedataapapter.id"

class FlightDetailActivity : AppCompatActivity() {
    private lateinit var mViewModel: FlightDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_detail)
//        val fadeIn = AlphaAnimation(0.8f, 0.2f)
//        fadeIn.interpolator = DecelerateInterpolator()
//        fadeIn.duration = 700
//
//        val fadeOut = AlphaAnimation(0.2f, 0.8f)
//        fadeOut.interpolator = AccelerateInterpolator()
//        fadeOut.duration = 700
//
//        val animSet = AnimationSet(false)
//        animSet.addAnimation(fadeIn)
//        animSet.addAnimation(fadeOut)
//        animSet.fillAfter = false
//        animSet.repeatMode = Animation.RESTART
        val alphaAnim = AnimationUtils.loadAnimation(this, R.anim.hide_show)
        val iv = findViewById<ImageView>(R.id.imageView)
//        iv.animation = animSet

//        alphaAnim = AnimationUtils.loadAnimation(this, R.anim.hide_show)
        mViewModel = ViewModelProvider(this).get(FlightDetailViewModel::class.java)
        mViewModel.item.observe(this, Observer {
            val descr = findViewById<TextView>(R.id.descricaoTextView)
            val local = findViewById<TextView>(R.id.localTextView)
            val timer = findViewById<TextView>(R.id.horarioTextView)

            descr.text = it.descricao
            local.text = it.localizacao
            timer.text = it.horario
        })

        mViewModel.isLoading.observe(this, Observer {
            Log.i(TAG, "Is Loading $it")
            if (iv.animation != null && iv.animation.hasStarted()) {
                iv.clearAnimation()
            }
            if (it) {
                iv.startAnimation(alphaAnim)
            }
            else {
                iv.clearAnimation()
            }
        })

        val flightId = intent.extras?.getLong(KEY_ID, 0L)
        flightId?.let {
            mViewModel.fetchItem(flightId)
        }

    }
}