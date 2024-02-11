package com.example.pravdinai_tinkoffandroid.ui.screens.utils

fun String.firstLetterToUpperCase(): String{
    val str = this.toCharArray()
    str[0] = this[0].uppercaseChar()
    return String(str)
}