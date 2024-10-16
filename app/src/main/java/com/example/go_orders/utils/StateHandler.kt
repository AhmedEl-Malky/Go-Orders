package com.example.go_orders.utils

import com.example.go_orders.data.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


fun <T> StateHandler(function: suspend () -> T): Flow<State<T>> {
    return flow {
        emit(State.Loading)
        try {
            val result = function()
            emit(State.Success(result))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }
}