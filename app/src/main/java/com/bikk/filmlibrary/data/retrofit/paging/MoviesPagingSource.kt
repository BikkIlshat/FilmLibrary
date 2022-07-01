package com.bikk.filmlibrary.data.retrofit.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bikk.filmlibrary.data.retrofit.ApiService
import com.bikk.filmlibrary.models.MovieItemModel


class MoviesPagingSource(private val apiService: ApiService) : PagingSource<Int, MovieItemModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItemModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, MovieItemModel> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getPopularMovie(currentPage)
            val responseData = mutableListOf<MovieItemModel>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}