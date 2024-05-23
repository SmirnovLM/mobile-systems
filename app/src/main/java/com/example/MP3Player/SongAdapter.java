package com.example.MP3Player;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private List<Uri> songList;
    private OnItemClickListener onItemClickListener;
    private int selectedPosition = -1;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public SongAdapter(List<Uri> songList, OnItemClickListener onItemClickListener) {
        this.songList = songList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new SongViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Uri songUri = songList.get(position);
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(holder.itemView.getContext(), songUri);

        String songName = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String artistName = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);

        holder.textViewSongName.setText(songName != null ? songName : "Unknown Title");
        holder.textViewArtistName.setText(artistName != null ? artistName : "Unknown Artist");

        byte[] albumArt = retriever.getEmbeddedPicture();
        if (albumArt != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(albumArt, 0, albumArt.length);
            holder.imageViewAlbumArt.setImageBitmap(bitmap);
        } else {
            holder.imageViewAlbumArt.setImageResource(R.drawable.art);
        }

        try {
            retriever.release();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Выделение текущей песни
        Context context = holder.itemView.getContext();
        if (position == selectedPosition) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousSelected = selectedPosition;
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(previousSelected);
                notifyItemChanged(selectedPosition);
                onItemClickListener.onItemClick(selectedPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged(); // Обновляем адаптер
    }

    static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSongName;
        TextView textViewArtistName;
        ImageView imageViewAlbumArt;

        public SongViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            textViewSongName = itemView.findViewById(R.id.text_song_name);
            textViewArtistName = itemView.findViewById(R.id.text_artist_name);
            imageViewAlbumArt = itemView.findViewById(R.id.image_album);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
