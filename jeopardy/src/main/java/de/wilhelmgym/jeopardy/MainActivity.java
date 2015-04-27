package de.wilhelmgym.jeopardy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.test) TextView test;

    /* TODO App icon */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //TODO create category choosing layout

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getVisibility() == View.VISIBLE) {
                    v.setVisibility(View.INVISIBLE);
                }
                else{
                    v.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
