package com.talloka.fragment1.ui.searchnews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.talloka.fragment1.MainActivity
import com.talloka.fragment1.R
import com.talloka.fragment1.ui.NewsViewModel

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    private lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}