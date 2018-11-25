package com.lee.doubanmovie.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.lee.doubanmovie.R

class RatioFixedImageView: ImageView {
    private var ratio = 1f
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.RatioFixedImageView)
        ratio = a.getFloat(R.styleable.RatioFixedImageView_rfi_ratio, 1f)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val h = Math.round(w / ratio + 0.5f)
        setMeasuredDimension(w, h)
    }
}