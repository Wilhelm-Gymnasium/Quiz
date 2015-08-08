package de.wilhelmgym.quiz;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.wilhelmgym.quiz.adapter.CategoriesAdapter;
import de.wilhelmgym.quiz.adapter.GridAdapter;
import de.wilhelmgym.quiz.helper.TransitionHelper;
import de.wilhelmgym.quiz.recyclerview.SpacingItemDecoration;
import de.wilhelmgym.quiz.recyclerview.SpanningGridLayoutManager;

public class CategoriesActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY_COLOR = "category_color";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.grid)
    RecyclerView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.background_grid)));

        setContentView(R.layout.activity_categories);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        SpanningGridLayoutManager layoutManager = new SpanningGridLayoutManager(this, getResources().getInteger(R.integer.span_count));
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        grid.setLayoutManager(layoutManager);

        SpacingItemDecoration decor = new SpacingItemDecoration(getResources().getDimensionPixelSize(R.dimen.baseline_half));
        grid.addItemDecoration(decor);

        CategoriesAdapter adapter = new CategoriesAdapter(this);
        adapter.setOnItemClickListener(new GridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GridAdapter adapter, int position, GridAdapter.ViewHolder holder, int color) {
                Intent intent = new Intent(CategoriesActivity.this, LevelsActivity.class);
                //TODO pass category as extra (Börgi)

                intent.putExtra(EXTRA_CATEGORY_COLOR, color);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    final Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(CategoriesActivity.this, false,
                            new Pair<>(holder.getLabel(), getString(R.string.transition_name_levels_toolbar)));
                    Bundle options = ActivityOptions.makeSceneTransitionAnimation(CategoriesActivity.this, pairs).toBundle();
                    startActivity(intent, options);
                } else {
                    startActivity(intent);
                }
            }
        });
        grid.setAdapter(adapter);
    }
}
