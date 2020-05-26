package com.plx.android.im;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EchoClient echoClient;
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注意切换ip地址
        echoClient = new EchoClient("10.252.62.180", 9877);
        button = findViewById(R.id.send);
        editText = findViewById(R.id.edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                echoClient.send(editText.getText().toString());
            }
        });
    }
}
