package com.example.ld.keyboarddemo;

import android.inputmethodservice.KeyboardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        KeyboardView mKb = findViewById(R.id.main_key);
        edit = findViewById(R.id.et_text);
//        new KeyBoradUtil(this, mKb, edit);
//        edit.setInputType(InputType.TYPE_NULL);
//
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Utils.shared(MainActivity.this, edit).showKeyboard();
//            }
//        });
        Log.e("MainActivity","尝试本地增加分支，推送远程");
    }
}
