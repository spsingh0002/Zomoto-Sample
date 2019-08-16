package som.sps.zmoto.network


import androidx.paging.PageKeyedDataSource

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import som.sps.zmoto.model.RestaurantResponse
import som.sps.zmoto.model.Restaurants
import timber.log.Timber


class RestaurantSearchDataSource internal constructor(
    private val entityId: Int,
    private val entityType: String,
    private val category: Int
) : PageKeyedDataSource<Int, Restaurants>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Restaurants>
    ) {
        RetrofitClient.getZomotoService()
            .fetchRestaurants(FIRST_PAGE, entityId, entityType, params.requestedLoadSize, category)
            .enqueue(object : Callback<RestaurantResponse> {
                override fun onResponse(
                    call: Call<RestaurantResponse>,
                    response: Response<RestaurantResponse>
                ) {
                    val body = response.body()
                    if (body != null) {
                        val key =
                            if (body.resultsFound - body.resultsStart - body.resultsShown > 1) FIRST_PAGE + 1 else null
                        callback.onResult(body.restaurants, null, key)
                    }
                }

                override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                    Timber.e(t)
                }
            })
    }

    //this will load the previous page
    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Restaurants>
    ) {
        val adjacentKey = if (params.key > 1) params.key - 1 else null
        RetrofitClient.getZomotoService()
            .fetchRestaurants(
                params.key * params.requestedLoadSize,
                entityId,
                entityType,
                params.requestedLoadSize,
                category
            )
            .enqueue(object : Callback<RestaurantResponse> {
                override fun onResponse(
                    call: Call<RestaurantResponse>,
                    response: Response<RestaurantResponse>
                ) {
                    if (response.body() != null) {
                        callback.onResult(response.body()!!.restaurants, adjacentKey)
                    }
                }

                override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                    Timber.e(t)
                }
            })
    }

    //this will load the next page
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Restaurants>
    ) {
        RetrofitClient.getZomotoService()
            .fetchRestaurants(
                params.key * params.requestedLoadSize, entityId, entityType, params
                    .requestedLoadSize, category
            )
            .enqueue(object : Callback<RestaurantResponse> {
                override fun onResponse(
                    call: Call<RestaurantResponse>,
                    response: Response<RestaurantResponse>
                ) {
                    val body = response.body()
                    if (body != null) {
                        val key =
                            if (body.resultsFound - body.resultsStart - body.resultsShown > 1) params.key + 1 else null
                        callback.onResult(body.restaurants, key)
                    }
                }

                override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                    Timber.e(t)
                }
            })

    }

    companion object {

        private val FIRST_PAGE = 0
    }
}