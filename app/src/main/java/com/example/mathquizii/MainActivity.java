package com.example.mathquizii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;

import com.example.mathquizii.model.Question;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    Button play_btn;
    EditText nameRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize(){
        play_btn = findViewById(R.id.play_btn);
        play_btn.setOnClickListener(this);
        nameRequest = findViewById(R.id.nameRequest);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.play_btn){
            String name = nameRequest.getText().toString();

            Intent myIntent = new Intent(this, PlayGame.class);
            myIntent.putExtra("name", name);
            startActivity(myIntent);
        }
    }
}

