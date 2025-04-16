package com.example.itubeapp;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class PlayVideoActivity extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        String videoUrl = getIntent().getStringExtra("videoUrl");
        String videoId = getYouTubeVideoId(videoUrl);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
            //    String videoId = "aqz-KE-bpKQ";
                Log.d("VideoDebug", "Extracted Video ID: " + videoId);
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }

   /* private String getYouTubeVideoId(String url) {
        String videoId = "";
        if (url.contains("youtu.be")) {
            videoId = url.substring(url.lastIndexOf("/") + 1);
        } else if (url.contains("v=")) {
            String[] parts = url.split("v=");
            if (parts.length > 1) {
                videoId = parts[1].split("&")[0];
            }
        }
        return videoId;
    }*/
   private String getYouTubeVideoId(String url) {
       String videoId = "";

       if (url.contains("youtu.be")) {
           videoId = url.substring(url.lastIndexOf("/") + 1);
           if (videoId.contains("?")) {
               videoId = videoId.substring(0, videoId.indexOf("?"));
           }
       } else if (url.contains("v=")) {
           String[] parts = url.split("v=");
           if (parts.length > 1) {
               videoId = parts[1].split("&")[0];
           }
       }

       return videoId;
   }
}