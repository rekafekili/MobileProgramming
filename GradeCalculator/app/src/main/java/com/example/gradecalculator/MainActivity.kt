package com.example.gradecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val subjectList = ArrayList<Subject>(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        main_recyclerview.layoutManager = LinearLayoutManager(this)
        val recyclerAdapter = SubjectRecyclerAdapter(this, subjectList)
        main_recyclerview.adapter = recyclerAdapter
    }
}
