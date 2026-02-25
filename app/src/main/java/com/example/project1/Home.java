package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Welcome message
        TextView tvWelcome = findViewById(R.id.tv_welcome);
        tvWelcome.setText("Welcome " + User.name + ".");

        // Header username
        TextView tvHeader = findViewById(R.id.tv_username_header);
        tvHeader.setText("Welcome, " + User.name);

        // Home icon click
        ImageView ivHome = findViewById(R.id.iv_home_icon);
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
        // Activities buttons
        Button btnAccountSettings = findViewById(R.id.btn_account_settings);
        btnAccountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountSettings.class);
                startActivity(intent);
            }
        });

        Button btnFavoriteMusic = findViewById(R.id.btn_favorite_music);
        btnFavoriteMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FavoriteMusic.class);
                startActivity(intent);
            }
        });

        Button btnBucketList = findViewById(R.id.btn_bucket_list);
        btnBucketList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BucketList.class);
                startActivity(intent);
            }
        });

        Button btnFavoriteMemories = findViewById(R.id.btn_favorite_memories);
        btnFavoriteMemories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FavoriteMemories.class);
                startActivity(intent);
            }
        });

        Button btnHelpfulLinks = findViewById(R.id.btn_helpful_links);
        btnHelpfulLinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpfulLinks.class);
                startActivity(intent);
            }
        });

        // Disable back button
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.d("Home log", "====Back button disabled====");
            }
        });
    }
}