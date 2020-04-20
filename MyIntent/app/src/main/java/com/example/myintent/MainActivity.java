package com.example.myintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1001;
    private static final int REQUEST_CODE_OTHER = 2002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NextActivity.class);
                intent.putExtra("name", "SeongYun");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        Button otherButton = findViewById(R.id.main_otherapp_button);
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName name = new ComponentName("com.example.anotherapp", "com.example.anotherapp.MainActivity");
                intent.setComponent(name);
                intent.putExtra("data", "HELLO");
                startActivityForResult(intent, REQUEST_CODE_OTHER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (data != null) {
                    String result = data.getStringExtra("result");
                    Toast.makeText(getApplicationContext(),
                            "결과 코드 : " + resultCode + "받은 데이터" + result,
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_OTHER:
                if (data != null) {
                    String result = data.getStringExtra("result");
                    Toast.makeText(getApplicationContext(),
                            "결과 코드 : " + resultCode + "받은 데이터" + result,
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}