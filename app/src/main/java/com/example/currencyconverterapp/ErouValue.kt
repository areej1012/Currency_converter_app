package com.example.currencyconverterapp

import com.google.gson.annotations.SerializedName

class ErouValue {
     @SerializedName("date")
     var date: String? = null

     @SerializedName("eur")
     var eur: Eur? = null
 }