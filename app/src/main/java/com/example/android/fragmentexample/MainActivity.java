/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fragmentexample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener{
    private Button btnOpen;
    private boolean isFragmentDisplayed = false;
    private int radioButtonChoice=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen=findViewById(R.id.btn_open);
    }

    public void openClick(View view) {
        if (!isFragmentDisplayed){
            displayFragment();
        } else {
            closeFragment();
        }
    }

    @SuppressLint("SetTextI18n")
    private void displayFragment(){
        SimpleFragment simpleFragment = SimpleFragment.newInstance(radioButtonChoice);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, simpleFragment)
                .addToBackStack(null).commit();
        isFragmentDisplayed = true;
        btnOpen.setText("Close");
    }

    @SuppressLint("SetTextI18n")
    private void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment)
                fragmentManager.findFragmentById(R.id.fragment_container);
        if (simpleFragment != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        isFragmentDisplayed = false;
        btnOpen.setText("Open");
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        radioButtonChoice = choice;
        Toast.makeText(this, "CHOICE " + choice, Toast.LENGTH_LONG).show();
    }

}
