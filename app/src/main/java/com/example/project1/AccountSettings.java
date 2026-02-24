package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AccountSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_settings);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Display username and current password from global variables
        TextView tvUsername = findViewById(R.id.tv_username);
        TextView tvPassword = findViewById(R.id.tv_password);
        tvUsername.setText("Username: " + User.username);
        tvPassword.setText("Password: " + User.password);


        // New password input field
        EditText etNewPassword = findViewById(R.id.et_new_password);

        // Change password button
        Button btnChange = findViewById(R.id.btn_change_password);

        // Change password listener
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = etNewPassword.getText().toString().trim();

                if (!newPassword.equals("")) {
                    User.password = newPassword;
                    tvPassword.setText("Password: " + User.password);
                    Toast.makeText(AccountSettings.this, getString(R.string.password_changed), Toast.LENGTH_SHORT).show();
                    Log.d("AccountSettings log", "====Password changed successfully====");
                } else {
                    Toast.makeText(AccountSettings.this, "Please enter a new password", Toast.LENGTH_SHORT).show();
                    Log.d("AccountSettings log", "====New password field is empty====");
                }
            }
        });

        // Header username
        TextView tvHeader = findViewById(R.id.tv_username_header);
        tvHeader.setText("Welcome, " + User.name);


        // Home icon click
        ImageView ivHome = findViewById(R.id.iv_home_icon);
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("AccountSettings log", "====Home icon clicked - returning to Home====");
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
    }
}