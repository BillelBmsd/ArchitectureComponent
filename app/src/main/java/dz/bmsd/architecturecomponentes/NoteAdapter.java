package dz.bmsd.architecturecomponentes;

import android.support.annotation.NonNull;
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
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item, viewGroup, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {
        Note currentNote = notes.get(i);
        noteHolder.textPriority.setText(String.valueOf(currentNote.getPriority()));
        noteHolder.textTitle.setText(currentNote.getTitle());
        noteHolder.textDescription.setText(currentNote.getDescription());
    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
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
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                        listener.onItemClick(notes.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public Note getNote(int position){
        return notes.get(position);
    }

    public interface OnItemClickListener{
        void onItemClick(Note note);
    }

    public void setOnItemClickListner(OnItemClickListener listener){
        this.listener = listener;
    }
}
