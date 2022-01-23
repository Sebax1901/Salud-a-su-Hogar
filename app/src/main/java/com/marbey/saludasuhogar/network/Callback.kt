package com.marbey.saludasuhogar.network

interface Callback<T> {
    fun onSuccess(result: T?)

    fun onFailed(exception: Exception)
}