package com.example.travelmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class fragment_home extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RadioGroup radioGroup;
        RadioButton choose;
        radioGroup = view.findViewById(R.id.user_selection_radio_group);
        choose = view.findViewById(R.id.user_selection_traveller);

        int radio = radioGroup.getCheckedRadioButtonId();
        choose = view.findViewById(radio);
        String activity = choose.getText().toString();
        if(Objects.equals(activity, "traveller")){
            Intent intent = new Intent(getActivity(), map.class);
            startActivity(intent);
        }
        return view;
    }
}
