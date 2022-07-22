package com.example.mathquizii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathquizii.model.Question;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Scores extends AppCompatActivity {
    RadioButton radioBtnAll, radioBtnRight, radioBtnWrong;
    TextView showResultTextView, percentageTextView;
    public ArrayList<Question> questionsList;
    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        initialize();
    }

    private void initialize(){
        radioBtnAll = findViewById(R.id.radioBtnAll);
        radioBtnRight = findViewById(R.id.radioBtnRight);
        radioBtnWrong = findViewById(R.id.radioBtnWrong);

        showResultTextView = findViewById(R.id.showResultTextView);
        percentageTextView = findViewById(R.id.percentageTextView);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("intentExtra");
        Serializable bundledListOfQuestions = bundle.getSerializable("bundleExtra");
        questionsList = (ArrayList<Question>) bundledListOfQuestions;

        int showPerc = intent.getIntExtra("percentage",0);
        percentageTextView.setText(String.valueOf(showPerc)+"%");
    }

    public void showListOfQuestions(View view) {
        if(radioBtnRight.isChecked())
            iterateByType(true);
        else if(radioBtnWrong.isChecked())
            iterateByType(false);
        else if(radioBtnAll.isChecked())
            showListOfQuestion(questionsList);

        showResultTextView.setText(str);
    }

    void iterateByType(boolean b) {
        str = "";
        for (Question q : questionsList){
            if (q.getRight().equals(b)){
                str = str + q;
            }
         }
    }
    void showListOfQuestion(ArrayList<Question> questionsList) {
        str = "";
        for (Question q : questionsList) {
            str = str + q;
        }
    }

    public void quit(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
