package de.wilhelmgym.quiz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.jorgecastilloprz.FABProgressCircle;
import com.github.jorgecastilloprz.listeners.FABProgressListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.wilhelmgym.quiz.structure.Answers;
import de.wilhelmgym.quiz.structure.Question;
import de.wilhelmgym.quiz.validator.Validator;

public class QuestionActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.question)
    TextView question;
    @InjectView(R.id.answer)
    EditText answer;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.fabProgress)
    FABProgressCircle fabProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        //TODO improve question layout (with help)
        answer.requestFocus();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setClickable(false);
                answer.setEnabled(false);
                fabProgress.show();
                new ValidatorTask().execute();
            }
        });
    }

    private class ValidatorTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            //Fake operation to simulate the relatively time intensive validation of entered Answer
            Answers answers = new Answers();
            answers.add("Angela Merkel");
            answers.add("Angela Dorothea Merkel");
            answers.add("Angela Kasner");
            answers.add("Angela Dorothea Kasner");

            Validator validator = new Validator();
            validator.validate(new Question("Wie heißt die Deutsche Bundeskanzlerin (2015)?", answers, "Politik"), "Angelaa merkwl");
            //Fake END
            // TODO implement real behavior
            return null;
        }

        @Override
        protected void onPostExecute(Boolean validAnswer) {
            fabProgress.beginFinalAnimation();
            fabProgress.attachListener(new FABProgressListener() {
                @Override
                public void onFABProgressAnimationEnd() {
                    // TODO start new activity (circular reveal)
                }
            });
        }
    }
}
