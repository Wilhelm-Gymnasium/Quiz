package de.wilhelmgym.quiz.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import de.wilhelmgym.quiz.R;

public class CategoriesAdapter extends GridAdapter {

    public CategoriesAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Resources resources = getContext().getResources();

        //TODO find, draw or shoot real images (license needed) (Wiwi)
        int[] imageResIds = {
                R.mipmap.category0,
                R.mipmap.category1,
                R.mipmap.category2,
                R.mipmap.category3,
                R.mipmap.category4,
                R.mipmap.category5
        };
        Bitmap image = BitmapFactory.decodeResource(resources, imageResIds[position]);
        holder.image.setImageBitmap(image);
        holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Palette.from(image).resizeBitmapSize(500).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch;
                if (palette.getVibrantSwatch() != null) {
                    swatch = palette.getVibrantSwatch();
                } else if (palette.getDarkVibrantSwatch() != null) {
                    swatch = palette.getDarkVibrantSwatch();
                } else if (palette.getLightVibrantSwatch() != null) {
                    swatch = palette.getLightVibrantSwatch();
                } else {
                    swatch = palette.getMutedSwatch();
                }
                //TODO animate color change (Börgi)
                holder.label.setBackgroundColor(swatch.getRgb());
                holder.label.setTextColor(swatch.getTitleTextColor());
            }
        });

        holder.label.setText(resources.getStringArray(R.array.categories)[position]);
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
