package com.freecodecamp.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout gameLayout;
    GridLayout gridLayout;
    Button goButton, playAgainButton, button0, button1, button2, button3;
    TextView sumTextView, resultTextView, scoreTextView, timerTextView, declarationTextView;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    ArrayList<Integer> answers =new ArrayList<>();

//    private void disableButtons(GridLayout layout) {    // in future i can use this commented code
//
//        // Get all touchable views
//        ArrayList<View> layoutButtons = layout.getTouchables();
//
//        // loop through them, if they are an instance of Button, disable it.
//        for(View v : layoutButtons){
//            if( v instanceof Button ) {
//                ((Button)v).setEnabled(false);
//            }
//        }
//    }

    public void chooseAnswer(View view){
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            score++;
            resultTextView.setText("Correct! :)");
        }
        else {
            resultTextView.setText("Wrong! :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }
    private void setGridLayoutButtonsFunc(boolean enableOrDisable){
        button0.setClickable(enableOrDisable);
        button1.setClickable(enableOrDisable);
        button2.setClickable(enableOrDisable);
        button3.setClickable(enableOrDisable);
    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        setGridLayoutButtonsFunc(true);
        timerTextView.setText("30 s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        createCountDownTimer();
    }

    public void createCountDownTimer(){
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + " S");
            }

            @Override
            public void onFinish() {
                timerTextView.setText(0 + " S");

                resultTextView.setText("Time Up!");

                setGridLayoutButtonsFunc(false);

                gridLayout.setFocusable(false);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void start(View view){
//        goButton.setVisibility(View.INVISIBLE);
        goButton.animate().translationXBy(-800).rotation(36000).setDuration(800);
        declarationTextView.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        createCountDownTimer();

    }

    public void newQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21)+1;
        int b = rand.nextInt(21);

        sumTextView.setText(a +" + "+ b);

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else {
                int wrongAnswer = rand.nextInt(41)+1;
                while (wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41)+1;
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = findViewById(R.id.gameLayout);
        gridLayout = findViewById(R.id.gridLayout);

        goButton = findViewById(R.id.goButton);
        playAgainButton = findViewById(R.id.playAgainButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        declarationTextView = findViewById(R.id.declarationTextView);

//        goButton.setVisibility(View.VISIBLE);
        goButton.setX(-800);
        goButton.animate().translationXBy(800).rotation(720).setDuration(1300);
        playAgainButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        newQuestion(); // initializes new quiz method


    }
}