package com.example.gradecalculator

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView

class SubjectRecyclerAdapter(
    private val context: Context,
    private val subjectList: ArrayList<Subject>
) :
    RecyclerView.Adapter<SubjectRecyclerAdapter.ViewHolder>() {

    private val TAG = "RecyclerAdapter"
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val classSpinner: Spinner =
            itemView.findViewById<Spinner>(R.id.main_recycler_item_classification_spinner)
        val subjectNameEditText: EditText =
            itemView.findViewById<EditText>(R.id.main_recycler_item_subject_name_edittext)
        val gradeSpinner: Spinner =
            itemView.findViewById<Spinner>(R.id.main_recycler_item_grade_spinner)
        val subjectGradeEditText: EditText =
            itemView.findViewById<EditText>(R.id.main_recycler_item_grade_edittext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = subjectList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.subjectNameEditText.setText(subjectList[position].subjectName)
        holder.subjectGradeEditText.setText(subjectList[position].subjectGrade)

        holder.classSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                subjectList[position].classification = pos
            }

        }

        ArrayAdapter.createFromResource(
            context,
            R.array.spinner_classification,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            holder.classSpinner.adapter = adapter
        }

        holder.gradeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                subjectList[position].grade = pos
            }

        }

        ArrayAdapter.createFromResource(
            context,
            R.array.spinner_grade,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            holder.gradeSpinner.adapter = adapter
        }
    }
}