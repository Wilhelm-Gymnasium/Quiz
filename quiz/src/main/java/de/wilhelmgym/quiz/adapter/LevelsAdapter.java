package de.wilhelmgym.quiz.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
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

        //TODO create icons (Börgi)
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

        int[] colorResIds = {
                R.color.noob,
                R.color.easy,
                R.color.medium,
                R.color.advanced,
                R.color.hard,
                R.color.extreme,
        };
        Palette.Swatch swatch = new Palette.Swatch(resources.getColor(colorResIds[position]), 0);

        holder.label.setTextColor(swatch.getTitleTextColor());
        holder.label.setBackgroundColor(swatch.getRgb());
        holder.label.setText(resources.getStringArray(R.array.levels)[position]);


        float[] hsl = swatch.getHsl();
        hsl[1] *= 0.85;
        hsl[2] *= 1.5;
        holder.itemView.setBackgroundColor(Color.HSVToColor(hsl));
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
