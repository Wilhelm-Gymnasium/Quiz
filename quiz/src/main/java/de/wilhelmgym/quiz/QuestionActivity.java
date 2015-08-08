package de.wilhelmgym.quiz;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Transition sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.levels_enter);
            getWindow().setSharedElementEnterTransition(sharedElementEnterTransition);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        animateToolbar();

        //TODO improve question layout (with help) (Luci)
        answer.requestFocus();

        fab.setBackgroundTintList(getResources().getColorStateList(R.color.fab));
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

    private void animateToolbar() {
        if (getIntent().hasExtra(LevelsActivity.EXTRA_LEVEL_COLOR)) {
            Palette.Swatch swatch = new Palette.Swatch(getIntent().getIntExtra(LevelsActivity.EXTRA_LEVEL_COLOR, getResources().getColor(R.color.color_primary)), 0);
            toolbar.setBackgroundColor(swatch.getRgb());
            toolbar.setTitleTextColor(swatch.getTitleTextColor());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                float[] hsl = swatch.getHsl();
                hsl[1] *= 0.75;
                hsl[2] *= 0.95;
                getWindow().setStatusBarColor(Color.HSVToColor(hsl));
            }
        }
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
            validator.validate(new Question("Wie heißt die Deutsche Bundeskanzlerin (2015)?", answers, "Politik"), "Angela merkwl");
            //Fake END
            // TODO implement real behavior (Heini)
            return null;
        }

        @Override
        protected void onPostExecute(Boolean validAnswer) {
            fabProgress.beginFinalAnimation();
            fabProgress.attachListener(new FABProgressListener() {
                @Override
                public void onFABProgressAnimationEnd() {
                    // TODO start new activity (circular reveal) (Heini)
                }
            });
        }
    }
}
