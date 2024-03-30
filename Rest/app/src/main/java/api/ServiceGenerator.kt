package api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private  var HTTPS_API_GITHUB_COM_ = "https://api.github.com/"

object ServiceGenerator {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

   private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

    var gsonFactory:GsonConverterFactory = GsonConverterFactory.create()

    var okHttpClient:OkHttpClient = OkHttpClient()

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(HTTPS_API_GITHUB_COM_).client(okHttpClient)
        .addConverterFactory(gsonFactory)

    private var retrofit: Retrofit = builder.client(httpClient.build()).build()

    fun changeApiUrl(newApiUrl:String){
        HTTPS_API_GITHUB_COM_ = newApiUrl

         val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(HTTPS_API_GITHUB_COM_).client(okHttpClient)
            .addConverterFactory(gsonFactory)
    }



    fun <S> createService(serviceClass: Class<S>): S{

        if (!httpClient.interceptors().contains(logging)){
            httpClient.addInterceptor(logging)
            builder.client(httpClient.build())
            retrofit = builder.build()
        }
        return retrofit.create(serviceClass)
    }
}