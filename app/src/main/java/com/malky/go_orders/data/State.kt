package com.malky.go_orders.data

sealed class State <out T> {
    object Loading : State<Nothing>()
    data class Success<T>(val data: T) : State<T>()
    data class Error(val message: String) : State<Nothing>()
    fun toData():T? = if(this is Success) data else null
}