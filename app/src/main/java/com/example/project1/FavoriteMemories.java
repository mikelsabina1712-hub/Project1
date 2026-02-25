package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FavoriteMemories extends AppCompatActivity {

    LinearLayout safariMemory;
    LinearLayout graduationMemory;
    LinearLayout footballMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorite_memories);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvHeader = findViewById(R.id.tv_username_header);
        tvHeader.setText("Welcome, " + User.name);

        safariMemory = findViewById(R.id.safari_memory);
        graduationMemory = findViewById(R.id.graduation_memory);
        footballMemory = findViewById(R.id.football_memory);

        EditText etFilter = findViewById(R.id.et_filter);
        Button btnFilter = findViewById(R.id.btn_submit_filter);

        btnFilter.setOnClickListener( view -> {
            String filter = etFilter.getText().toString().trim().toLowerCase();

            // While nothing is submitted in the filter, show all memories
            if (filter.isEmpty()) {
                safariMemory.setVisibility(View.VISIBLE);
                graduationMemory.setVisibility(View.VISIBLE);
                footballMemory.setVisibility(View.VISIBLE);
                return;
            }

            // Safari memory
            if ("roars at sunset".toLowerCase().contains(filter)) {
                safariMemory.setVisibility(View.VISIBLE);
            } else {
                safariMemory.setVisibility(View.GONE);
            }

            // Graduation memory
            if ("a milestone achieved".toLowerCase().contains(filter)) {
                graduationMemory.setVisibility(View.VISIBLE);
            } else {
                graduationMemory.setVisibility(View.GONE);
            }

            // Football memory
            if ("a magical night at the bernabéu".toLowerCase().contains(filter)) {
                footballMemory.setVisibility(View.VISIBLE);
            } else {
                footballMemory.setVisibility(View.GONE);
            }
        });

        // Home button
        ImageView ivHome = findViewById(R.id.iv_home_icon);
        ivHome.setOnClickListener(view -> {
            Intent intent = new Intent(FavoriteMemories.this, Home.class);
            startActivity(intent);
        });
    }
}