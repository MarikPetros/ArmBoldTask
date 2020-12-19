package com.marik.armboldtask.utils


import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.marik.armboldtask.R


class DownArrowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {
    private val paint = Paint(ANTI_ALIAS_FLAG).apply {
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.MITER
        strokeWidth = 2F
        color = ResourcesCompat.getColor(resources, R.color.white, null)
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (width == 0 || height == 0) return

        canvas.drawLines(
            listOf(
                0f + paddingStart,
                1f + paddingTop,
                (width / 2f) - 2f,
                (height - paddingBottom).toFloat(),
                (width / 2f) - 2f,
                (height - paddingBottom).toFloat(),
                (width - paddingEnd).toFloat(),
                (0f + paddingStart)
            ).toFloatArray(),
            paint
        )
    }
}