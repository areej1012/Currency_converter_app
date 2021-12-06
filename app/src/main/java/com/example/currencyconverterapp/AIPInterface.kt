package com.example.currencyconverterapp

import retrofit2.Call
import retrofit2.http.GET

interface AIPInterface {
    @GET("eur.json")
    fun getERUOValue():Call<ErouValue>
}