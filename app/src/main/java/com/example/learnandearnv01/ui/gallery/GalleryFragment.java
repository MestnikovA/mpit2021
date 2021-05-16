package com.example.learnandearnv01.ui.gallery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.learnandearnv01.R;
import com.example.learnandearnv01.databinding.FragmentGalleryBinding;
import com.example.learnandearnv01.models.Courses;
import com.example.learnandearnv01.OpenedCourse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference courses;
    String coursesid;
    String currentCourseId;
    String courseDescription;
    String courseAuthor;
    String courseName;
    String userCurrentCourses;

    private LinearLayout coursesView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        courses = db.getReference("Courses");
        courseDescription = new String("");
        courseAuthor = new String("");
        courseName = new String("");
        userCurrentCourses = new String("");

        Button btnJs = (Button) root.findViewById(R.id.btnJsCourse);
        Button btnMyCourses = (Button) root.findViewById(R.id.btnMyCourses);
        Button btnAllCourses = (Button) root.findViewById(R.id.btnAllCourses);

        DatabaseReference userCourses = FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("courses");

        ArrayList<String> themes = new ArrayList<>();
        DatabaseReference dbCourses = FirebaseDatabase.getInstance().getReference("Courses");
        DatabaseReference courseId = dbCourses.child("course01");
        DatabaseReference author = courseId.child("author");
        DatabaseReference description = courseId.child("description");
        DatabaseReference coursename = courseId.child("coursename");

        userCourses.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                userCurrentCourses = String.valueOf(task.getResult().getValue());
            }
        });


        coursename.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Log.d("ИМЯ КУРСА", String.valueOf(task.getResult().getValue()));
                courseName = String.valueOf(task.getResult().getValue());
                btnJs.setText(courseName);
            }
        });
        description.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Log.d("ИМЯ КУРСА", String.valueOf(task.getResult().getValue()));
                courseDescription = String.valueOf(task.getResult().getValue());
            }
        });
        author.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Log.d("ИМЯ КУРСА", String.valueOf(task.getResult().getValue()));
                courseAuthor = String.valueOf(task.getResult().getValue());
            }
        });




        btnJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OpenedCourse.class);
                intent.putExtra("COURSE_NAME",courseName);
                intent.putExtra("COURSE_AUTHOR",courseAuthor);
                intent.putExtra("COURSE_DESCRIPTION",courseDescription);
                startActivity(intent);
            }
        });
        btnAllCourses.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                btnJs.setVisibility(View.VISIBLE);

            }
        });
        btnMyCourses.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(btnJs.getText().equals(userCurrentCourses)){
                    btnJs.setVisibility(View.VISIBLE);
                }
                else{
                    btnJs.setVisibility(View.INVISIBLE);
                }

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