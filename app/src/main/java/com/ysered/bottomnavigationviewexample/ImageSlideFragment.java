package com.ysered.bottomnavigationviewexample;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageSlideFragment extends Fragment {
    private static final String TAG = ImageSlideFragment.class.getSimpleName();

    private static final String ARG_IMAGE_INDEX = "arg_image_index";
    private static final int[] IMAGE_RESOURCES = {
            R.drawable.android01,
            R.drawable.android02
    };

    private int imageIndex;
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
            imageIndex = args.getInt(ARG_IMAGE_INDEX);
            imageResource = IMAGE_RESOURCES[imageIndex];
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_image_slide, container, false);

        final ImageView slideImage = (ImageView) view.findViewById(R.id.slideImage);
        final TextView slideText = (TextView) view.findViewById(R.id.slideText);

        slideImage.setImageResource(imageResource);
        slideText.setText(String.format("Slide %s", (imageIndex + 1)));

        return view;
    }
}
