package com.example.lab9;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ActivityMP3Player extends AppCompatActivity {
    private static final int PICK_AUDIO_REQUEST_CODE = 1001;
    private MediaPlayer mediaPlayer;
    private Button selectButton, restartButton, playButton;
    private Uri selectedAudioUri;
    private ImageView imageViewAlbumArt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3_player);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        selectButton = findViewById(R.id.button_select_music);
        playButton = findViewById(R.id.button_play_music);
        restartButton = findViewById(R.id.button_restart_music);
        imageViewAlbumArt = findViewById(R.id.imageView_album_art);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAudio();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        playButton.setText("Play");
                    } else {
                        mediaPlayer.start();
                        playButton.setText("Pause");
                    }
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    mediaPlayer.seekTo(0); // Перемотка песни к началу
                    mediaPlayer.start(); // Начало воспроизведения сначала
                    playButton.setText("Pause");
                }
            }
        });
    }

    private void selectAudio() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        startActivityForResult(intent, PICK_AUDIO_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_AUDIO_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                selectedAudioUri = data.getData();
                try {
                    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                    retriever.setDataSource(getApplicationContext(), selectedAudioUri);
                    byte[] albumArt = retriever.getEmbeddedPicture();
                    if (albumArt != null) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(albumArt, 0, albumArt.length);
                        imageViewAlbumArt.setImageBitmap(bitmap);
                    } else {
                        imageViewAlbumArt.setImageResource(R.drawable.art);
                    }
                    if (mediaPlayer != null) {
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(getApplicationContext(), selectedAudioUri);
                    mediaPlayer.prepare();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    imageViewAlbumArt.setImageResource(R.drawable.art);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
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