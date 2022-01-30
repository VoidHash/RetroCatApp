package com.voidhash.thecatapp.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voidhash.thecatapp.R
import com.voidhash.thecatapp.adapters.RecyclerAdapter
import com.voidhash.thecatapp.api.CatAPI
import com.voidhash.thecatapp.models.Kitty
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    private fun getData() {
        var rclView: RecyclerView = catRecyclerView

        var catList = listOf<Kitty>()
        val myAdapter: RecyclerAdapter = RecyclerAdapter(catList, this)
        rclView.adapter = myAdapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rclView.layoutManager = layoutManager

        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endpoint = retrofitClient.create(CatAPI::class.java)
        val callback = endpoint.getBreeds()

        callback.enqueue(object : Callback<List<Kitty>> {
            override fun onFailure(call: Call<List<Kitty>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                t.message?.let { Log.e("DBG", it) }
            }

            override fun onResponse(call: Call<List<Kitty>>, response: Response<List<Kitty>>) {
                response.body()?.let {
                    rclView.adapter = RecyclerAdapter(it, this@MainActivity)
                }
            }
        })
    }
}