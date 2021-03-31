package com.maniksingh.manik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int receiver_code = 1;
    public static final int sender_code = 0;
    RecyclerView chat_rv;
    AppCompatButton receive_btn, send_btn;
    EditText enter_text_et;
    List<TextPOJO> chatList = new ArrayList<>();
    ChatAdapter chatAdapter;
    GridLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onClick();
    }

    private void onClick() {

        receive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enter_text_et.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please type something to receive.", Toast.LENGTH_SHORT).show();
                } else {
                    TextPOJO receiverObj = new TextPOJO(enter_text_et.getText().toString(), receiver_code);
                    chatList.add(receiverObj);
                    chatAdapter = new ChatAdapter(MainActivity.this, chatList);
                    layoutManager = new GridLayoutManager(MainActivity.this, 1);
                    chat_rv.setLayoutManager(layoutManager);
                    chat_rv.setAdapter(chatAdapter);
                    enter_text_et.getText().clear();
                }
            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (enter_text_et.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please type something to send.", Toast.LENGTH_SHORT).show();
                } else {
                    TextPOJO senderObj = new TextPOJO(enter_text_et.getText().toString(), sender_code);
                    chatList.add(senderObj);
                    chatAdapter = new ChatAdapter(MainActivity.this, chatList);
                    layoutManager = new GridLayoutManager(MainActivity.this, 1);
                    chat_rv.setLayoutManager(layoutManager);
                    chat_rv.setAdapter(chatAdapter);
                    enter_text_et.getText().clear();

                }

            }
        });
    }

    private void init() {
        chat_rv = findViewById(R.id.chat_rv);
        receive_btn = findViewById(R.id.receive_btn);
        send_btn = findViewById(R.id.send_btn);
        enter_text_et = findViewById(R.id.enter_text_et);
    }
}