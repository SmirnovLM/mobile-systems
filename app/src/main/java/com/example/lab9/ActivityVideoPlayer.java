package com.example.lab9;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class ActivityVideoPlayer extends AppCompatActivity {
    private static final int PICK_VIDEO_REQUEST_CODE = 1002;
    private VideoView videoView;
    private Button selectButton, playButton, restartButton;
    private Uri selectedVideoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        videoView = findViewById(R.id.videoView);
        selectButton = findViewById(R.id.button_select_video);
        playButton = findViewById(R.id.button_play_video);
        restartButton = findViewById(R.id.button_restart_video);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectVideo();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartVideo();
            }
        });
    }

    private void selectVideo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        startActivityForResult(intent, PICK_VIDEO_REQUEST_CODE);
    }

    private void playVideo() {
        if (selectedVideoUri != null) {
            videoView.setVideoURI(selectedVideoUri);
            videoView.start();
        }
    }

    private void restartVideo() {
        if (selectedVideoUri != null) {
            videoView.setVideoURI(selectedVideoUri);
            videoView.start();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_VIDEO_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                selectedVideoUri = data.getData();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}