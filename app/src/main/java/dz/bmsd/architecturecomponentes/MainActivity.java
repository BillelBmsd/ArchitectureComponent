package dz.bmsd.architecturecomponentes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import dz.bmsd.architecturecomponentes.room.Note;
import dz.bmsd.architecturecomponentes.view_model.NoteViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                //update recycleView
                Log.d(TAG, "onChanged: ");
            }
        });

    }
}
