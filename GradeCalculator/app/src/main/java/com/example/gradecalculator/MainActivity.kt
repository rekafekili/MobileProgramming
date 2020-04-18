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
        var subjectTotal = 0  // 총 취득 학점
        var commonCount = 0     // 교양 총 취득 학점
        var commonTotal = 0.0   // 교양 과목 총점
        var majorCount = 0      // 전공 총 취득 학점
        var majorTotal = 0.0    // 전공 과목 총점
        var etcCount = 0        // Pass 과목 총점
        for (subject: Subject in subjectList) {
            if (subject.classification == 0 || subject.subjectName == "" || subject.grade == 0 || subject.subjectGrade == "") {
                continue
            } else {
                subjectCount++ // 수강 과목 개수 +1
                if(subject.grade == 9) { // If Grade is F 학점 반영 안함
                    continue
                } else {
                    if(subject.grade != 10) { // Pass 과목은 총 취득 학점에만 더하고, 학점 반영 X
                        // String Array 의 index 값을 이용하여 학점을 계산 -> A+(1) : 4.5, A(2) : 4.0, S(10) : 0
                        val grade = (4.5 - (subject.grade - 1) * (0.5)) * subject.subjectGrade.toInt()
                        if (subject.classification == 1) { // 교양 과목일 경우
                            commonTotal += grade
                            commonCount += subject.subjectGrade.toInt()
                        } else {    // 전공 과목일 경우
                            majorTotal += grade
                            majorCount += subject.subjectGrade.toInt()
                        }
                    } else {
                        etcCount += subject.subjectGrade.toInt()
                    }
                    subjectTotal += subject.subjectGrade.toInt()
                }
            }
        }
        val commonAvg = if(commonCount!=0) commonTotal / commonCount else 0.0     // 교양 평점
        val majorAvg = if(majorCount!=0) majorTotal / majorCount else 0.0        // 전공 평점
        val totalAvg = (commonTotal + majorTotal) / (subjectTotal - etcCount)    // 총 평점
        showAlertDialog(subjectCount, subjectTotal, commonAvg, majorAvg, totalAvg)
    }

    private fun showAlertDialog(
        subjectCount: Int,
        subjectTotal: Int,
        commonAvg: Double,
        majorAvg: Double,
        totalAvg: Double
    ) {
        val builder = AlertDialog.Builder(this)

        if(subjectCount != 0) {
            builder.setTitle("Result").setMessage(
                "총 과목 수 : $subjectCount\n" +
                        "취득 학점 : $subjectTotal\n" +
                        "교양 평점 : ${String.format("%.2f",commonAvg)}\n" +
                        "전공 평점 : ${String.format("%.2f",majorAvg)}\n" +
                        "총 평점 : ${String.format("%.2f",totalAvg)}\n"
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
