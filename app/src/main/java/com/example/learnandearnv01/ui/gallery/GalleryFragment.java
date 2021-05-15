package com.example.learnandearnv01.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.learnandearnv01.R;
import com.example.learnandearnv01.databinding.FragmentGalleryBinding;
import com.example.learnandearnv01.models.Courses;
import com.example.learnandearnv01.OpenedCourse;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<String> themes = new ArrayList<>();
        themes.add("Введение в JavaScript");
        themes.add("Справочники и спецификации");
        themes.add("Редакторы кода");
        themes.add("Консоль разработчика");
        themes.add("Привет, мир!");
        themes.add("Структура кода");
        themes.add("Строгий режим — use strict");
        themes.add("Переменные");
        themes.add("Типы данных");
        themes.add("Взаимодействие: alert, prompt, confirm");
        themes.add("Преобразование типов");
        themes.add("Базовые операторы, математика");


        Courses jscourse = new Courses();
        jscourse.setAuthor("Petrov Ivan");
        jscourse.setCoursename("Основы программирования JavaScript");
        jscourse.setDescription("Научитесь программировать на JavaScript. Узнайте много нового в программировании.");
        jscourse.setLevel("Easy");
        //jscourse.setThemes(themes);

        Button btnJs = (Button) root.findViewById(R.id.btnJsCourse);

        btnJs.setText(jscourse.getCoursename());
        btnJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OpenedCourse.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}