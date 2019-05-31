package com.example.kotlindrawing

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class DrawView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var mPaint = Paint()
    private var mPath = Path()
    private var bgPath = Path()
    private var bgPaint = Paint()

    private var mCurX = 0f
    private var mCurY = 0f
    private var mStartX = 0f
    private var mStartY = 0f

    init {
        mPaint.apply {
            color = Color.WHITE
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 16f
            isAntiAlias = true
        }
        bgPaint.apply {
            color = Color.BLACK
            style = Paint.Style.FILL
            isAntiAlias = false
        }

        clearCanvas()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        clearCanvas()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mStartX = x
                mStartY = y
                actionDown(x, y)
            }
            MotionEvent.ACTION_MOVE -> actionMove(x, y)
            MotionEvent.ACTION_UP -> actionUp()
        }

        invalidate()
        return true
    }

    private fun actionDown(x: Float, y: Float) {
        mPath.moveTo(x, y)
        mCurX = x
        mCurY = y
    }

    private fun actionMove(x: Float, y: Float) {
        mPath.quadTo(mCurX, mCurY, (x + mCurX) / 2, (y + mCurY) / 2)
        mCurX = x
        mCurY = y
    }

    private fun actionUp() {
        mPath.lineTo(mCurX, mCurY)

        // draw a dot on click
        if (mStartX == mCurX && mStartY == mCurY) {
            mPath.lineTo(mCurX, mCurY + 2)
            mPath.lineTo(mCurX + 1, mCurY + 2)
            mPath.lineTo(mCurX + 1, mCurY)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(bgPath, bgPaint)
        canvas.drawPath(mPath, mPaint)
    }

    fun clearCanvas() {
        mPath.reset()
        bgPath.reset()
        bgPath.addRect(0f, 0f, this.width.toFloat(), this.height.toFloat(), Path.Direction.CW)
        invalidate()
    }
}