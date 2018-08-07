package com.elyeproj.surfaceviewexplore

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class SlowView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private val drawAnimate by lazy {
        DrawAnimate(height, width)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawAnimate.draw(canvas)
        if (isAttachedToWindow) invalidate()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(View.resolveSize(desiredWidth, widthMeasureSpec),
                View.resolveSize(desiredHeight, heightMeasureSpec))
    }
}
