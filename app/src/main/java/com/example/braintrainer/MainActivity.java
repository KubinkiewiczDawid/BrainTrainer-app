package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    TextView summaryText;
    Button playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int x;
    int y;
    int locationOfCorrectAnswer;



    public void playAgain(View view){
        trainingStart();
        playAgainButton.setVisibility(View.INVISIBLE);
    }

    public void trainingStart(){
        countDownTimer = new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                TextView timeText = findViewById(R.id.timeTextView);
                timeText.setText(Integer.toString((int)millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton = findViewById(R.id.playAgainButton);

                playAgainButton.setVisibility(View.VISIBLE);
            }

        }.start();

        createRandomSummary();
    }

    public void chooseAnswer(View view){

        createRandomSummary();

        //Sprawdzamy ktory przycisk zostal nacisniety
        Button counter = (Button) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());


    }

    public void start(View view){
        Button startButton = findViewById(R.id.startButton);

        startButton.setVisibility(View.INVISIBLE);

        trainingStart();
    }

    public void createRandomSummary(){
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
