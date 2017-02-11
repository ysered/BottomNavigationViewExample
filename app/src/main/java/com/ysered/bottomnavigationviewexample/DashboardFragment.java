package com.ysered.bottomnavigationviewexample;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DashboardFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private LinearLayout indicatorLayout;
    private ImageView[] indicatorImages;
    private int currentPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        indicatorLayout = (LinearLayout) view.findViewById(R.id.indicator);

        final PagerAdapter adapter = new DashboardPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        currentPosition = viewPager.getCurrentItem();
        initIndicators(adapter.getCount());

        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        updateIndicators(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * Create and initializes array {@link #indicatorImages} of indicator images and add to linear layout {@link #indicatorLayout}.
     * Sets different images for selected and unselected pages.
     *
     * @param count of pages to create individual indicators for them
     */
    private void initIndicators(int count) {
        final int indicatorMargin = (int) getResources().getDimension(R.dimen.indicator_margin);
        indicatorImages = new ImageView[count];
        for (int i = 0; i < count; i++) {
            final ImageView indicatorImage = new ImageView(getContext());
            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(indicatorMargin, 0, indicatorMargin, 0);
            indicatorImage.setLayoutParams(params);
            if (i == currentPosition) {
                indicatorImage.setImageResource(R.drawable.ic_indicator_selected);
            } else {
                indicatorImage.setImageResource(R.drawable.ic_indicator);
            }
            indicatorImages[i] = indicatorImage;
            indicatorLayout.addView(indicatorImage);
        }
    }

    /**
     * Changes indicator images for old and new selected pages.
     *
     * @param newPosition position of new page
     */
    private void updateIndicators(int newPosition) {
        final ImageView oldImage = indicatorImages[currentPosition];
        oldImage.setImageResource(R.drawable.ic_indicator);
        ObjectAnimator.ofFloat(oldImage, "scaleX", 1.6f, 1f).setDuration(200).start();
        ObjectAnimator.ofFloat(oldImage, "scaleY", 1.6f, 1f).setDuration(200).start();

        final ImageView newImage = indicatorImages[newPosition];
        newImage.setImageResource(R.drawable.ic_indicator_selected);
        ObjectAnimator.ofFloat(newImage, "scaleX", 0.6f, 1f).setDuration(200).start();
        ObjectAnimator.ofFloat(newImage, "scaleY", 0.6f, 1f).setDuration(200).start();

        currentPosition = newPosition;
    }
}
