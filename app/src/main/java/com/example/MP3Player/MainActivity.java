package com.example.MP3Player;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_AUDIO_REQUEST_CODE = 1001;
    private static final int PICK_AUDIO_DIRECTORY_REQUEST_CODE = 1002;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private Button selectButton;
    private ImageButton playButton, replayButton, nextButton, previousButton;

    private List<Uri> listAudioFiles = new ArrayList<>();
    private int currentSongIndex = 0;

    private ImageView imageViewAlbumArt;
    private TextView textViewSongName, textViewArtistName, textViewCurrentTime, textViewTotalTime;
    private SeekBar seekBarProgress;
    private Handler handler;

    boolean isReplayClicked = false; // Флаг, указывающий, была ли нажата кнопка "реплей"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUIElements();

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAudioFiles.clear();
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                startActivityForResult(intent, PICK_AUDIO_DIRECTORY_REQUEST_CODE);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        playButton.setImageResource(R.drawable.button_start);
                    } else {
                        mediaPlayer.start();
                        playButton.setImageResource(R.drawable.button_pause);
                    }
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSongIndex++;
                if (currentSongIndex < listAudioFiles.size()) {
                    displaySongInfo(listAudioFiles.get(currentSongIndex));
                    playSong(listAudioFiles.get(currentSongIndex));
                } else {
                    currentSongIndex = 0;
                    displaySongInfo(listAudioFiles.get(currentSongIndex));
                    playSong(listAudioFiles.get(currentSongIndex));
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSongIndex > 0) {
                    try {
                        if (mediaPlayer != null) {
                            if (mediaPlayer.getCurrentPosition() < 5000) {
                                currentSongIndex--;
                                playSong(listAudioFiles.get(currentSongIndex));
                            } else {
                                mediaPlayer.seekTo(0);
                                mediaPlayer.start();
                                playButton.setImageResource(R.drawable.button_pause);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isReplayClicked = !isReplayClicked;
                replayButton.setImageResource(isReplayClicked ? R.drawable.button_replay2 : R.drawable.button_replay);
            }
        });

        handler = new Handler();

        // Установка обработчика изменения положения полосы прогресса
        seekBarProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Установка обновления времени и полосы прогресса каждую секунду
        Runnable updateSeekBar = new Runnable() {
            @Override public void run() {
                if (mediaPlayer != null) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    seekBarProgress.setProgress(currentPosition);
                    textViewCurrentTime.setText(millisecondsToTime(currentPosition));
                }
                handler.postDelayed(this, 1000); // Обновляем каждую секунду
            }
        };

        handler.postDelayed(updateSeekBar, 0);
    }


    private void initializeUIElements() {
        selectButton = findViewById(R.id.button_select_music);
        playButton = findViewById(R.id.button_play_music);
        replayButton = findViewById(R.id.button_replay_music);
        nextButton = findViewById(R.id.button_next_music);
        previousButton = findViewById(R.id.button_previous_music);

        imageViewAlbumArt = findViewById(R.id.imageView_album_art);

        textViewSongName = findViewById(R.id.textView_song_name);
        textViewArtistName = findViewById(R.id.textView_artist_name);
        textViewCurrentTime = findViewById(R.id.textView_current_time);
        textViewTotalTime = findViewById(R.id.textView_total_time);

        seekBarProgress = findViewById(R.id.seekBar_progress);

        textViewSongName.setVisibility(View.GONE);
        textViewArtistName.setVisibility(View.GONE);
        textViewCurrentTime.setVisibility(View.GONE);
        textViewTotalTime.setVisibility(View.GONE);
        seekBarProgress.setVisibility(View.GONE);

        playButton.setVisibility(View.GONE);
        replayButton.setVisibility(View.GONE);
        previousButton.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
    }
    private void setVisibleUIElements() {
        textViewSongName.setVisibility(View.VISIBLE);
        textViewArtistName.setVisibility(View.VISIBLE);
        textViewCurrentTime.setVisibility(View.VISIBLE);
        textViewTotalTime.setVisibility(View.VISIBLE);
        seekBarProgress.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.VISIBLE);
        replayButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
        previousButton.setVisibility(View.VISIBLE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_AUDIO_DIRECTORY_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri treeUri = data.getData();
                DocumentFile documentFile = DocumentFile.fromTreeUri(this, treeUri);
                if (documentFile != null && documentFile.isDirectory()) {
                    for (DocumentFile file : documentFile.listFiles()) {
                        if (file.isFile()) {
                            listAudioFiles.add(file.getUri());
                        }
                    }
                }

                if (!listAudioFiles.isEmpty()) {
                    currentSongIndex = 0;
                    try {
                        setVisibleUIElements();
                        displaySongInfo(listAudioFiles.get(currentSongIndex));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    private void playSong(Uri audioUri) {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
            
            mediaPlayer.setDataSource(getApplicationContext(), audioUri);
            mediaPlayer.prepare();

            // Устанавливаем максимальное значение прогресса для SeekBar
            textViewTotalTime.setText(millisecondsToTime(mediaPlayer.getDuration()));
            seekBarProgress.setMax(mediaPlayer.getDuration());

            // Устанавливаем слушатель завершения воспроизведения
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (isReplayClicked) {
                        mediaPlayer.seekTo(0);
                        mediaPlayer.start();
                    } else {
                        mediaPlayer.pause();
                        playButton.setImageResource(R.drawable.button_start);
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            // Обработка ошибок при подготовке и воспроизведении песни
            imageViewAlbumArt.setImageResource(R.drawable.art);
        }
    }


    private void displaySongInfo(Uri audioUri) {
        try {
            // Получаем обложку альбома и устанавливаем её на ImageView:
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(getApplicationContext(), audioUri);

            // Получение автора и название песни:
            String songName = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            if (songName != null) textViewSongName.setText(songName);
            else textViewSongName.setText("Unknown Title");


            String artistName = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            if (artistName != null) textViewArtistName.setText(artistName);
            else textViewArtistName.setText("Unknown Artist");


            byte[] albumArt = retriever.getEmbeddedPicture();
            if (albumArt != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(albumArt, 0, albumArt.length);
                imageViewAlbumArt.setImageBitmap(bitmap);
            } else {
                imageViewAlbumArt.setImageResource(R.drawable.art);
            }

            retriever.release(); // Освобождение:
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String millisecondsToTime(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
    }
}