package com.example.project1;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FavoriteMusic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorite_music);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Spinner with available songs
        Spinner spinner = findViewById(R.id.spinner_songs);
        String[] songs = {"Select Song", "Song 1: Me Va Me Va", "Song 2: ¿Y Cómo Es Él?", "Song 3: Viva La Vida"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, songs);
        spinner.setAdapter(adapter);

        // Spinner for select item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    return;
                }
                String url = "";
                if (position == 1) {
                    url = "https://www.youtube.com/watch?v=z-I6JNO6hvY&list=RDz-I6JNO6hvY";
                } else if (position == 2) {
                    url = "https://www.youtube.com/watch?v=f9sGyvLfQk4&list=RDz-I6JNO6hvY";
                } else if (position == 3) {
                    url = "https://www.youtube.com/watch?v=dvgZkm1xWPE&list=RDdvgZkm1xWPE";
                }
                Log.d("Music log", "====Song selected: " + songs[position] + "====");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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
                Log.d("FavoriteMusic log", "====Home icon clicked - returning to Home====");
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

    }
}
