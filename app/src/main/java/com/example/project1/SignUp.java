package com.example.project1;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Assign variables to views from layout, identify using their IDs.
        EditText etName = findViewById(R.id.et_name);
        RadioGroup radioGroupGender = findViewById(R.id.radio_group_gender);
        EditText etAge = findViewById(R.id.et_age);
        EditText etAddress = findViewById(R.id.et_address);
        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password);

        Button btnSignup = findViewById(R.id.btn_signup);

        // Setting an onClickListener on the above button to look for the click event.
        btnSignup.setOnClickListener(new View.OnClickListener() {
            // This part is inner class, as discussed in the previous lecture.
            @Override
            public void onClick(View view) {
                // Read the text from EditTexts and convert to strings.
                String name = etName.getText().toString().trim();
                String gender = "";
                int selectedId = radioGroupGender.getCheckedRadioButtonId();

                if (selectedId == R.id.rb_male) {
                    gender = "Male";
                    Log.d("SignUp log", "====Gender selected: Male====");
                } else if (selectedId == R.id.rb_female) {
                    gender = "Female";
                    Log.d("SignUp log", "====Gender selected: Female====");
                } else if (selectedId == R.id.rb_other) {
                    gender = "Other";
                    Log.d("SignUp log", "====Gender selected: Other====");
                } else {
                    // Optional: show message if nothing selected
                    Toast.makeText(SignUp.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                    Log.d("SignUp log", "====No gender selected====");
                    return;  // stop signup if gender not chosen
                }
                String age = etAge.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Check if all fields are filled.
                if (name.equals("") || gender.equals("") || age.equals("") || address.equals("") || username.equals("") || password.equals("")) {
                    Toast.makeText(SignUp.this, getString(R.string.fields_required), Toast.LENGTH_SHORT).show();
                    Log.d("SignUp log", "====Fields are required====");
                    return;
                }

                // Username validation: exactly 5 alphanumeric, lowercase, no space.
                if (!username.matches("^[a-z0-9]{5}$")) {
                    Toast.makeText(SignUp.this, getString(R.string.invalid_username), Toast.LENGTH_SHORT).show();
                    Log.d("SignUp log", "====Invalid username====");
                    return;
                }

                // Password validation: exactly 8 alphanumeric, start upper, has number, no space.
                if (!password.matches("^[A-Z][a-zA-Z0-9]{7}$") || !password.matches(".*\\d.*")) {
                    Toast.makeText(SignUp.this, getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();
                    Log.d("SignUp log", "====Invalid password====");
                    return;
                }

                // Set global variables (as per project instructions).
                User.name = name;
                User.gender = gender;
                User.age = age;
                User.address = address;
                User.username = username;
                User.password = password;

                Log.d("SignUp log", "====Sign up successful====");

                // Navigate to Login activity.
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}