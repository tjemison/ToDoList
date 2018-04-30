/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.delaroystudios.todoList;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class AddTaskActivity extends AppCompatActivity implements
        AddTaskFragment.OnFragmentInteractionListener {

    // Declare a member variable to keep track of a task's selected mPriority
    private int mPriority;
    private AddTaskFragment addTaskFragment;
    private static final String ADD_TASK_TAG = "AddTaskFragment Tag";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        /*This is how we handle orientation changes.
         *If savedInstanceState is null (meaning, the fragment has not had its state saved), create
         *a new AddTaskFragment. Otherwise, simply assign the savedInstanceState to the existing AddTaskFragment
         *via the supportFragmentManager.
         */
        if (savedInstanceState == null) {
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction fragTransaction = fragManager.beginTransaction();
            addTaskFragment = new AddTaskFragment();
            fragTransaction.add(R.id.AddTaskFragmentContainer, addTaskFragment, ADD_TASK_TAG);
            fragTransaction.commit();
        }
        else {
            addTaskFragment = (AddTaskFragment) getSupportFragmentManager().getFragment(savedInstanceState, ADD_TASK_TAG);
        }

    }

    public void onFragmentInteraction(Uri uri) {
        //Just here to satisfy the compiler.
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
