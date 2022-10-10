package com.example.newapplication

import java.text.SimpleDateFormat
import java.util.*

class Greeting {

    private val formatter = SimpleDateFormat("hh")
    private val time = formatter.format(Date())
    var greeting = ""

    fun setGreeting(): String {

        greeting = when (time.toInt()) {
            in 0..5 -> "Доброй ночи"
            in 6..11 -> "Доброе утро"
            in 12..17 -> "Добрый день"
            in 18..23 -> "Добрый вечер"
            else -> "Привет"
        }
        return greeting
    }

}