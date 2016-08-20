package ru.chipenable.filemanager

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.TextView

class SelectableTextView : TextView {

    private var clickListener: OnClick? = null
    private lateinit var gestureDetector: GestureDetector

    interface OnClick {
        fun onClick(path: String)
    }

    constructor(context: Context) : super(context) {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    fun setOnWordClickListener(listener: OnClick) {
        clickListener = listener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean {

            val p = findSelectedWord(e)
            if (p != null) {
                val path = text.subSequence(p.start, p.end).toString()
                clickListener?.onClick(path)
            }

            return true
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return super.onSingleTapUp(e)
        }
    }

    private inner class Position internal constructor(var start: Int, var end: Int)

    private fun findSelectedWord(e: MotionEvent): Position? {
        val text = text.toString()
        val start = getOffsetForPosition(e.x, e.y)

        if (start >= text.length || start < 0) {
            return null
        }

        val ch = text[start]
        if (ch.compareTo('/') == 0) {
            return Position(0, 1)
        }

        val startPath = 0

        var endPath = text.indexOf('/', start)
        if (endPath == -1) {
            endPath = text.length
        }

        if (startPath >= endPath) {
            return null
        }

        return Position(startPath, endPath)
    }

}
