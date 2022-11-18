package com.example.travelmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class registration_page extends AppCompatActivity {
    private EditText registration_name;
    private TextInputLayout new_password_layout, confirm_password_layout;
    private String name, new_pass, confirm_pass;
    private TextView name_error, new_pass_error, confirm_pass_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        registration_name = findViewById(R.id.registration_page_name_edittext);
        new_password_layout = findViewById(R.id.registration_page_newPassword_layout);
        confirm_password_layout = findViewById(R.id.registration_page_confirmPassword_layout);
        name_error = findViewById(R.id.registration_page_name_error);
        new_pass_error = findViewById(R.id.registration_page_newPassword_error);
        confirm_pass_error = findViewById(R.id.registration_page_confirmPassword_error);
    }
    public boolean check(){
        if(Objects.equals(name, "") || Objects.equals(confirm_pass, "") ||
                Objects.equals(new_pass, "")){
            if(Objects.equals(name, ""))
                name_error.setText(R.string.this_field_cant_be_empty);
            else
                name_error.setText("");
            if(Objects.equals(confirm_pass, ""))
                confirm_pass_error.setText(R.string.this_field_cant_be_empty);
            else
                confirm_pass_error.setText("");
            if(Objects.equals(new_pass, ""))
                new_pass_error.setText(R.string.this_field_cant_be_empty);
            else{
                if(new_pass.length() < 6)
                    new_pass_error.setText(R.string.password_must_be_6_to_12_character);
                else
                    new_pass_error.setText("");
            }
            /*if(Objects.equals(mobile_string, ""))
                mobile_error.setText(R.string.this_field_cant_be_empty);
            else{
                if(mobile_string.length() != 10)
                    mobile_error.setText(R.string.invalid_number);
                else
                    mobile_error.setText("");
            }
            if(Objects.equals(email, ""))
                email_error.setText(R.string.this_field_cant_be_empty);
            else
                email_error.setText("");*/
            return false;
        }
        else
            return true;
    }

    public void next(View view) {
        name = registration_name.getText().toString();
        new_pass = Objects.requireNonNull(new_password_layout.getEditText()).getText().toString();
        confirm_pass = Objects.requireNonNull(confirm_password_layout.getEditText()).getText().toString();

        if(check()){
            if(new_pass.equals(confirm_pass)){
                Intent intent = new Intent(this, registration2.class);
                intent.putExtra("name", name);
                intent.putExtra("password", confirm_pass);
                startActivity(intent);
            }
            else
                confirm_pass_error.setText(R.string.password_dont_match);
        }
    }
}