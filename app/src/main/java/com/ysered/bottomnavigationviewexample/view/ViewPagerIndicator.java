package com.ysered.bottomnavigationviewexample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ysered.bottomnavigationviewexample.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerIndicator extends LinearLayout {

    private int count;
    private int current = 0;
    private @DrawableRes int imageResource;
    private @DrawableRes int selectedImageResource;
    private int margin;

    private List<ImageView> indicatorImages;

    public ViewPagerIndicator(Context context) {
        super(context);
        init();
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        applyAttributes(attrs);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        applyAttributes(attrs);
    }

    public void setCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Indicator count should be > 0");
        }
        this.count = count;
        initIndicators(count);
    }

    public void setCurrent(int current) {
        if (count <= 0) {
            throw new IllegalArgumentException("Indicator count should be > 0");
        } else if (current < 0) {
            throw new IllegalArgumentException("Current indicator position should be > 0");
        }
        updateIndicators(current);
        this.current = current;
    }

    private void init() {
        setGravity(Gravity.CENTER);
        setOrientation(HORIZONTAL);
    }

    private void applyAttributes(AttributeSet attrs) {
        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        imageResource = typedArray.getResourceId(R.styleable.ViewPagerIndicator_indicator_drawable, R.drawable.ic_indicator);
        selectedImageResource = typedArray.getResourceId(R.styleable.ViewPagerIndicator_selected_indicator_drawable,
                R.drawable.ic_indicator_selected);
        margin = (int) typedArray.getDimension(R.styleable.ViewPagerIndicator_indicator_margin,
                getResources().getDimension(R.dimen.indicator_margin));
        typedArray.recycle();
    }

    /**
     * Creates and initializes list of indicator images {@link #indicatorImages} and adds them to layout.
     * Sets different images for selected and unselected pages.
     */
    private void initIndicators(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Indicator count should be > 0");
        }

        indicatorImages = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            final ImageView indicatorImage = new ImageView(getContext());
            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(margin, 0, margin, 0);
            indicatorImage.setLayoutParams(params);

            if (i == current) {
                indicatorImage.setImageResource(selectedImageResource);
            } else {
                indicatorImage.setImageResource(imageResource);
            }
            indicatorImages.add(indicatorImage);
            addView(indicatorImage);
        }
    }

    /**
     * Changes indicator images for old and new selected pages.
     *
     * @param newPosition position of new page
     */
    private void updateIndicators(int newPosition) {
        final ImageView oldImage = indicatorImages.get(current);
        final ImageView newImage = indicatorImages.get(newPosition);
        oldImage.setImageResource(imageResource);
        newImage.setImageResource(selectedImageResource);
    }
}
