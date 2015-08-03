package de.wilhelmgym.quiz.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import de.wilhelmgym.quiz.R;

public class CategoriesAdapter extends GridAdapter {


    public static Integer ANIMATIONLENGTH = 1500;

    public CategoriesAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        final Resources resources = getContext().getResources();

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

        //holder.image.setAlpha(100);

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

                Integer BackgroundColorFrom = R.color.background_tile;
                Integer BackgroundColorTo = swatch.getRgb();
                ValueAnimator BackgroundColorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), BackgroundColorFrom, BackgroundColorTo);
                BackgroundColorAnimation.setDuration(ANIMATIONLENGTH);
                BackgroundColorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        holder.label.setBackgroundColor((Integer) animator.getAnimatedValue());
                    }

                });




                Integer TextColorFrom = R.color.text_color_tile_label;
                Integer TextColorTo = swatch.getTitleTextColor();
                ValueAnimator TextColorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), TextColorFrom, TextColorTo);
                TextColorAnimation.setDuration(ANIMATIONLENGTH);
                TextColorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        holder.label.setTextColor((Integer) animator.getAnimatedValue());
                    }

                });



                BackgroundColorAnimation.start();
                TextColorAnimation.start();
            }
        });

        holder.label.setText(resources.getStringArray(R.array.categories)[position]);
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
