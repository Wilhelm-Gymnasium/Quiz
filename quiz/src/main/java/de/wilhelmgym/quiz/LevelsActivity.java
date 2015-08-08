package de.wilhelmgym.quiz;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Pair;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.wilhelmgym.quiz.adapter.GridAdapter;
import de.wilhelmgym.quiz.adapter.LevelsAdapter;
import de.wilhelmgym.quiz.helper.TransitionHelper;
import de.wilhelmgym.quiz.recyclerview.SpacingItemDecoration;
import de.wilhelmgym.quiz.recyclerview.SpanningGridLayoutManager;

public class LevelsActivity extends AppCompatActivity {

    public static final String EXTRA_LEVEL_COLOR = "level_color";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.grid)
    RecyclerView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Transition sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.levels_enter);
            getWindow().setSharedElementEnterTransition(sharedElementEnterTransition);
        }

        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.background_grid)));

        setContentView(R.layout.activity_levels);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        animateToolbar();

        SpanningGridLayoutManager layoutManager = new SpanningGridLayoutManager(this, getResources().getInteger(R.integer.span_count));
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        grid.setLayoutManager(layoutManager);

        SpacingItemDecoration decor = new SpacingItemDecoration(getResources().getDimensionPixelSize(R.dimen.baseline_half));
        grid.addItemDecoration(decor);

        LevelsAdapter adapter = new LevelsAdapter(this);
        adapter.setOnItemClickListener(new GridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GridAdapter adapter, int position, GridAdapter.ViewHolder holder, int color) {
                Intent intent = new Intent(LevelsActivity.this, QuestionActivity.class);
                //TODO pass level as extra (Börgi)

                intent.putExtra(EXTRA_LEVEL_COLOR, color);
                if (getIntent().hasExtra(CategoriesActivity.EXTRA_CATEGORY_COLOR)) {
                    intent.putExtra(CategoriesActivity.EXTRA_CATEGORY_COLOR, getIntent().getIntExtra(CategoriesActivity.EXTRA_CATEGORY_COLOR, getResources().getColor(R.color.background_tile)));
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    final Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(LevelsActivity.this, false,
                            new Pair<>(holder.getLabel(), getString(R.string.transition_name_levels_toolbar)));
                    Bundle options = ActivityOptions.makeSceneTransitionAnimation(LevelsActivity.this, pairs).toBundle();
                    startActivity(intent, options);
                } else {
                    startActivity(intent);
                }
            }
        });
        grid.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void animateToolbar() {
        if (getIntent().hasExtra(CategoriesActivity.EXTRA_CATEGORY_COLOR)) {
            Palette.Swatch swatch = new Palette.Swatch(getIntent().getIntExtra(CategoriesActivity.EXTRA_CATEGORY_COLOR, getResources().getColor(R.color.color_primary)), 0);
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
}
