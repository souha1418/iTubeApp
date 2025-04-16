package com.example.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    EditText etYoutubeUrl;
    Button btnPlay, btnAddToPlaylist, btnMyPlaylist;

    DBHelper dbHelper;
    String currentUsername;  // we'll pass it from LoginActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        etYoutubeUrl = findViewById(R.id.etYoutubeUrl);
        btnPlay = findViewById(R.id.btnPlay);
        btnAddToPlaylist = findViewById(R.id.btnAddToPlaylist);
        btnMyPlaylist = findViewById(R.id.btnMyPlaylist);

        dbHelper = new DBHelper(this);

        // Get the current logged in username
        currentUsername = getIntent().getStringExtra("username");

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = etYoutubeUrl.getText().toString();
                if (url.isEmpty()) {
                    Toast.makeText(DashboardActivity.this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DashboardActivity.this, PlayVideoActivity.class);
                    intent.putExtra("videoUrl", url);
                    startActivity(intent);
                }
            }
        });

        btnAddToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = etYoutubeUrl.getText().toString();
                if (url.isEmpty()) {
                    Toast.makeText(DashboardActivity.this, "Enter URL first", Toast.LENGTH_SHORT).show();
                } else {
                    boolean saved = dbHelper.insertPlaylist(currentUsername, url);
                    if (saved) {
                        Toast.makeText(DashboardActivity.this, "Added to playlist", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DashboardActivity.this, "Failed to add", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnMyPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, PlayListActivity.class);
                intent.putExtra("username", currentUsername);
                startActivity(intent);
            }
        });
    }
}
