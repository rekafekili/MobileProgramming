package com.example.variousintent

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val IMAGE_CAPTURE_REQUEST = 1004
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 웹 브라우저 실행
        main_webview.loadUrl("https://www.google.com")

        // 버튼을 클릭하면 카메라 앱 호출
        main_button.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if(intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, IMAGE_CAPTURE_REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == IMAGE_CAPTURE_REQUEST) {
            val image = data?.extras?.get("data") as Bitmap
            main_imageview.setImageBitmap(image)
        }
    }
}
