package com.elyeproj.surfaceviewexplore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.slow_surfaceview_animation
import kotlinx.android.synthetic.main.activity_main.slow_view_animation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        slow_view_animation.setOnClickListener {
            startActivity(Intent(this, SlowViewActivity::class.java))
        }

        slow_surfaceview_animation.setOnClickListener {
            startActivity(Intent(this, SlowSurfaceViewActivity::class.java))
        }
    }
}
