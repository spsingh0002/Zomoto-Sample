package som.sps.zmoto.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import som.sps.zmoto.model.CategoriesResponse
import som.sps.zmoto.model.LocationSuggestionResponse

interface ZomotoService {

    @Headers("user-key:${Constants.key}")
    @GET("categories")
    fun getCategories(): Call<CategoriesResponse>

    @Headers("user-key:${Constants.key}")
    @GET("locations")
    fun getLocationSuggestions(
        @Query("query")  query:String,
        @Query("lat") lat:Double?=Constants.lat,
        @Query("lon") lon:Double?=Constants.longitute,
        @Query("count") results:Int=10
    ): Call<LocationSuggestionResponse>

}