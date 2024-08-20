package com.talloka.fragment1.repository

import com.talloka.fragment1.api.RetrofitInstance
import com.talloka.fragment1.dblocal.ArticleDatabase
import java.util.Locale.IsoCountryCode

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String,pageNumber: Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

}
