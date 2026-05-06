package com.umbb.mobguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Main activity showing the university presentation and entry point.
 */
public class MainActivity ext ends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sets the layout for the university presentation screen.
        setContentView(R.layout.activity_main);

        Button btnExplore = findViewById(R.id.btn_explore);
        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigates to the faculty list activity when clicked.
                startActivity(new Intent(MainActivity.this, FacultyListActivity.class));
            }
        });
    }
}
