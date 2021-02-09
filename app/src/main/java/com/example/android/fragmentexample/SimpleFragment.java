package com.example.android.fragmentexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SimpleFragment extends Fragment {
    private static final String CHOICE = "choice";
    private int radioButtonChoice = 2;
    private OnFragmentInteractionListener listener;

    public SimpleFragment() {}

    public static SimpleFragment newInstance(int choice){
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt(CHOICE, choice);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);

        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        final TextView tvHeader = rootView.findViewById(R.id.tv_header);

        if (getArguments().containsKey(CHOICE)){
            radioButtonChoice = getArguments().getInt(CHOICE);
            if (radioButtonChoice != 2){
                radioGroup.check(radioGroup.getChildAt(radioButtonChoice).getId());
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n") @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                View radioButton = radioGroup.findViewById(id);
                int index = radioGroup.indexOfChild(radioButton);
                switch (index){
                    case 0:
                        tvHeader.setText("Article: Like");
                        radioButtonChoice = 0;
                        break;

                    case 1:
                        tvHeader.setText("Article: Thanks");
                        radioButtonChoice = 1;
                        break;

                    default:
                        radioButtonChoice = 2;
                        break;
                }
                listener.onRadioButtonChoice(radioButtonChoice);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        }
    }

    public interface OnFragmentInteractionListener{
        void onRadioButtonChoice (int choice);
    }
}