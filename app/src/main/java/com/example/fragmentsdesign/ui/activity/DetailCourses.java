package com.example.fragmentsdesign.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fragmentsdesign.R;
import com.example.fragmentsdesign.databinding.ActivityCoursesDscBinding;
import com.squareup.picasso.Picasso;

public class DetailCourses extends AppCompatActivity {
    TextView titleCourse;
    TextView descCourses;
    ImageView imgCourses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //create binding obj
        com.example.fragmentsdesign.databinding.ActivityCoursesDscBinding binding = ActivityCoursesDscBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);

        //find view in layout
        setContentView(binding.getRoot());
        titleCourse = binding.txtCoursesTitle;
        descCourses = binding.txtCoursesDetail;
        imgCourses  = binding.imgCoursesLogo;

        //bind data into view-layout
        getIntentSignal();
    }


    //bind data into view-layout that we get from intent signal of all-adapter class
    public void getIntentSignal(){
        titleCourse.setText(getIntent().getExtras().getString("titleCourses"));
        descCourses.setText(getIntent().getExtras().getString("descCourses"));
        Picasso.get().load(getIntent().getExtras().getString("imgCourses")).into(imgCourses);
    }

}
