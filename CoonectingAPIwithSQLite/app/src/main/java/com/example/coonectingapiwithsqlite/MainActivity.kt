package com.example.coonectingapiwithsqlite

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coonectingapiwithsqlite.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: RecyclerAdapter
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)

        // Show progress dialog before making the network request
        progressDialog?.show()


        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                progressDialog?.dismiss() // Dismiss progress dialog once data is fetched
                val responseBody = response.body()
                val productList = responseBody?.products

                productList?.forEach { product ->
                    dbHelper.insertProduct(product)
                }
                // Retrieve and display products from the database
                displayProducts()
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                progressDialog?.dismiss() // Dismiss progress dialog on failure
                Log.d("Main Activity", "onFailure: " + t.message)
            }
        })

        // Set up RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun displayProducts() {
        val storedProducts = dbHelper.getAllProducts()

        //TO SEND THE DATA CLASS DIRECTLY , MAKE THE DATA CLASS PARCEBLE
        adapter = RecyclerAdapter(storedProducts) { product ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
    }
}
