package com.example.fragmentsdesign.api.service;

import com.example.fragmentsdesign.api.model.Courses;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/course.json")
    Call<List<Courses>> loadCoursesList();
}
