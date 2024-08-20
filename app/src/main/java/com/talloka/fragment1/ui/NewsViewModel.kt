package com.talloka.fragment1.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talloka.fragment1.model.News
import com.talloka.fragment1.repository.NewsRepository
import com.talloka.fragment1.resource.Resource
import kotlinx.coroutines.launch

class NewsViewModel(
    val newsRepository : NewsRepository
) : ViewModel() {
    val breakingNews:MutableLiveData<Resource<News>> = MutableLiveData()
    val breakingNewsPage = 1

    init {
        getBreakingNews("id")
    }

    fun getBreakingNews(countryCode:String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews( countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }
    private fun handleBreakingNewsResponse(response : retrofit2.Response<News>) : Resource<News> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}