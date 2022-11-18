package com.example.travelmate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class registration2 extends AppCompatActivity {
    int year, month, day, SELECT_IMAGE_CODE = 1;
    private EditText dob_calender, registration_page_email_edittext, registration_page_mobile_edittext;
    private TextView dob_error, gender_error, registration_page_email_error;
    private CheckBox checkbox;
    private ImageView registration_page2_profile_pic_imageview;
    private DatabaseReference reference;
    private String dates, address, current_address;;
    private RadioGroup radioGroup;
    private RadioButton male, female;
    private  String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        dob_calender = findViewById(R.id.registration_page2_dob_edittext);
        registration_page2_profile_pic_imageview = findViewById(R.id.registration_page2_profile_pic_imageview);
        dob_error = findViewById(R.id.registration_page2_dob_error);
        radioGroup = findViewById(R.id.registration_page2_radio_group);
        male = findViewById(R.id.registration_page2_male_radio);
        female = findViewById(R.id.registration_page2_female_radio);
        gender_error = findViewById(R.id.registration_page2_gender_error);
        registration_page_email_edittext = findViewById(R.id.registration_page_email_edittext);
        registration_page_email_error = findViewById(R.id.registration_page_email_error);
        registration_page_mobile_edittext = findViewById(R.id.registration_page_mobile_edittext);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        reference = db.getReference().child("user");

    }
    public boolean check(){
            return true;
    }

    public void save(View view) {
        String email = registration_page_email_edittext.getText().toString();
        String mobile_string = registration_page_mobile_edittext.getText().toString();

        int radio = radioGroup.getCheckedRadioButtonId();
        male = findViewById(radio);
        gender = male.getText().toString();

        String name, password;
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        password = bundle.getString("password");

        if(check()){
            HashMap<String, String> user_map = new HashMap<>();
            user_map.put("user_name", name);
            user_map.put("user_email", email);
            user_map.put("user_mobile", mobile_string);
            user_map.put("user_dob", dates);
            user_map.put("password", password);
            user_map.put("gender", gender);

            reference.push().setValue(user_map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(registration2.this, "registration has successfully completed", Toast.LENGTH_LONG).show();
                }
            });
            Toast.makeText(this, "registration done", Toast.LENGTH_LONG).show();
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void dob_calender(View view) {
        final Calendar calendar =Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog RDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int rYear, int rMonth, int rDay) {
                rMonth = rMonth+1;
                dates = rDay+ "-" +rMonth+ "-" +rYear;
                dob_calender.setText(dates);
                year = rYear;
                month = rMonth;
                day = rDay;
            }
        },year, month, day);
        RDatePickerDialog.show();
    }

    public void profile_pic(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "title"), SELECT_IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_IMAGE_CODE){
            assert data != null;
            Uri uri = data.getData();
            registration_page2_profile_pic_imageview.setImageURI(uri);
        }
    }
}