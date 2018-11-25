package com.lee.doubanmovie.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lee.doubanmovie.R

class StatusLayout : FrameLayout {
    companion object {
        private const val LOADING = 1
        private const val EMPTY = 2
        private const val ERROR = 3
    }

    private var errorView: View? = null
    private var emptyView: View? = null
    private var loadingView: View? = null
    private var mState: Int = 0
    private var mLayoutInflater: LayoutInflater
    private var mRetryListener: OnClickListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        mLayoutInflater = LayoutInflater.from(context)
    }

    fun setOnRetryClickListener(listener: OnClickListener) {
        mRetryListener = listener
    }

    fun showLoading() {
        inflaterLoadingView()
        showView(loadingView)
        mState = LOADING
    }

    fun showEmpty() {
        inflaterEmptyView()
        showView(emptyView)
        mState = EMPTY
    }

    fun showError() {
        inflaterErrorView()
        showView(errorView)
        mState = ERROR
    }

    fun showContent() {
        for (i in 0 until childCount) {
            when (getChildAt(i)) {
                errorView, emptyView, loadingView -> {
                    getChildAt(i).visibility = View.GONE
                }
                else -> {
                    getChildAt(i).visibility = View.VISIBLE
                }
            }
        }
    }

    private fun inflaterLoadingView() {
        if (loadingView == null) {
            loadingView = mLayoutInflater.inflate(R.layout.layout_loading, this, false)
            addView(loadingView)
        }
    }

    private fun inflaterEmptyView() {
        if (emptyView == null) {
            emptyView = mLayoutInflater.inflate(R.layout.layout_empty, this, false)
            addView(emptyView)
            emptyView?.findViewById<FloatingActionButton>(R.id.refresh_btn)?.setOnClickListener {
                mRetryListener?.onClick(it)
            }
        }
    }

    private fun inflaterErrorView() {
        if (errorView == null) {
            errorView = LayoutInflater.from(context).inflate(R.layout.layout_error, this, false)
            addView(errorView)
            errorView?.findViewById<FloatingActionButton>(R.id.refresh_btn)?.setOnClickListener {
                mRetryListener?.onClick(it)
            }
        }
    }

    private fun showView(v: View?) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.visibility = if (child == v) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}