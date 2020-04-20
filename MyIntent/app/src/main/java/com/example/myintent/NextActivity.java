package com.example.myintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NextActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Intent intent = getIntent();
        if(intent != null) {
            String str = intent.getStringExtra("name");
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }

        Button button = findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("result", "GOOD");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
