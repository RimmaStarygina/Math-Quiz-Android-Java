package com.example.mathquizii;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathquizii.model.Question;

public class PlayGame extends AppCompatActivity implements OnClickListener {
    //Add instance variables to represent the operands, operator and answer:
    private int answer = 0;
    int operator = 0;
    int operand1 = 0;
    int operand2 = 0;

    // define some constants for the four operators, which will streamline the gameplay processing:
    private final int ADD_OPERATOR = 0;
    private final int SUBTRACT_OPERATOR = 1;
    private final int MULTYPLY_OPERATOR = 2;
    private final int DIVIDE_OPERATOR = 3;

    //add an array for the text to display for each of these operators, with the array index corresponding to the constant in each case:
    private String[] operators = {"+", "-", "x", "/"};

    //add an instance variable for a random number generator:
    private Random random;

    //to send info to Scores screen
    public ArrayList<Question> questionsList;
    String question;
    Boolean right;
    int enteredAnswer;

    int correctAnswer = 0;
    int wrongAnswer = 0;
    int percentage = 0;

    TextView questionTextView, answerTextView, scoreTextView, responseTextView, timerTextView, playerNameTextView;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnMinus, btnDot, btnAnswer, btnClear, btnResults;

    //    ====Timer====
    int secondsRemaining = 30;
    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        // when timer starts
        public void onTick(long millisUntilFinished) {
            secondsRemaining--;
            timerTextView.setText(Integer.toString(secondsRemaining) + " sec");
        }

        @Override
        // when timer stops
        public void onFinish() {
            btnAnswer.setEnabled(false);
            percentage = correctAnswer*100/(correctAnswer+wrongAnswer);
            responseTextView.setText("correct answers: " + correctAnswer + "\n wrong answers: " + wrongAnswer);
        }
    };
//    ====End of Timer====

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        initialize();
        myGetIntent();
    }

    private void initialize() {
        questionsList = new ArrayList<>();
        questionTextView = findViewById(R.id.question);
        answerTextView = findViewById(R.id.answer);
        scoreTextView = findViewById(R.id.score);
        timerTextView = findViewById(R.id.timer);
        responseTextView = findViewById(R.id.response);
        playerNameTextView = findViewById(R.id.playerNameTextView);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(this);
        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(this);
        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(this);
        btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(this);

        btnMinus = findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(this);
        btnDot = findViewById(R.id.btnDot);
        btnDot.setOnClickListener(this);

        btnAnswer = findViewById(R.id.btnAnswer);
        btnAnswer.setOnClickListener(this);
        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        btnResults = findViewById(R.id.btnResults);

        //make correct/incorrect view invisible at the beginning
        responseTextView.setVisibility(View.INVISIBLE);

//        initialize the random number generator:
        random = new Random();

        chooseQuestion();
        timer.start();
    }
    //    this method is going to execute every time we need a new question.
    //    It will choose the operator and operands at random.
    private void chooseQuestion() {
    //        reset the answerTextView. it must be reset each time a new question is generated:
        answerTextView.setText("= ?");
    //       Select an operator at random, making sure it is within the range of the operators array
    //      {"+", "-", "x", "/"} and choose operands withing the range:
        operator = random.nextInt(operators.length);
        operand1 = random.nextInt(10);
        operand2 = random.nextInt(10);
        //calculate the answer
        switch (operator) {
            case ADD_OPERATOR:
                answer = operand1 + operand2;
                break;
            case SUBTRACT_OPERATOR:
                while (operand2 > operand1) {
                    operand1 = random.nextInt(10);
                    operand2 = random.nextInt(10);
            }
                answer = operand1 - operand2;
                break;
            case MULTYPLY_OPERATOR:
                answer = operand1 * operand2;
                break;
            case DIVIDE_OPERATOR:
                  while  ((((double) operand1 / (double) operand2) % 1 > 0) || (operand2 == 0)) {
                    operand1 = random.nextInt(10);
                    operand2 = random.nextInt(10);
            }
                answer = operand1 / operand2;
                break;
            default:
                break;
        }
        // display the question to the user:
        question = operand1 + " " + operators[operator] + " " + operand2;
        questionTextView.setText(question);
    }

    @Override
//    Find out which button was clicked:
    public void onClick(View view) {
          switch(view.getId()){
              case R.id.btnAnswer:
                  userAnswer();
                  break;
              case R.id.btnClear:
                  cleanUserAnswer();
                  break;
              default:
                  //            invisible title correct/incorrect while we type numbers
                  responseTextView.setVisibility(View.INVISIBLE);
                  //            handle all number buttons in the same way, retrieving the number pressed from the tag:
                  int enteredNumber = Integer.parseInt(view.getTag().toString());
                  //           Check the currently displayed answer and respond accordingly,
                  //            either setting or appending the digit just pressed:
                  if (answerTextView.getText().toString().endsWith("?"))
                      answerTextView.setText("= " + enteredNumber);
                  else
                      answerTextView.append("" + enteredNumber);
          }
    }
    private void userAnswer() {
        //        handle clicks on the answer button
        String answerContent = answerTextView.getText().toString();
        //            if we have an answer, get anything after "=" sign: (substring(2)):
        if (!answerContent.endsWith("?")) {
        //                assign substring(2) to entered answer
            enteredAnswer = Integer.parseInt(answerContent.substring(2));
        //                receive a score for displaying
            int displayedScore = getScore();
        //                check whether the user's answer is correct or not and then increase or
        //                decrease scores and correctAnswer or wrongAnswer accordingly:
        //                make visiible a title correct/incorrect
            if (enteredAnswer == answer) {
                scoreTextView.setText("score: " + (displayedScore + 1));
                responseTextView.setText("Correct!!!");
                responseTextView.setVisibility(View.VISIBLE);
                correctAnswer++;
                right = true;
            } else {
                scoreTextView.setText("score: " + (displayedScore - 1));
                responseTextView.setText("Incorrect!!!");
                responseTextView.setVisibility(View.VISIBLE);
                wrongAnswer++;
                right = false;
            }
            addQuestionToArray();
            chooseQuestion();
        }
    }
    private void cleanUserAnswer() {
    //        clear panel with answer was typed
        answerTextView.setText("= ?");
    }
    //this method return the current score by reading the scoreTextView:
    private int getScore() {
        String scoreStr = scoreTextView.getText().toString();
        return Integer.parseInt(scoreStr.substring(scoreStr.lastIndexOf(" ") + 1));
    }

    public void addQuestionToArray() {
        Question questionObject = new Question(this.question, this.enteredAnswer, this.right);
        questionsList.add(questionObject);
    }

    public void showResults(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundleExtra", questionsList);

        Intent intent = new Intent(this, Scores.class);
        intent.putExtra("intentExtra", bundle);
        intent.putExtra("percentage", percentage);

        startActivity(intent);
    }
    private void myGetIntent() {
        String name = getIntent().getStringExtra("name");
        playerNameTextView.setText(String.valueOf(name));
    }
}
