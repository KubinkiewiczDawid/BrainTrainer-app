package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    TextView summaryText;
    TextView pointsText;
    TextView resultTextView;
    Button playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int x;
    int y;
    int locationOfCorrectAnswer;
    int counter = 0;
    int correctCount = 0;


    public void playAgain(View view){
        Intent restartIntent = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(restartIntent);
        playAgainButton.setVisibility(View.INVISIBLE);
    }

    public void trainingStart(){

        createRandomSummary();


        countDownTimer = new CountDownTimer(4000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                TextView timeText = findViewById(R.id.timeTextView);
                timeText.setText(Integer.toString((int)millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton = findViewById(R.id.playAgainButton);

                playAgainButton.setVisibility(View.VISIBLE);

                resultTextView = findViewById(R.id.resultTextView);

                pointsText = findViewById(R.id.endPointsText);

                resultTextView.setText("Time's up!");

                if(resultTextView.getVisibility() != View.VISIBLE){
                    pointsText.setText("U didn't answer any calculation");

                }else{
                    if(correctCount == 1){
                        pointsText.setText("U've got " + Integer.toString(correctCount) + " point for " + " 1 answer");
                    }else {
                        pointsText.setText("U've got " + Integer.toString(correctCount) + " points for " + Integer.toString(counter) + " answers");
                    }
                }

                pointsText.setVisibility(View.VISIBLE);
            }

        }.start();
    }

    public void chooseAnswer(View view){

        counter++;

        resultTextView = findViewById(R.id.resultTextView);

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

    public void start(View view){
        Button startButton = findViewById(R.id.startButton);

        startButton.setVisibility(View.INVISIBLE);

        trainingStart();
    }

    public void createRandomSummary(){

        pointsText = findViewById(R.id.pointsTextView);
        pointsText.setText(correctCount + "/"+ counter);

        summaryText = findViewById(R.id.summaryText);

        Random rand = new Random();
        x = rand.nextInt(50) + 1;
        y = rand.nextInt(50) + 1;

        summaryText.setText(x + " + " + y);

        createAnswers();
    }

    public void createAnswers(){

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

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

        Button startButton = findViewById(R.id.startButton);

        startButton.setVisibility(View.VISIBLE);

    }
}
