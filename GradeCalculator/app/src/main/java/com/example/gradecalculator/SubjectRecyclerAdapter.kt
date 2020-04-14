package com.example.gradecalculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView

class SubjectRecyclerAdapter(val context: Context, val subjectList: ArrayList<Subject>)
    : RecyclerView.Adapter<SubjectRecyclerAdapter.ViewHolder>() {
    private val classArray = context.resources.getStringArray(R.array.spinner_classification)
    private val gradeArray = context.resources.getStringArray(R.array.spinner_grade)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val classSpinner = itemView.findViewById<Spinner>(R.id.main_recycler_item_classification_spinner)
        val subjectNameEditText = itemView.findViewById<EditText>(R.id.main_recycler_item_subject_name_edittext)
        val gradeSpinner = itemView.findViewById<Spinner>(R.id.main_recycler_item_grade_spinner)
        val subjectGradeEditText = itemView.findViewById<EditText>(R.id.main_recycler_item_grade_edittext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = subjectList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.subjectNameEditText.setText(subjectList[position].subjectName)
        holder.subjectGradeEditText.setText(subjectList[position].subjectGrade)
    }
}