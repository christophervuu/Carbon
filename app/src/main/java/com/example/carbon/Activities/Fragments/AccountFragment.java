package com.example.carbon.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carbon.Activities.AddUserEmailActivity;
import com.example.carbon.R;

public class AccountFragment extends Fragment  {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_account, container, false);

        final Intent intent = new Intent(getActivity(), AddUserEmailActivity.class);
        Button mButton = view.findViewById(R.id.FragmentAccountButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });


        return view;
    }
}
