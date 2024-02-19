package ru.example.simplemosdiaryclient.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.example.simplemosdiaryclient.database.database_entity.Lesson;
import ru.example.simplemosdiaryclient.databinding.ItemLessonBinding;

import java.util.List;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.MyViewHolder> {
    private final List<Lesson> lessonList;

    public PageAdapter(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemLessonBinding binding = ItemLessonBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(lessonList.get(position));
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemLessonBinding binding;

        MyViewHolder(@NonNull ItemLessonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Lesson lesson) {
            binding.setLessonSubject(lesson.getSubject());

            binding.setLessonInfo(
                    lesson.getTimeStartString() + " - " +
                            lesson.getTimeEndString() + "  |  " +
                            lesson.getTeacherLastName() + " " +
                            lesson.getTeacherFirstName().substring(0, 1) + "." +
                            lesson.getTeacherMiddleName().substring(0, 1) + ".");

            binding.setLessonInfo2(
                    lesson.getCabinetNum() + "  |  " +
                            lesson.getGroupName().substring(0, lesson.getGroupName().length() - lesson.getSubject().length()));

            binding.setLessonNum(lesson.getLessonNum());

            binding.setAbsenceReasonId(lesson.getAbsenceReasonId() == null ? null : lesson.getAbsenceReasonId().intValue());

            binding.setMark(lesson.getMarkValue() == null ? null : String.valueOf(lesson.getMarkValue().intValue()));

            binding.executePendingBindings();
        }
    }
}
