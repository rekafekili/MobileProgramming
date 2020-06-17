package com.example.question1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText speedEditText;
    private Button startButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        nameEditText = findViewById(R.id.main_name_edittext);
        speedEditText = findViewById(R.id.main_speed_edittext);
        startButton = findViewById(R.id.main_start_button);
        resultTextView = findViewById(R.id.main_count_textview);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String speed = speedEditText.getText().toString();

                Intent intent = new Intent(getApplicationContext(), CountActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("speed", Integer.parseInt(speed));

                startActivityForResult(intent, 1004);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1004 && resultCode == RESULT_OK) {
            String resultCount = data.getStringExtra("count");
            resultTextView.setText(String.format("카운트 완료 숫자 = %s", resultCount));
        }
    }
}