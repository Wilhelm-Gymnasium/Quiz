package de.wilhelmgym.quiz.adapter;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.graphics.Palette;
import android.util.Property;
import android.widget.ImageView;
import android.widget.TextView;

import de.wilhelmgym.quiz.R;

public class CategoriesAdapter extends GridAdapter {

    public static Integer ANIMATIONLENGTH = 1500;
    private final Property<TextView, Integer> PROPERTY_TEXT_COLOR = new Property<TextView, Integer>(Integer.class, "textColor") {
        @Override
        public Integer get(TextView object) {
            return object.getTextColors().getDefaultColor();
        }

        @Override
        public void set(TextView object, Integer value) {
            object.setTextColor(value);
        }
    };
    private final Property<TextView, Integer> PROPERTY_BACKGROUND_COLOR = new Property<TextView, Integer>(Integer.class, "backgroundColor") {
        @Override
        public Integer get(TextView object) {
            Drawable background = object.getBackground();
            if(background instanceof ColorDrawable){
                return ((ColorDrawable) background).getColor();
            }
            return Color.BLACK;
        }

        @Override
        public void set(TextView object, Integer value) {
            object.setBackgroundColor(value);
        }
    };

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

                ObjectAnimator animatorTextColor = ObjectAnimator.ofInt(holder.label, PROPERTY_TEXT_COLOR, swatch.getTitleTextColor());
                animatorTextColor.setEvaluator(new ArgbEvaluator());

                ObjectAnimator animatorBackgroundColor = ObjectAnimator.ofInt(holder.label, PROPERTY_BACKGROUND_COLOR, swatch.getRgb());
                animatorBackgroundColor.setEvaluator(new ArgbEvaluator());

                AnimatorSet animatorColors = new AnimatorSet();
                animatorColors.playTogether(animatorTextColor, animatorBackgroundColor);
                animatorColors.setDuration(resources.getInteger(android.R.integer.config_longAnimTime));
                animatorColors.start();
            }
        });

        holder.label.setText(resources.getStringArray(R.array.categories)[position]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            holder.label.setTransitionName(resources.getString(R.string.transition_name_levels_toolbar));
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
