/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.android_me.ui;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if (savedInstanceState == null) {
            // create fragment object
            BodyItemFragment bodyHeadFragment = new BodyItemFragment();
            // set image
            bodyHeadFragment.setIntegerList(AndroidImageAssets.getHeads());
            int headIndex = getIntent().getIntExtra("headIndex", 0);
            bodyHeadFragment.setIntIndex(headIndex);
            // add the fragment to screen
            FragmentManager fragmentManager = getSupportFragmentManager();
            // fragment transaction
            fragmentManager.beginTransaction()
                    .add(R.id.container_for_head, bodyHeadFragment)
                    .commit();

            BodyItemFragment bodyFragment = new BodyItemFragment();
            bodyFragment.setIntegerList(AndroidImageAssets.getBodies());
            int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
            bodyFragment.setIntIndex(bodyIndex);

            fragmentManager.beginTransaction()
                    .add(R.id.container_for_body, bodyFragment)
                    .commit();

            BodyItemFragment legFragment = new BodyItemFragment();
            legFragment.setIntegerList(AndroidImageAssets.getLegs());
            int legIndex = getIntent().getIntExtra("legsIndex", 0);
            legFragment.setIntIndex(legIndex);

            fragmentManager.beginTransaction()
                    .add(R.id.container_for_legs, legFragment)
                    .commit();
        }
    }
}
