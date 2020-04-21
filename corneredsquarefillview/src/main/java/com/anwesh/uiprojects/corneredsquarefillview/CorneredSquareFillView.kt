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
val sizeFactor : Float = 2.9f
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")
