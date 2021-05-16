package com.example.learnandearnv01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OpenedCourse extends AppCompatActivity {


    TextView openedCourseName;
    TextView openedCourseAuthor;
    TextView openedCourseDescription;
    Button btnAddCourse;
    WebView rickroll;

    androidx.constraintlayout.widget.ConstraintLayout openedActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opened_course);
        openedCourseName = findViewById(R.id.openedCourseName);
        openedCourseAuthor = findViewById(R.id.openedCourseAuthor);
        openedCourseDescription = findViewById(R.id.openedCourseDescription);
        btnAddCourse = findViewById(R.id.btnAddCourse);

        openedActivity = findViewById(R.id.openedActivity);


        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference addCourse = FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("courses");
                addCourse.setValue(getIntent().getStringExtra("COURSE_NAME"));
                Snackbar.make(openedActivity, "Курс успешно добавлен.", Snackbar.LENGTH_SHORT).show();
            }
        });



        openedCourseName.setText(getIntent().getStringExtra("COURSE_NAME"));
        openedCourseAuthor.setText("Автор: "+getIntent().getStringExtra("COURSE_AUTHOR"));
        openedCourseDescription.setText(getIntent().getStringExtra("COURSE_DESCRIPTION"));
    }
}