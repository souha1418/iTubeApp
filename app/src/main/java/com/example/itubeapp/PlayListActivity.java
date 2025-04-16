package com.example.itubeapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class PlayListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PlayListAdapter playlistAdapter;
    DBHelper dbHelper;
    String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        recyclerView = findViewById(R.id.recyclerViewPlaylist);
        dbHelper = new DBHelper(this);
        currentUsername = getIntent().getStringExtra("username");

        List<String> videoUrls = dbHelper.getPlaylist(currentUsername);
        if (videoUrls.isEmpty()) {
            Toast.makeText(this, "No videos in your playlist.", Toast.LENGTH_SHORT).show();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        playlistAdapter = new PlayListAdapter(this, videoUrls);
        recyclerView.setAdapter(playlistAdapter);
    }
}

