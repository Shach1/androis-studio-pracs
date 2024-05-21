package com.example.database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder>
{
    private final List<Lesson> lessons;

    public LessonAdapter(List<Lesson> lessons) {
        this.lessons = lessons;
    }
    @NonNull
    @Override
    public LessonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.bind(lessons.get(position));
    }

    @Override
    public int getItemCount() {
        if (lessons != null) return lessons.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvId;
        private final TextView tvNumberPair;
        private final TextView tvTeacher;
        private final TextView tvSubject;
        private final TextView tvClassroom;
        private final TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.id_column);
            tvNumberPair = itemView.findViewById(R.id.number_pair_column);
            tvTeacher = itemView.findViewById(R.id.teacher_column);
            tvSubject = itemView.findViewById(R.id.subject_column);
            tvClassroom = itemView.findViewById(R.id.classroom_column);
            tvTime = itemView.findViewById(R.id.time_column);
        }
        public void bind(Lesson lesson)
        {
            tvId.setText(String.valueOf(lesson.getId()));
            tvNumberPair.setText(String.valueOf(lesson.getNumberPair()));
            tvTeacher.setText(lesson.getTeacher());
            tvSubject.setText(lesson.getSubject());
            tvClassroom.setText(lesson.getClassroom());
            tvTime.setText(lesson.getTime());
        }
    }
}
