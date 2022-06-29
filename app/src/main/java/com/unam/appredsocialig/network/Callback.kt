package com.unam.appredsocialig.network

interface Callback<T>{
    fun onSuccess(result: T?)

    fun onFailed(exception: Exception)
}