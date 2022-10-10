package model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("current.json?key=$KEY&")

    fun getWeather(@Query("q") q: String): Call<Weather>
    //http://api.weatherapi.com/v1/current.json?key=f84c1a8ad2f04fa79cb85013222909&q=London&aqi=no

    companion object{

        private const val KEY = "f84c1a8ad2f04fa79cb85013222909"
        private const val BASE_URL = "http://api.weatherapi.com/v1/"


        fun create() : ApiInterface{

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}