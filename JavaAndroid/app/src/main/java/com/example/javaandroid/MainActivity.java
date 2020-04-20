package com.example.javaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = findViewById(R.id.textview);
        tv.setText("텍스트 값을 코드에서 변경합니다.");

        setContentView(R.layout.activity_main);
        // View를 직접 만들어 보자.
//        LinearLayout mainLayout = new LinearLayout(this);
//        Button button = new Button(this);
//        button.setText("Button Created by Code");
//        mainLayout.addView(button);
//
//        setContentView(mainLayout);
    }
}