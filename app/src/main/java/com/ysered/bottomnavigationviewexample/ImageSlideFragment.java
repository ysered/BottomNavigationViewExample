package com.ysered.bottomnavigationviewexample;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageSlideFragment extends Fragment {

    private static final String ARG_IMAGE_INDEX = "arg_image_index";
    private static final int[] IMAGE_RESOURCES = {
            R.drawable.android01,
            R.drawable.android02
    };

    private @DrawableRes int imageResource;

    public static ImageSlideFragment getInstance(int position) {
        final Bundle args = new Bundle(1);
        args.putInt(ARG_IMAGE_INDEX, position);
        ImageSlideFragment fragment = new ImageSlideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            final int index = args.getInt(ARG_IMAGE_INDEX);
            imageResource = IMAGE_RESOURCES[index];
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_image_slide, container, false);

        final ImageView imageSlide = (ImageView) view.findViewById(R.id.slideImage);
        imageSlide.setImageResource(imageResource);

        return view;
    }
}
