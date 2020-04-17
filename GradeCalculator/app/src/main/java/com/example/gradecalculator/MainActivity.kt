package com.example.gradecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private val TAG = "MainActivity"
    private val subjectList = ArrayList<Subject>()
    private val INIT_COUNT = 5
    private lateinit var recyclerAdapter: SubjectRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSubjectList()
        initRecyclerView()
        initButton()
    }

    private fun initButton() {
        main_reset_button.setOnClickListener {
            initSubjectList()
            recyclerAdapter.notifyDataSetChanged()
        }
        main_add_button.setOnClickListener {
            val subject = Subject()
            subjectList.add(subject)
            recyclerAdapter.notifyDataSetChanged()
        }
        main_result_button.setOnClickListener {

        }
    }

    private fun initSubjectList() {
        subjectList.clear()
        for(i in 0 until INIT_COUNT){
            val subject = Subject()
            subjectList.add(subject)
        }
    }

    private fun initRecyclerView() {
        main_recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = SubjectRecyclerAdapter(this, subjectList)
        main_recyclerview.adapter = recyclerAdapter
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }
}
