package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    TextView summaryText;
    TextView pointsText;
    TextView endPointsText;
    TextView resultTextView;
    TextView timeText;
    Button playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int x;
    int y;
    int locationOfCorrectAnswer;
    int counter = 0;
    int correctCount = 0;
    RelativeLayout relativeLayout;
    Button startButton;
    Button button;
    Button button2;
    Button button3;
    Button button4;

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);

        relativeLayout.setVisibility(View.VISIBLE);

        trainingStart();
    }

    public void playAgain(View view){

        counter = 0;
        correctCount = 0;
        answers.removeAll(answers);
        endPointsText.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        pointsText.setText("0/0");

        button.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);

        trainingStart();
    }

    public void trainingStart(){

        createRandomSummary();

        countDownTimer = new CountDownTimer(3100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timeText.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);

                resultTextView.setText("Time's up!");

                if(counter == 0){
                    endPointsText.setText("U didn't answer any calculation");
                }else{
                    endPointsText.setText("Your score: " + correctCount + "/" + counter);
                }

                endPointsText.setVisibility(View.VISIBLE);

                button.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);

            }

        }.start();
    }

    public void chooseAnswer(View view){

        counter++;

        resultTextView.setVisibility(View.VISIBLE);

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            resultTextView.setText("Correct!");
            correctCount++;

        }else{
            resultTextView.setText("Wrong!");
        }


        answers.removeAll(answers);

        createRandomSummary();

    }

    public void createRandomSummary(){

        pointsText.setText(correctCount + "/"+ counter);

        summaryText = findViewById(R.id.summaryText);

        Random rand = new Random();
        x = rand.nextInt(49) + 1;
        y = rand.nextInt(49) + 1;

        summaryText.setText(x + " + " + y);

        createAnswers();
    }

    public void createAnswers(){

        Random rand = new Random();

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;

        for(int i = 0; i < 4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(x+y);
            }else {

                incorrectAnswer = rand.nextInt(100);

                while(incorrectAnswer == x + y){
                    incorrectAnswer = rand.nextInt(100);
                }

                answers.add(incorrectAnswer);
            }
        }

        button.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        playAgainButton = findViewById(R.id.playAgainButton);
        //Answers buttons
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        resultTextView = findViewById(R.id.resultTextView);
        pointsText = findViewById(R.id.pointsTextView);
        endPointsText = findViewById(R.id.endPointsText);
        timeText = findViewById(R.id.timeTextView);

        relativeLayout = findViewById(R.id.relativeLayout);
    }
}
