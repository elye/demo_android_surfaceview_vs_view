package com.elyeproj.surfaceviewexplore

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch

class SlowSurfaceView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0)
    : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    private var job: Job? = null
    private var doAnimate = true
    private val drawAnimate by lazy {
        DrawAnimate(height, width)
    }

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Do nothing for now
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        doAnimate = false
        job?.cancel()
        job = null
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        doAnimate = true
        job = launch {
            while (doAnimate && isAttachedToWindow) {
                synchronized(holder) {
                    val canvas = holder.lockCanvas()
                    canvas?.let {
                        drawAnimate.draw(it)
                        holder.unlockCanvasAndPost(it)
                    }
                }
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(View.resolveSize(desiredWidth, widthMeasureSpec),
                View.resolveSize(desiredHeight, heightMeasureSpec))
    }
}
