package com.example.javaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.javaandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
//    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        TextView tv = findViewById(R.id.textview);
        tv.setText("텍스트 값을 코드에서 변경합니다.");

//        binding.textview.setText("Binding");

        // View를 직접 만들어 보자.
//        LinearLayout mainLayout = new LinearLayout(this);
//        Button button = new Button(this);
//        button.setText("Button Created by Code");
//        mainLayout.addView(button);
//
//        setContentView(mainLayout);
    }
}