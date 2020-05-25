package com.example.testchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatActivity extends AppCompatActivity {
    EditText msgInput, msgOut;
    Button sendBtn;
    private BufferedReader in; // 네트워크 입력 스트림(수신)
    private PrintWriter out; // 네트워크 출력 스트림
    String recvData; // 수신한 메시지
    String sendingData; // 송신할 메시지
    private final Handler handler = new Handler(); //main 쓰레드의 UI 제어를 위해사용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // UI로 화면 표시
        setView(); // UI 구성. 객체 레퍼런스 설정하기
        setEvent();
        Intent intent = getIntent();
        final String server =intent.getStringExtra("server");
        //https://stackoverflow.com/questions/18341652/connect-failed-econnrefused
        //To access your PC localhost from Android emulator, use 10.0.2.2 instead of 127.0.0.1. localhost or 127.0.0.1 refers to the emulated device itself, not the host the emulator is running on.
        final String nickname =intent.getStringExtra("nickname");
        Log.d("채팅", server + nickname);
        // main쓰레드에서 네트워킹 작업을 하면 안됨 (NetworkOnMainThreadException)
        // connectServer메소드 내에 소켓 생성 작업이 있기 때문에 쓰레드 내에서 실행하도록 함
        // connectServer메소드 내에 메시지 수신 쓰레드가 또 있기 때문에 추후 깔끔하게 정리할 필요는 있음
        new Thread(new Runnable() {
            @Override
            public void run() {
                connectServer(server, nickname);
            }
        }).start();
    }
    private void connectServer(String server, String nickname) {
        String str[] = server.split(":");
        try {
            // 서버에 접속하여 소켓 객체 생성
            Socket s = new Socket(str[0], Integer.parseInt(str[1]));
            Log.d("채팅", "서버에 접속 정보" + s) ;

            // 입출력 스트림 만들기
            in = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
            out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"), true); //auto flush

            // (1) 쓰레드를 만들어서 서버에서 받은 데이터를 화면에 출력하자
            new Thread() {
                public void run() {
                    recvData = "";
                    try {
                        while(( recvData = in.readLine()) != null) {
                            // GUI 화면에 출력해야지..
                            handler.post(new Runnable() { // Handler를 이용해서 main 쓰레드에서 UI 접근하도록
                                @Override
                                public void run() {
                                    msgOut.append(recvData + "\n");
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
            }.start();

            // (2) 접속하자마자 첫번째 메시지로 로그인 명령 + 닉네임 전달
            out.println("login "+nickname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setView(){
        msgOut = findViewById(R.id.msgOut);
        msgInput = findViewById(R.id.msgInput);
        sendBtn = findViewById(R.id.sendBtn);
    }
    public void setEvent(){
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg();
            }
        });
        msgInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                // 엔터키를 눌렀을때 메시지 보내기
                if( (keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    sendMsg();
                    return true;
                }
                return false;
            }
        });
    }
    private void sendMsg(){
        // 실제 output 스트림에 메시지 보내기
        // UI 요소에 접근하는 코드가 있기 때문에 handler를 통해서 작업
        handler.post(new Runnable() {
            @Override
            public void run() {
                sendingData = msgInput.getText().toString(); // 보낼 text를 sendingData 변수에 저장
                msgInput.setText(""); // 입력 내용 클리어
                msgInput.requestFocus();
                // main 쓰레드에서 네트워킹 작업을 하면 NetworkOnMainThreadException 발생하기 때문에
                // 별도의 쓰레드를 만들어서 out 스트림에 데이터 보내기. AsyncTask를 사용해도 됨
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        out.println(sendingData); // 네트워크로 입력된 text를 보내기
                    }
                }).start();
            }
        });
    }
}