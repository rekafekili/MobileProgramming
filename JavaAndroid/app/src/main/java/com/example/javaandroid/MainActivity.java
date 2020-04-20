package com.example.javaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javaandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LayoutInflater inflater;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] buttonIDs = {R.id.button1, R.id.button2, R.id.button3, R.id.button4};
        Button[] buttons = new Button[buttonIDs.length];

        container = findViewById(R.id.container);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < buttonIDs.length; i++) {
            buttons[i] = findViewById(buttonIDs[i]);
            buttons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        container.removeAllViews();
        switch (v.getId()) {
            case R.id.button1:
                Toast.makeText(this, "Button1 Clicked", Toast.LENGTH_SHORT).show();
                inflater.inflate(R.layout.sub1, container, true);
                break;
            case R.id.button2:
                Toast.makeText(this, "Button2 Clicked", Toast.LENGTH_SHORT).show();
                inflater.inflate(R.layout.sub2, container, true);
                TextView textView = container.findViewById(R.id.sub2_textview);
                textView.setText("두번째 탭이 클릭되었습니다.");
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
        }
    }
}