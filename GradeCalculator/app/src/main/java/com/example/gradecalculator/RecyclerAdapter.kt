package com.example.gradecalculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val context: Context, private val subjectLists: ArrayList<SubjectData>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val majorCommonSpinner = itemView.findViewById<Spinner>(R.id.main_recycler_item_major_common_spinner)
        val subjectName = itemView.findViewById<EditText>(R.id.main_recycler_item_subject_name_edittext)
        val gradeSpinner = itemView.findViewById<Spinner>(R.id.main_recycler_item_grade_spinner)
        val subjectGrade = itemView.findViewById<EditText>(R.id.main_recycler_item_grade_edittext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.majorCommonSpinner
    }

    override fun getItemCount() = subjectLists.size
}