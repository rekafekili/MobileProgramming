package com.example.question2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.question2.model.Covid;
import com.example.question2.model.Global;
import com.example.question2.repository.CovidService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText country;
    private Button search;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        country = findViewById(R.id.main_country_slug_edittext);
        search = findViewById(R.id.main_search_button);
        result = findViewById(R.id.main_result_textivew);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CovidService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        CovidService service = retrofit.create(CovidService.class);

        service.fetchCovidInfo().clone().enqueue(new Callback<Covid>() {
            @Override
            public void onResponse(Call<Covid> call, Response<Covid> response) {
                final Global global = response.body().getGlobal();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String resultString = String.format("신규 확진자 : %d\n누적 확진자: %d\n", global.getNewConfirmed(), global.getTotalConfirmed());
                        result.setText(resultString);
                    }
                });
            }

            @Override
            public void onFailure(Call<Covid> call, Throwable t) {

            }
        });
    }
}