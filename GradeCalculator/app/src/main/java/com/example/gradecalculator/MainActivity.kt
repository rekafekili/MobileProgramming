package com.example.gradecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val INIT_COUNT = 5
    private val subjectList = ArrayList<Subject>()
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
            Log.d(TAG, subjectList.toString())
            recyclerAdapter.notifyDataSetChanged()
        }
        main_result_button.setOnClickListener {
            calculateGrade()
        }
    }

    private fun calculateGrade() {
        var subjectCount = 0    // 총 과목 개수
        var subjectTotal = 0.0  // 총 과목 총점
        var commonCount = 0     // 교양 과목 개수
        var commonTotal = 0.0   // 교양 과목 총점
        var majorCount = 0      // 전공 과목 개수
        var majorTotal = 0.0    // 전공 과목 총점
        for (subject: Subject in subjectList) {
            if (subject.classification == 0 || subject.subjectName == "" || subject.grade == 0 || subject.subjectGrade == "") {
                continue
            } else {
                subjectCount++
                val grade = 4.5 - (subject.grade - 1) * (0.5)
                if (subject.classification == 1) { // When Common
                    commonCount++
                    commonTotal += grade
                } else {    // When Major
                    majorCount++
                    majorTotal += grade
                }
                subjectTotal += grade
            }
        }

        val commonAvg = if(commonCount!=0) commonTotal / commonCount else 0.0     // 교양 평점
        val majorAvg = if(majorCount!=0) majorTotal / majorCount else 0.0        // 전공 평점
        val totalAvg = subjectTotal / subjectCount    // 총 평점
        val convertGrade = (totalAvg / 4.5) * 100     // 변환 점수

        showAlertDialog(subjectCount, commonAvg, majorAvg, totalAvg, convertGrade)
    }

    private fun showAlertDialog(
        subjectCount: Int,
        commonAvg: Double,
        majorAvg: Double,
        totalAvg: Double,
        convertGrade: Double
    ) {
        val builder = AlertDialog.Builder(this)

        if(subjectCount != 0) {
            builder.setTitle("Result").setMessage(
                "총 과목 수 : $subjectCount\n" +
                        "교양 평점 : $commonAvg\n" +
                        "전공 평점 : $majorAvg\n" +
                        "총 평점 : $totalAvg\n" +
                        "변환 점수 : ${String.format("%.2f",convertGrade)}\n"
            )
        } else {
            builder.setTitle("No Result").setMessage("입력된 과목이 없습니다.")
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun initSubjectList() {
        subjectList.clear()
        for (i in 0 until INIT_COUNT) {
            val subject = Subject()
            subjectList.add(subject)
        }
    }

    private fun initRecyclerView() {
        main_recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = SubjectRecyclerAdapter(this, subjectList)
        main_recyclerview.adapter = recyclerAdapter
    }
}
