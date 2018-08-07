package com.elyeproj.surfaceviewexplore

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class DrawAnimate(private val height: Int, private val width: Int) {

    private var startPoint = 0f
    private val strokePaint = Paint()
            .apply { color = Color.RED }
            .apply { strokeWidth = 16f }


    companion object {
        private const val SIZE = 50f
        private const val SLEEP = 500L
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(startPoint, height/2 - SIZE/2, startPoint + SIZE, height/2 + SIZE/2, strokePaint)
        Thread.sleep(SLEEP)
        startPoint += SIZE
        if (startPoint > width) { startPoint = 0f }

    }
}
