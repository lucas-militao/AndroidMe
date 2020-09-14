package com.example.android.android_me.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.androidMeLinearLayout) != null) {
            mTwoPane = true;

            Button nextButton = (Button) findViewById(R.id.nextButton);
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.imagesGridView);
            gridView.setNumColumns(2);

            if (savedInstanceState == null) {

                FragmentManager fragmentManager = getSupportFragmentManager();

                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setmImageIds(AndroidImageAssets.getHeads());
                fragmentManager.beginTransaction()
                        .add(R.id.headContainer, headFragment)
                        .commit();

                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
                fragmentManager.beginTransaction()
                        .add(R.id.bodyContainer, bodyFragment)
                        .commit();

                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setmImageIds(AndroidImageAssets.getLegs());
                fragmentManager.beginTransaction()
                        .add(R.id.legContainer, legFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {
        int bodyPartNumber = position / 12;

        int listIndex = position - 12*bodyPartNumber;

        if (mTwoPane) {

            BodyPartFragment newFragment = new BodyPartFragment();

            switch (bodyPartNumber) {
                case 0:
                    newFragment.setmImageIds(AndroidImageAssets.getHeads());
                    newFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.headContainer, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setmImageIds(AndroidImageAssets.getBodies());
                    newFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.bodyContainer, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setmImageIds(AndroidImageAssets.getLegs());
                    newFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.legContainer, newFragment)
                            .commit();
                    break;
                default: break;
            }

        } else {

            switch (bodyPartNumber) {
                case 0: headIndex = listIndex;
                    break;
                case 1: bodyIndex = listIndex;
                    break;
                case 2: legIndex = listIndex;
                    break;
                default: break;
            }
        }

        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
