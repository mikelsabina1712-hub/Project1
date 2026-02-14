package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BucketList extends AppCompatActivity {

    // Declaring view objects.
    EditText etGoal;
    CheckBox cbItem1;
    CheckBox cbItem2;
    int itemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bucket_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etGoal = findViewById(R.id.et_goal);
        cbItem1 = findViewById(R.id.cb_item1);
        cbItem2 = findViewById(R.id.cb_item2);

        Button btnSave = findViewById(R.id.btn_save);
        // Setting an onClickListener on the save button.
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String goal = etGoal.getText().toString().trim();
                if (goal.equals("")) {
                    Log.d("Bucket log", "====Goal empty====");
                    return;
                }

                itemCount++;
                if (itemCount == 1) {
                    cbItem1.setText(goal);
                    cbItem1.setVisibility(View.VISIBLE);
                    BucketListData.item1 = goal;
                    BucketListData.item1Visible = true;
                    Log.d("Bucket log", "====Item 1 added: " + goal + "====");
                } else if (itemCount == 2) {
                    cbItem2.setText(goal);
                    cbItem2.setVisibility(View.VISIBLE);
                    BucketListData.item2 = goal;
                    BucketListData.item2Visible = true;
                    Log.d("Bucket log", "====Item 2 added: " + goal + "====");
                } else {
                    // Replace second for additional items.
                    cbItem2.setText(goal);
                    BucketListData.item2 = goal;
                    Log.d("Bucket log", "====Replaced item 2: " + goal + "====");
                }
                etGoal.setText("");
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
                Log.d("BucketList log", "====Home icon clicked - returning to Home====");
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                // Optional: finish();   // close current screen
            }
        });
    }
    // Bonus: Restore from globals on resume (Activity Lifecycle from Lecture 5).
    @Override
    protected void onResume() {
        super.onResume();
        if (BucketListData.item1Visible) {
            cbItem1.setText(BucketListData.item1);
            cbItem1.setVisibility(View.VISIBLE);
            Log.d("Bucket log", "====Restored item 1====");
        }
        if (BucketListData.item2Visible) {
            cbItem2.setText(BucketListData.item2);
            cbItem2.setVisibility(View.VISIBLE);
            Log.d("Bucket log", "====Restored item 2====");
        }
    }

    // Bonus: Save to globals on pause (already done in save, but log for debug).
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Bucket log", "====Paused, globals saved====");
    }
}