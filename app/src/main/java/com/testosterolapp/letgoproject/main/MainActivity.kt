package com.testosterolapp.letgoproject.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.testosterolapp.letgoproject.R
import com.testosterolapp.letgoproject.database.DaoRepository
import com.testosterolapp.letgoproject.util.LinearLayoutManagerWrapper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: MainActivityViewModel by viewModels()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManagerWrapper(this)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

        val adapter = MainActivityAdapter()
        recyclerView.adapter = adapter

        val repositoryDao = DaoRepository(this).superheroDao
        model.init(repositoryDao!!)

        model.allRepositories!!.observe(this) { characters ->
            if (characters.isNotEmpty()) {
                adapter.submitList(characters)
            }
        }
        model.filterTextAll.setValue("")


    }
}