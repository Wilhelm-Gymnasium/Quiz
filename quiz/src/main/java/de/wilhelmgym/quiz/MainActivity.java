package de.wilhelmgym.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.wilhelmgym.quiz.structure.Answers;
import de.wilhelmgym.quiz.structure.Question;
import de.wilhelmgym.quiz.validator.Validator;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Quiz";

    /* TODO App icon */

    @InjectView(R.id.easy) FrameLayout easy;
    @InjectView(R.id.normal) FrameLayout normal;
    @InjectView(R.id.hard) FrameLayout hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //TODO open GameActivity on click. "Intent"s should be used
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "This should create a new easy game.", Toast.LENGTH_SHORT).show();

                Answers answers = new Answers();
                answers.add("answer0 answer2 answer3");

                Validator validator = new Validator();
                validator.validate(new Question("question", answers, "category"), "answer1 answer2 answer4");
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "This should create a new normal game.", Toast.LENGTH_SHORT).show();
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "This should create a new hard game.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
