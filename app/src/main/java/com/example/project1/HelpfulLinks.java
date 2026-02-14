package com.example.project1;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HelpfulLinks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_helpful_links);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set listeners for link buttons.
        findViewById(R.id.btn_link1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://developer.android.com");
                Log.d("Links log", "====Opened Android Developer Guide====");
            }
        });

        findViewById(R.id.btn_link2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://stackoverflow.com");
                Log.d("Links log", "====Opened Stack Overflow====");
            }
        });

        findViewById(R.id.btn_link3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://wikipedia.org");
                Log.d("Links log", "====Opened Wikipedia====");
            }
        });

        findViewById(R.id.btn_link4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://news.google.com");
                Log.d("Links log", "====Opened News Site====");
            }
        });

        // Set username in the header
        TextView tvHeader = findViewById(R.id.tv_username_header);
        tvHeader.setText("Welcome, " + User.name);

        // Home icon click listener
        ImageView ivHome = findViewById(R.id.iv_home_icon);
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("HelpfulLinks log", "====Home icon clicked - returning to Home====");
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                // Optional: finish();  // close current activity
            }
        });
    }

    // Method to open URL in browser.
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}