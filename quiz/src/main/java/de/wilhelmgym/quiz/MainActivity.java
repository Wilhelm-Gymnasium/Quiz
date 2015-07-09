package de.wilhelmgym.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

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
