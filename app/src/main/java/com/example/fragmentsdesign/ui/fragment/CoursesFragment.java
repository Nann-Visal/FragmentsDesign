package com.example.fragmentsdesign.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdesign.api.model.Courses;
import com.example.fragmentsdesign.api.service.ApiService;
import com.example.fragmentsdesign.databinding.FragmentCoursesBinding;
import com.example.fragmentsdesign.ui.adapter.AllCoursesAdapter;
import com.example.fragmentsdesign.ui.adapter.PPCoursesAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoursesFragment extends Fragment {
    private FragmentCoursesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCoursesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    //load list courses from server
        loadCoursesListFromServer();
    }

    //load data
    private void loadCoursesListFromServer(){

        //create http clients
        Retrofit httpClients = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create service obj
        ApiService apiService = httpClients.create(ApiService.class);

        //load data
        Call<List<Courses>> task = apiService.loadCoursesList();
        task.enqueue(new Callback<List<Courses>>() {
            @Override
            public void onResponse(Call<List<Courses>> call, Response<List<Courses>> response) {
                //if success
                if (response.isSuccessful()){
                    showCoursesList(response.body());
                }else{
                    Toast.makeText(getContext(),"Data Loading . . .",Toast.LENGTH_LONG).show();
                }
            }

            @SuppressLint("ShowToast")
            @Override
            public void onFailure(Call<List<Courses>> call, Throwable t) {
                //if un-success
                Toast.makeText(getContext(),"Data Failed!!",Toast.LENGTH_LONG).show();
            }
        });
    }

//    show data desc" that meaning we get data from httpclient and then give its to adapter for implement and then adapter return
//    ui+data follow layout-design" <<Normal to understand, it is RecyclerView Implementation>>
    private void showCoursesList(List<Courses> coursesList){

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(RecyclerView.VERTICAL);

        PPCoursesAdapter ppCoursesAdapter = new PPCoursesAdapter();
        AllCoursesAdapter allCoursesAdapter = new AllCoursesAdapter();

        //create layout manager for recycler view
        binding.recyclerViewPPCourses.setLayoutManager(linearLayoutManager1);
        binding.recycleViewAllCourses.setLayoutManager(linearLayoutManager2);


        //create adapter ppcourses
        ppCoursesAdapter.submitList(coursesList);
        binding.recyclerViewPPCourses.setAdapter(ppCoursesAdapter);
        allCoursesAdapter.submitList(coursesList);
        binding.recycleViewAllCourses.setAdapter(allCoursesAdapter);
    }
}
