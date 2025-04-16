package com.example.itubeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlaylistViewHolder> {

    private Context context;
    private List<String> videoUrls;

    public PlayListAdapter(Context context, List<String> videoUrls) {
        this.context = context;
        this.videoUrls = videoUrls;
    }

    @Override
    public PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_item, parent, false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaylistViewHolder holder, int position) {
        holder.tvVideoUrl.setText(videoUrls.get(position));
        holder.btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlayVideoActivity.class);
            intent.putExtra("videoUrl", videoUrls.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videoUrls.size();
    }

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder {

        TextView tvVideoUrl;
        Button btnPlay;

        public PlaylistViewHolder(View itemView) {
            super(itemView);
            tvVideoUrl = itemView.findViewById(R.id.tvVideoUrl);
            btnPlay = itemView.findViewById(R.id.btnPlay);
        }
    }
}


