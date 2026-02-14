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

    LinearLayout layoutMemories;
    String[][] memories = {
            {"Graduation Day", "The day I graduated from college was unforgettable.", "memory_graduation"}, // Assume drawable
            {"Family Vacation", "Our trip to the beach was full of fun.", "memory_vacation"},
            {"First Job", "Starting my first job was exciting.", "memory_job"}
    };

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

        layoutMemories = findViewById(R.id.layout_memories);
        EditText etFilter = findViewById(R.id.et_filter);

        Button btnFilter = findViewById(R.id.btn_submit_filter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filter = etFilter.getText().toString().trim().toLowerCase();
                displayMemories(filter);
                Log.d("Memories log", "====Filter applied: " + filter + "====");
            }
        });

        displayMemories(""); // Initial display all

        // Set username in the header
        TextView tvHeader = findViewById(R.id.tv_username_header);
        tvHeader.setText("Welcome, " + User.name);

        // Home icon click listener
        ImageView ivHome = findViewById(R.id.iv_home_icon);
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FavoriteMemories log", "====Home icon clicked - returning to Home====");
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                // Optional: finish();  // close current activity
            }
        });
    }

    /**
     * Displays memories based on filter (bonus: not case-sensitive).
     * Clears existing views and adds matching ones.
     */
    private void displayMemories(String filter) {
        // Clear existing memories (keep title, filter, home button).
        layoutMemories.removeViews(3, layoutMemories.getChildCount() - 4);

        for (String[] memory : memories) {
            if (filter.equals("") || memory[0].toLowerCase().contains(filter)) {
                TextView tvTitle = new TextView(this);
                tvTitle.setText(memory[0]);
                tvTitle.setTextSize(18f);

                TextView tvDesc = new TextView(this);
                tvDesc.setText(memory[1]);

                ImageView iv = new ImageView(this);
                int resId = getResources().getIdentifier(memory[2], "drawable", getPackageName());
                iv.setImageResource(resId);
                iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));

                layoutMemories.addView(tvTitle);
                layoutMemories.addView(tvDesc);
                layoutMemories.addView(iv);

                Log.d("Memories log", "====Added memory: " + memory[0] + "====");
            }
        }
    }
}