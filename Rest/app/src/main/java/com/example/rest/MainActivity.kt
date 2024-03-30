package com.example.rest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import api.ServiceGenerator
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var list:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        list = findViewById(R.id.list)





        val client:GitHubClient = ServiceGenerator.createService(GitHubClient::class.java)
       val call: Call<List<GitHubRepo>>  = client.reposForUsers("Funto759")

        call.enqueue(object:Callback<List<GitHubRepo>> {
            override fun onResponse(
                call: Call<List<GitHubRepo>>?,
                response: Response<List<GitHubRepo>>?
            ) {

                    val repos = response!!.body() // Safe call, repos can be null

                        list.adapter = GitHubRepoAdaptor(this@MainActivity, 0, repos)

            }

            override fun onFailure(call: Call<List<GitHubRepo>>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "error : (", Toast.LENGTH_SHORT).show()
            }
        })
    }
}