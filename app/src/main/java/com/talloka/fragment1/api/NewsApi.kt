package com.talloka.fragment1.api

import com.talloka.fragment1.model.News
import com.talloka.fragment1.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//pakai interface karena akan dipakai di banyak class class lain.
interface NewsApi {
    @GET("/v2/top-hadlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "id",
        @Query("page") page : Int = 1,
        @Query("apiKey") apiKey : String = API_KEY
    ) : Response<News>

    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q") countryCode: String,
        @Query("page") page : Int = 1,
        @Query("apiKey") apiKey : String = API_KEY
    ) : Response<News>
}