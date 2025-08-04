package com.example.lzycrazy.withoutlogin.services

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServicesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ServicesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)


        recyclerView = findViewById(R.id.recyclerViewServices)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchServices()

    }

    private fun fetchServices() {
        RetrofitInstance.api.getServices()
            .enqueue(object : Callback<List<ServicesAdapterItem>> {
                override fun onResponse(
                    call: Call<List<ServicesAdapterItem>>,
                    response: Response<List<ServicesAdapterItem>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val services = response.body()!!
                        adapter = ServicesAdapter(services)
                        recyclerView.adapter = adapter
                    } else {
                        Log.e("API_ERROR", "Response failed")
                    }
                }

                override fun onFailure(call: Call<List<ServicesAdapterItem>>, t: Throwable) {
                    Log.e("API_ERROR", "Network call failed: ${t.message}")
                }
            })
    }


}