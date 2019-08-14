package som.sps.zmoto.network

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import som.sps.zmoto.model.CategoriesResponse

interface ZomotoService {

    @Headers("user-key:${Constants.key}")
    @GET("categories")
    fun getCategories(): Call<CategoriesResponse>
}