package de.wilhelmgym.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.wilhelmgym.quiz.adapter.CategoriesAdapter;
import de.wilhelmgym.quiz.adapter.GridAdapter;
import de.wilhelmgym.quiz.recyclerview.SpacingItemDecoration;
import de.wilhelmgym.quiz.recyclerview.SpanningGridLayoutManager;

public class CategoriesActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.grid)
    RecyclerView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            public void onItemClick(GridAdapter adapter, int position) {
                //TODO pass category as extra (Heini)
                //TODO animations (Börgi)
                startActivity(new Intent(CategoriesActivity.this, LevelsActivity.class));
            }
        });
        grid.setAdapter(adapter);
    }
}
