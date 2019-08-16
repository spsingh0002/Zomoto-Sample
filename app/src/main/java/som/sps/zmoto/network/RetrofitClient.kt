package som.sps.zmoto.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    fun getZomotoService(): ZomotoService {
        return retrofit.create(ZomotoService::class.java)
    }


}


