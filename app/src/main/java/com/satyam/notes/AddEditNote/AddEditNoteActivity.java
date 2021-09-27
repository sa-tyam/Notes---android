package com.satyam.notes.AddEditNote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.satyam.notes.R;
import com.satyam.notes.databinding.ActivityAddEditNoteBinding;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.satyam.notes.AddEditNote.EXTRA_TITLE";
    public static final String EXTRA_DESC = "com.satyam.notes.AddEditNote.EXTRA_DESC";
    public static final String EXTRA_PRIORITY = "com.satyam.notes.AddEditNote.EXTRA_PRIORITY";
    public static final String EXTRA_ID = "com.satyam.notes.AddEditNote.EXTRA_ID";

    private ActivityAddEditNoteBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews () {

        binding.priorityNumberPicker.setMinValue(1);
        binding.priorityNumberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear);
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            binding.titleEditText.setText(intent.getStringExtra(EXTRA_TITLE));
            binding.descriptionEditText.setText(intent.getStringExtra(EXTRA_DESC));
            binding.priorityNumberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_edit_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_edit_save:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote () {
        String title = binding.titleEditText.getText().toString();
        String description = binding.descriptionEditText.getText().toString();
        int priority = binding.priorityNumberPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Enter title and Description",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DESC, description);
        intent.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (id != -1) {
            intent.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, intent);

        finish();
    }
}