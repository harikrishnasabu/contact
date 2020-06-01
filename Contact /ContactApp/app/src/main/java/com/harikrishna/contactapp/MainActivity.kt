package com.harikrishna.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var conatctAdapter: ContactRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        addDataSet()

    }

    private fun addDataSet(){
        val data = Data.createDataSet()
     //   val response=Data.getContacts()
        conatctAdapter.submitList(data)
    }

    private fun initRecyclerView(){

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            conatctAdapter = ContactRecyclerAdapter()
            adapter = conatctAdapter
        }
    }

    fun navAddContact(view: View) {
        val intent = Intent(this, AddContactActivity::class.java).apply {

        }
        startActivity(intent)
    }


}