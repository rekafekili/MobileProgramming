package com.example.gradecalculator

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
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

        val classIndex = subjectList[position].classification
        holder.classSpinner.setSelection(if (classIndex == 0) 0 else classIndex)
        val gradeIndex = subjectList[position].grade
        holder.gradeSpinner.setSelection(if(gradeIndex==0) 0 else gradeIndex)

        setTextWatcher(holder)
        setSelectedListener(holder)
    }

    private fun setTextWatcher(holder: ViewHolder) {
        holder.subjectNameEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                subjectList[holder.adapterPosition].subjectName = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                Log.d(TAG, "before : $s, $start, $count, $after")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                Log.d(TAG, "changed : $s, $start, $before, $count")
            }
        })

        holder.subjectGradeEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                subjectList[holder.adapterPosition].subjectGrade = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                Log.d(TAG, "before : $s, $start, $count, $after")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                Log.d(TAG, "changed : $s, $start, $before, $count")
            }
        })
    }

    private fun setSelectedListener(holder: ViewHolder) {
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
                Log.d(TAG, "Selcted $parent / $view / $pos")
                if (pos != 0) {
                    subjectList[holder.adapterPosition].classification = pos
                }
            }
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
                if (pos != 0)
                    subjectList[holder.adapterPosition].grade = pos
            }
        }
    }
}