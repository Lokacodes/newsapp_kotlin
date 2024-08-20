package com.talloka.fragment1.ui.breakingnews

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talloka.fragment1.MainActivity
import com.talloka.fragment1.R
import com.talloka.fragment1.adapter.NewsAdapter
import com.talloka.fragment1.resource.Resource
import com.talloka.fragment1.ui.NewsViewModel

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

//
//    companion object {
//        fun newInstance() = BlankFragment()
//    }
//
//    private val viewModel: NewsViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // TODO: Use the ViewModel
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return inflater.inflate(R.layout.fragment_breaking_news, container, false)
//    }
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var paginationProgressBar: View
    private lateinit var rvBreakingNews: RecyclerView
    val TAG = "breakingNewsFragment"

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        paginationProgressBar = view.findViewById(R.id.paginationProgressBar)
//        rvBreakingNews = view.findViewById(R.id.rvBreakingNews)
//        viewModel = (activity as MainActivity).viewModel
//    }
//
//
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paginationProgressBar = view.findViewById(R.id.paginationProgressBar)
        rvBreakingNews = view.findViewById(R.id.rvBreakingNews)
        viewModel = (activity as MainActivity).viewModel

    setupRecycleView()
        viewModel.breakingNews.observe(viewLifecycleOwner){ response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error Occured: $message")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecycleView(){
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply{
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}