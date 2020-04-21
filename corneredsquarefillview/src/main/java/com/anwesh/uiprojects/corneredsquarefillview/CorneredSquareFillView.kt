package com.anwesh.uiprojects.corneredsquarefillview

/**
 * Created by anweshmishra on 22/04/20.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.graphics.RectF
import android.app.Activity
import android.content.Context

val nodes : Int = 5
val squares : Int = 4
val colors : Array<String> = arrayOf("#2196F3", "#F44336", "#9C27B0", "#FF5722", "#4CAF50")
val scGap : Float = 0.02f
val strokeFactor : Float = 90f
val sizeFactor : Float = 5.6f
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(),maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawCorneredSquareFill(i : Int, w : Float, scale : Float, size : Float, paint : Paint) {
    val sf : Float = scale.sinify().divideScale(i, squares)
    val sf1 : Float = sf.divideScale(0, 2)
    val sf2 : Float = sf.divideScale(1, 2)
    save()
    rotate(90f * i)
    translate(-w / 2, -w / 2)
    drawRect(RectF(0f, 0f, size * sf1, size), paint)
    drawLine(size / 2, size / 2, (w - 2 * size) * sf2, size / 2, paint)
    restore()
}

fun Canvas.drawCorneredSquaresFill(w : Float, scale : Float, size : Float, paint : Paint) {
    for (j in 0..(squares - 1)) {
        drawCorneredSquareFill(j, w, scale, size, paint)
    }
}

fun Canvas.drawCSFNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val size : Float = Math.min(w, h) / sizeFactor
    paint.color = Color.parseColor(colors[i])
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    save()
    translate(w / 2, h / 2)
    drawCorneredSquaresFill(w, scale, size, paint)
    restore()
}

class CorneredSquareFillView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {

        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += scGap * dir
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}