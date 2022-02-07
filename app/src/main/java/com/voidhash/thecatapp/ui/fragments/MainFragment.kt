package com.voidhash.thecatapp.ui.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voidhash.thecatapp.R
import com.voidhash.thecatapp.backend.api.CatAPI
import com.voidhash.thecatapp.backend.models.Kitty
import com.voidhash.thecatapp.ui.adapters.RecyclerAdapter
import com.voidhash.thecatapp.ui.listeners.CatClickListener
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment(), CatClickListener {

    private lateinit var myAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getData()
    }

    private fun init(){
        val rclView: RecyclerView = catRecyclerView
        val catList = listOf<Kitty>()
        myAdapter = RecyclerAdapter(catList, requireContext())
        myAdapter.myClickListener = this
        rclView.adapter = myAdapter

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rclView.layoutManager = layoutManager
    }

    private fun getData() {

        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endpoint = retrofitClient.create(CatAPI::class.java)
        val callback = endpoint.getBreeds()

        callback.enqueue(object : Callback<List<Kitty>> {
            override fun onFailure(call: Call<List<Kitty>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Kitty>>, response: Response<List<Kitty>>) {
                response.body()?.let {
                    myAdapter.setListItems(it)
                }
            }
        })
    }

    override fun onCatClickListener(model: Kitty) {
        val detail = DetailFragment()
        detail.apply {
            arguments = Bundle().apply {
                putParcelable("model", model)
            }
        }
        val fm: FragmentManager = requireFragmentManager()
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack(null)
        ft.replace(R.id.fragment_content, detail)
        ft.commit()
    }
}