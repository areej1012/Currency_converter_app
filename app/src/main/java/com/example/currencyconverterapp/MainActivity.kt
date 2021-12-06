package com.example.currencyconverterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.currencyconverterapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
     var euroList : ArrayList<String> = arrayListOf()
    var selected = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cur = arrayListOf("aud", "cny","inr","jpy" , "sar","usd"  )
        val adpterArray = ArrayAdapter(this ,R.layout.dropdwon_item, cur )
        binding.listEURO.setAdapter(adpterArray)
        APIRequst()


        binding.listEURO.setOnItemClickListener { adapterView, view, i, l ->
            val value = binding.teerrorValue.text.toString()
            if (value.isEmpty()){
                Toast.makeText(this,"Please enter EURO value",Toast.LENGTH_LONG).show()
            }
            else{
                selected = i
                cul(selected,value)
            }
        }
        binding.btconvert.setOnClickListener {
            val value = binding.teerrorValue.text.toString()
            if (value.isEmpty()){
                Toast.makeText(this,"Please enter EURO value",Toast.LENGTH_LONG).show()
            }
            else
                cul(selected,value)
        }
    }
    fun cul(id : Int, value : String) {
        val sal = value.toDouble()
        when(id){
            0 -> cul2(euroList[0].toDouble(),sal)
            1 -> cul2(euroList[1].toDouble(),sal)
            2 -> cul2(euroList[2].toDouble(),sal)
            3 -> cul2(euroList[3].toDouble(),sal)
            4 -> cul2(euroList[4].toDouble(),sal)
            5 -> cul2(euroList[5].toDouble(),sal)

        }
    }

    fun cul2(eur : Double, value : Double) {
        binding.tvresult.text ="${eur*value}"
    }
    private fun APIRequst() {
        val apiInterface  = APIClient().getClient()?.create(AIPInterface::class.java)

        apiInterface?.getERUOValue()?.enqueue(object : Callback<ErouValue>{
            override fun onResponse(call: Call<ErouValue>, response: Response<ErouValue>) {
                try {
                    binding.tvdate.text ="Date: ${response.body()!!.date}"
                    euroList.add(response.body()!!.eur?.aud!!)
                    euroList.add(response.body()!!.eur?.cny!!)
                    euroList.add(response.body()!!.eur?.inr!!)
                    euroList.add(response.body()!!.eur?.jpy!!)
                    euroList.add(response.body()!!.eur?.sar!!)
                    euroList.add(response.body()!!.eur?.usd!!)
                }catch (e : Exception){
                    Log.e("MAIN","Unable get Date \n $e")
                }
            }

            override fun onFailure(call: Call<ErouValue>, t: Throwable) {
                Log.e("MAIN","Unable get Date \n $t")
            }

        })
    }
}