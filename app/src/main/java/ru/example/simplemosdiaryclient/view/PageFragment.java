package ru.example.simplemosdiaryclient.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;

import ru.example.simplemosdiaryclient.database.SimpleMosDiaryClientDatabase;
import ru.example.simplemosdiaryclient.database.database_entity.Lesson;
import ru.example.simplemosdiaryclient.databinding.FragmentPageBinding;

import java.util.ArrayList;
import java.util.List;

public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";

    LiveData<List<Lesson>> employeesLiveData;
    LiveData<List<String>> liveData;

    List<Lesson> lessonList = new ArrayList<>();
    PageAdapter pageAdapter;

    Integer pageNumber = -1;

    Context context;
    FragmentPageBinding binding;

    private SimpleMosDiaryClientDatabase db;

    public static PageFragment newInstance(int pageNumber) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPageBinding.inflate(inflater);

        context = requireContext();

        Bundle args = getArguments();
        if (args != null) {
            pageNumber = args.getInt(ARG_PAGE_NUMBER);

            db = SimpleMosDiaryClientDatabase.getInstance(context);

            if (binding.recyclerView.getItemDecorationCount() > 0) {
                binding.recyclerView.removeItemDecorationAt(0);
            }
            binding.recyclerView.removeAllViews();

            float scale = context.getResources().getDisplayMetrics().density;
            int spase = 15;
            int verticalSpaceHeight = (int) (spase * scale + 0.5f);

            binding.recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(verticalSpaceHeight));
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            pageAdapter = new PageAdapter(lessonList);
            binding.recyclerView.setAdapter(pageAdapter);

            liveData = DataController.getInstance().getData();

            liveData.observe(getViewLifecycleOwner(), value -> {
                if (employeesLiveData != null)
                    employeesLiveData.removeObservers(getViewLifecycleOwner());

                employeesLiveData = db.simpleMosDiaryClientDao().getLessonsLiveDataByDate(value.get(pageNumber));

                employeesLiveData.observe(getViewLifecycleOwner(), lessons -> {
                    lessonList.clear();
                    lessonList.addAll(lessons);

                    pageAdapter.notifyDataSetChanged();

                    if (lessonList.size() == 0) {
                        binding.textView.setVisibility(View.VISIBLE);
                    } else {
                        binding.textView.setVisibility(View.GONE);
                    }
                });
            });
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (liveData != null) liveData.removeObservers(getViewLifecycleOwner());
        if (employeesLiveData != null) employeesLiveData.removeObservers(getViewLifecycleOwner());
    }
}