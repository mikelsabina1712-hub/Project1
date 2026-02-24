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

        // Listeners for link buttons
        findViewById(R.id.btn_link1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://www.lamoncloa.gob.es/Paginas/index.aspx");
                Log.d("Links log", "====Opened Spanish Government Web====");
            }
        });

        findViewById(R.id.btn_link2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://cs.richmond.edu/Academics/courses/index.html");
                Log.d("Links log", "====Opened CS Richmond Courses====");
            }
        });

        findViewById(R.id.btn_link3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://www.elmundo.es/");
                Log.d("Links log", "====Opened El Mundo====");
            }
        });

        findViewById(R.id.btn_link4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://spidertechnet.richmond.edu/TDClient/1955/Portal/KB/ArticleDet?ID=125025");
                Log.d("Links log", "====Opened UR VPN Installation Web====");
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
                Log.d("HelpfulLinks log", "====Home icon clicked - returning to Home====");
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
    }

    // Open url in browser
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}