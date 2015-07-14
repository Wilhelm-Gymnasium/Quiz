package de.wilhelmgym.quiz.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import de.wilhelmgym.quiz.R;

public class LevelsAdapter extends GridAdapter {

    public LevelsAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Resources resources = getContext().getResources();

        //TODO create icons
        int[] iconResIds = {
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher
        };
        Bitmap icon = BitmapFactory.decodeResource(resources, iconResIds[position]);
        holder.image.setImageBitmap(icon);
        holder.image.setScaleType(ImageView.ScaleType.CENTER);

        int[] textColorResIds = {
                R.color.text_color_tile_noob,
                R.color.text_color_tile_easy,
                R.color.text_color_tile_medium,
                R.color.text_color_tile_advanced,
                R.color.text_color_tile_hard,
                R.color.text_color_tile_extreme,
        };
        int[] backgroundLabelResIds = {
                R.color.background_tile_label_noob,
                R.color.background_tile_label_easy,
                R.color.background_tile_label_medium,
                R.color.background_tile_label_advanced,
                R.color.background_tile_label_hard,
                R.color.background_tile_label_extreme,
        };
        holder.label.setTextColor(resources.getColor(textColorResIds[position]));
        holder.itemView.setBackgroundColor(resources.getColor(backgroundLabelResIds[position]));
        holder.label.setText(resources.getStringArray(R.array.levels)[position]);

        int[] backgroundResIds = {
                R.color.background_tile_noob,
                R.color.background_tile_easy,
                R.color.background_tile_medium,
                R.color.background_tile_advanced,
                R.color.background_tile_hard,
                R.color.background_tile_extreme,
        };
        holder.itemView.setBackgroundColor(resources.getColor(backgroundResIds[position]));
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
