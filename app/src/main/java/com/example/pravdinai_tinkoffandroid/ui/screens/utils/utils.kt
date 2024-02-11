package com.example.pravdinai_tinkoffandroid.ui.screens.utils

import com.example.pravdinai_tinkoffandroid.data.network.Country
import com.example.pravdinai_tinkoffandroid.data.network.Genre

fun String.firstLetterToUpperCase(): String{
    val str = this.toCharArray()
    str[0] = this[0].uppercaseChar()
    return String(str)
}

fun List<Genre>.toStringOfGenres(): String{
    var result: String = ""

    this.forEach {
        if (it != this.first()){
            result += ","
        }
        result += " ${it.genre}"
    }
    return result
}

fun List<Country>.toStringOfCountry(): String{
    var result: String = ""

    this.forEach {
        if (it != this.first()){
            result += ","
        }
        result += " ${it.country}"
    }
    return result
}
