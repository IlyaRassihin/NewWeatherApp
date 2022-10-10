package com.example.newapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import model.ApiInterface
import model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var tvGreeting: TextView? = null
    private var etEnterCity: EditText? = null
    private var btnGetWeather: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()

        tvGreeting = findViewById(R.id.tv_Greeting)
        etEnterCity = findViewById(R.id.et_enter_city)
        btnGetWeather = findViewById(R.id.btn_get_weather)


        //устанавливаем приветствие в зависимости от времени суток
        val getGreeting = Greeting().setGreeting()
        val word = DelayedPrinter.Word(100, getGreeting)
        word.setOffset(50)
        DelayedPrinter.printText(word, tvGreeting)

        //обрабатываем клик кнопки
        btnGetWeather?.setOnClickListener {
            if (etEnterCity?.text?.toString()?.trim()?.equals("")!!)
                Toast.makeText(this, "Enter your city", Toast.LENGTH_SHORT).show()
            else {
                val cityName = etEnterCity?.text?.trim()?.toString()
                val apiInterface = ApiInterface.create().getWeather(cityName!!)
                apiInterface.enqueue(object : Callback<Weather> {
                    override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                        if (response.body() != null) {
                            val tmp = response.body()!!.current.temp_c
                            val wind = response.body()!!.current.wind_kph
                            val pressure = response.body()!!.current.pressure_mb
                            val humidity = response.body()!!.current.humidity
                            val weatherConditionText = response.body()!!.current.condition.text
                            val weatherConditionIcon = response.body()!!.current.condition.icon
                            Toast.makeText(this@MainActivity, "Tmp is: $tmp", Toast.LENGTH_SHORT)
                                .show()

                        } else Toast.makeText(this@MainActivity, "Wrong city", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onFailure(call: Call<Weather>, t: Throwable) {

                        Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_LONG).show()
                        Log.d("btnCLick", "$t")
                    }
                })
            }
        }
    }
}