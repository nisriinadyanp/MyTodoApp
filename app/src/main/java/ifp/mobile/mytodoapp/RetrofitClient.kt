package ifp.mobile.mytodoapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import androidx.compose.runtime.getValue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASEURL =
        "https://mgm.ub.ac.id/api/"

    private val loggingInterceptor =
        HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val apiService: ApiService by lazy{
        Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}