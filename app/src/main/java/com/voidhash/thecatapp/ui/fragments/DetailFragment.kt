package com.voidhash.thecatapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.voidhash.thecatapp.R
import com.voidhash.thecatapp.backend.api.CatAPI
import com.voidhash.thecatapp.backend.models.Kitty
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailFragment : Fragment() {

    private var catModel: Kitty? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            catModel = it.getParcelable("model")
        }

        getMyKitty()
    }

    private fun getMyKitty() {

        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endpoint = retrofitClient.create(CatAPI::class.java)
        val callback = catModel?.let { endpoint.getKitty(it.id) }

        callback?.enqueue(object : Callback<List<Kitty>> {
            override fun onFailure(call: Call<List<Kitty>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                Log.e("DBG", t.message.toString())
            }

            override fun onResponse(call: Call<List<Kitty>>, response: Response<List<Kitty>>) {
                response.body()?.let {
                    if(it.isNullOrEmpty()){
                        setViewData(catModel!!)
                    } else {
                        Log.e("DBG", "Model loaded from request")
                        setViewData(it.first())
                    }
                }
            }
        })
    }

    private fun setViewData(model: Kitty) {
        val txtBreed: TextView = txtBreed
        txtBreed.text = model.name
        val txtDescription: TextView = txtDescription
        txtDescription.text = model.description
        val txtLifeSpan = txtLifeSpan
        txtLifeSpan.text = "Life span: "+ model.lifeSpan + " years"
        val txtOrigin = txtOrigin
        txtOrigin.text = "Origin: "+ model.origin
        val txtTemperament = txtTemperament
        txtTemperament.text = model.temperament

        val imgCat: ImageView = imgCat
        Glide.with(this)
            .load(catModel?.image?.url)
            .apply(RequestOptions().override(1350, 450))
            .into(imgCat)
    }
}