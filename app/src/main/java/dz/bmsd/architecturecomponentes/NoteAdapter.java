package dz.bmsd.architecturecomponentes;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dz.bmsd.architecturecomponentes.room.Note;

/**
 * Created by Bilo on 23,May,2019
 */
public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

    private OnItemClickListener listener;

    protected NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldNote, @NonNull Note newNote) {
            return oldNote.getId() == newNote.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldNote, @NonNull Note newNote) {
            return oldNote.getTitle().equals(newNote.getTitle()) &&
                    oldNote.getDescription().equals(newNote.getDescription()) &&
                    oldNote.getPriority() == newNote.getPriority();
        }
    };


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item, viewGroup, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {
        Note currentNote = getItem(i);
        noteHolder.textPriority.setText(String.valueOf(currentNote.getPriority()));
        noteHolder.textTitle.setText(currentNote.getTitle());
        noteHolder.textDescription.setText(currentNote.getDescription());
    }




    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private TextView textDescription;
        private TextView textPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            this.textTitle = itemView.findViewById(R.id.text_title);
            this.textDescription = itemView.findViewById(R.id.text_description);
            this.textPriority = itemView.findViewById(R.id.text_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public Note getNote(int position) {
        return getItem(position);
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
