package com.ysered.bottomnavigationviewexample.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ysered.bottomnavigationviewexample.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerIndicator extends LinearLayout {

    private int count;
    private int current = 0;
    private List<ImageView> indicatorImages;

    public ViewPagerIndicator(Context context) {
        super(context);
        init();
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
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

    /**
     * Creates and initializes list of indicator images {@link #indicatorImages} and adds to layout.
     * Sets different images for selected and unselected pages.
     */
    private void initIndicators(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Indicator count should be > 0");
        }

        final Resources resources = getResources();
        final int indicatorMargin = (int) resources.getDimension(R.dimen.indicator_margin);
        final int indicatorBounds = (int) resources.getDimension(R.dimen.indicator_bounds);

        indicatorImages = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            // create ImageView
            final ImageView indicatorImage = new ImageView(getContext());
            FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
            imageParams.gravity = Gravity.CENTER;
            indicatorImage.setLayoutParams(imageParams);

            // create FrameLayout and put ImageView inside it
            final FrameLayout indicatorFrame = new FrameLayout(getContext());
            indicatorFrame.addView(indicatorImage);
            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(indicatorBounds, indicatorBounds);
            params.setMargins(indicatorMargin, 0, indicatorMargin, 0);
            indicatorFrame.setLayoutParams(params);

            if (i == current) {
                indicatorImage.setImageResource(R.drawable.ic_indicator_selected);
            } else {
                indicatorImage.setImageResource(R.drawable.ic_indicator);
            }
            indicatorImages.add(indicatorImage);
            addView(indicatorFrame);
        }
    }

    /**
     * Changes indicator images for old and new selected pages.
     *
     * @param newPosition position of new page
     */
    private void updateIndicators(int newPosition) {
        final ImageView oldImage = indicatorImages.get(current);
        oldImage.setImageResource(R.drawable.ic_indicator);
        ObjectAnimator.ofFloat(oldImage, "scaleX", 1.6f, 1f).setDuration(200).start();
        ObjectAnimator.ofFloat(oldImage, "scaleY", 1.6f, 1f).setDuration(200).start();

        final ImageView newImage = indicatorImages.get(newPosition);
        newImage.setImageResource(R.drawable.ic_indicator_selected);
        ObjectAnimator.ofFloat(newImage, "scaleX", 0.6f, 1f).setDuration(200).start();
        ObjectAnimator.ofFloat(newImage, "scaleY", 0.6f, 1f).setDuration(200).start();
    }
}
