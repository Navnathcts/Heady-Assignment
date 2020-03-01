package navanth.com.herokuapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import navanth.com.herokuapp.Utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitProvider {

    // we are creating a networking client using OkHttp and add our authInterceptor.
    private val apiClient =
        OkHttpClient().newBuilder().connectTimeout(1, TimeUnit.MINUTES).build()

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

}