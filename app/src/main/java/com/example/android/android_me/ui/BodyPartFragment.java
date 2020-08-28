package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

public class BodyPartFragment extends Fragment {

    // Variables to store a list of image resources and the index of the image that this fragment displays
    private List<Integer> mImageIds;
    private int mListIndex;

    // Mandatory constructor for instatiating the fragment
    public BodyPartFragment() {
    }

    /*
    * Inflates the fragment layout and sets any image resources
    * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        //Get a reference to the ImageView in the fragment layout
        ImageView imageView = (ImageView) rootView.findViewById(R.id.bodyPartImageView);

        if (mImageIds != null) {
            imageView.setImageResource(mImageIds.get(mListIndex));
        } else {
            Log.v("IMAGE_IDS", "This fragment has a null list of image id's");
        }

        // Return root view
        return rootView;
    }

    public void setmImageIds(List<Integer> imageIds) {
        this.mImageIds = imageIds;
    }

    public void setmListIndex(int index) {
        this.mListIndex = index;
    }
}
