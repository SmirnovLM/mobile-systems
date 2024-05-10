package com.example.lab6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab6.NoteDataBase.DBHelper;

import java.util.ArrayList;
import java.util.Objects;

public class ActivityNoteKeeper extends AppCompatActivity {

    private EditText editTextNote;
    private Button buttonAddNote;
    private ListView listViewNotes;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> notesList;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_keeper);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        editTextNote = findViewById(R.id.editTextNote);
        buttonAddNote = findViewById(R.id.buttonAddNote);
        listViewNotes = findViewById(R.id.listViewNotes);

        dbHelper = new DBHelper(this);

        loadNotes();

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteNote(position);
            }
        });
    }

    private void loadNotes() {
        notesList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                int columnIndex = cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NOTE_TEXT);
                String noteText = cursor.getString(columnIndex);
                notesList.add(noteText);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        listViewNotes.setAdapter(adapter);
    }

    private void addNote() {
        String noteText = editTextNote.getText().toString().trim();
        if (!noteText.isEmpty()) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_NOTE_TEXT, noteText);
            long newRowId = db.insert(DBHelper.TABLE_NAME, null, values);
            if (newRowId != -1) {
                editTextNote.setText("");
                notesList.add(noteText);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Error adding note", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a note", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteNote(int position) {
        String noteText = notesList.get(position);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = db.delete(DBHelper.TABLE_NAME,
                DBHelper.COLUMN_NOTE_TEXT + " = ?",
                new String[]{noteText});
        if (deletedRows > 0) {
            notesList.remove(position);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Error deleting note", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Обрабатываем нажатие на кнопку "Назад"
        if (item.getItemId() == android.R.id.home) {
            // Завершаем текущую активность
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}