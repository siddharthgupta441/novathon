package com.example.travelmate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    EditText login_email, login_password;
    TextView login_forgot, login_email_error, login_password_error;
    String login_email_string, login_password_string;
    DatabaseReference databaseReference;
    FirebaseDatabase db;
    ArrayList<User> list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_email = findViewById(R.id.login_page_email_edittext);
        login_password = findViewById(R.id.login_page_Password_edittext);
        login_forgot = findViewById(R.id.login_page_forgot_text);
        login_email_error = findViewById(R.id.login_page_login_email_error);
        login_password_error = findViewById(R.id.login_page_password_error);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null)
            logedIn_page();
    }

    public void forgot(View view) {
    }

    public void login(View view) {
        String email, password;
        login_email_string = login_email.getText().toString();
        login_password_string = login_password.getText().toString();
        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        if (!Objects.equals(login_email_string, "") && !Objects.equals(login_password_string, "")) {
            //if (login_email_string.equals(email) && login_password_string.equals(password)) {
            if(login_email_string.equals("siddharth") && login_password_string.equals("123456")){
                finish();
                Intent intent = new Intent(this, fragment_container.class);
                startActivity(intent);
            } else
                login_password_error.setText(R.string.wrong_email_password);
        }
        for(int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            email = user.getEmail();
            password = user.getPassword();
        }
        if (Objects.equals(login_email_string, "") || Objects.equals(login_password_string, "")) {
            if (Objects.equals(login_password_string, ""))
                login_password_error.setText(R.string.this_field_cant_be_empty);
            if (Objects.equals(login_email_string, ""))
                login_email_error.setText(R.string.this_field_cant_be_empty);
        }
    }

    public void signIn(View view) {
        Intent intent = new Intent(this, registration_page.class);
        startActivity(intent);
    }

   public void google_signIn(View view) {
        Intent gs_intent = gsc.getSignInIntent();
        startActivityForResult(gs_intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                logedIn_page();
            } catch (ApiException e) {
                Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void logedIn_page() {
        finish();
        Intent intent = new Intent(this, fragment_container.class);
        startActivity(intent);
    }
}