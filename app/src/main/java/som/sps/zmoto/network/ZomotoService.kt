package som.sps.zmoto.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import som.sps.zmoto.model.*

interface ZomotoService {

    @Headers("user-key:${Constants.key}")
    @GET("categories")
    fun getCategories(): Call<CategoriesResponse>

    @Headers("user-key:${Constants.key}")
    @GET("locations")
    fun getLocationSuggestions(
        @Query("query") query: String,

        @Query("count") results: Int = 10
    ): Call<LocationSuggestionResponse>

    @Headers("user-key:${Constants.key}")
    @GET("search")
    fun fetchRestaurants(
        @Query("start") start:Int
        ,@Query("entity_id") entityId: Int
        , @Query("entity_type") entityType: String
        , @Query("count") count: Int
        , @Query("category") category: Int

    ): Call<RestaurantResponse>

    @Headers("user-key:${Constants.key}")
    @GET("restaurant")
    fun getRestaurantDetail(@Query("res_id") resId: Int):Call<Restaurant>
}