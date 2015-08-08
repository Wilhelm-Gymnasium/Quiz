package de.wilhelmgym.quiz.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.wilhelmgym.quiz.R;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private Context context;

    private OnItemClickListener listener;

    public GridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable labelBackground = holder.label.getBackground();
                Palette.Swatch colors = new Palette.Swatch(Color.BLUE, 0);
                if (labelBackground instanceof ColorDrawable) {
                    listener.onItemClick(GridAdapter.this, position, holder, ((ColorDrawable) labelBackground).getColor());
                } else {
                    listener.onItemClick(GridAdapter.this, position, holder, context.getResources().getColor(R.color.background_tile));
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public OnItemClickListener getOnItemClickListener() {
        return listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(GridAdapter adapter, int position, ViewHolder holder, int color);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.image)
        ImageView image;
        @InjectView(R.id.label)
        TextView label;
        View.OnClickListener listener;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClick(v);
            }
        }

        public View getItemView() {
            return itemView;
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getLabel() {
            return label;
        }
    }
}
