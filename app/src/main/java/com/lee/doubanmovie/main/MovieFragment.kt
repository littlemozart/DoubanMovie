package com.lee.doubanmovie.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.lee.doubanmovie.R
import com.lee.doubanmovie.adapter.MovieAdapter
import com.lee.doubanmovie.data.Movie
import kotlinx.android.synthetic.main.fragment_movie.*
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 *
 */
class MovieFragment : Fragment(), MovieContract.View {

    companion object {
        private const val COLUMNS = 2

        fun newInstance(): MovieFragment {
            return MovieFragment()
        }
    }

    override lateinit var presenter: MovieContract.Presenter
    private lateinit var mAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = MovieAdapter(context)
        recycler_view.layoutManager = GridLayoutManager(context!!, COLUMNS)
        recycler_view.adapter = mAdapter
        refresh_layout.setOnRefreshListener {
            presenter.refresh()
        }
        refresh_layout.setOnLoadMoreListener {
            presenter.loadMore()
        }
        state_layout.setOnRetryClickListener(View.OnClickListener {
            presenter.start()
        })
        presenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.release()
    }

    override fun onRefreshFinished(success: Boolean) {
        refresh_layout.finishRefresh(success)
    }

    override fun onLoadMoreFinished(success: Boolean) {
        refresh_layout.finishLoadMore(success)
    }

    override fun showLoading() {
        state_layout.showLoading()
    }

    override fun showEmpty() {
        state_layout.showEmpty()
    }

    override fun showError() {
        state_layout.showError()
    }

    override fun showContent() {
        state_layout.showContent()
    }

    override fun setMovieList(list: List<Movie.Subject>) {
        mAdapter.list = ArrayList(list)
    }

    override fun addMovieList(list: List<Movie.Subject>) {
        mAdapter.add(list)
    }

    override fun setNoMoreData(noMore: Boolean) {
        refresh_layout.setNoMoreData(noMore)
    }
}
