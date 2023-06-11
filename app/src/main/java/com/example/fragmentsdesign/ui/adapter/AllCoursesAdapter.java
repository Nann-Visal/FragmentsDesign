package com.example.fragmentsdesign.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.fragmentsdesign.api.model.Courses;
import com.example.fragmentsdesign.databinding.ViewHolderAllcoursesBinding;
import com.example.fragmentsdesign.ui.activity.DetailCourses;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllCoursesAdapter extends ListAdapter<Courses, AllCoursesAdapter.AllCoursesViewHolder> {

    public AllCoursesAdapter() {
        super(new DiffUtil.ItemCallback<Courses>() {
            @Override
            public boolean areItemsTheSame(@NonNull Courses oldItem, @NonNull Courses newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Courses oldItem, @NonNull Courses newItem) {
                return oldItem.getId() == newItem.getId();
            }
        });

    }


    @NonNull
    @Override
    public AllCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //generate view item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderAllcoursesBinding binding = ViewHolderAllcoursesBinding.inflate(layoutInflater,parent,false);
        return new AllCoursesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCoursesViewHolder holder, int position) {

        //bind data into each item

        Courses item = getItem(position);
        holder.bind(item);

        //set event-listener on part of item <<btn-Detail>>
        holder.itemAllCoursesBinding.btnAllCDesc.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailCourses.class);

            //it mean intent-obj get data from each item for send to view-layout in DetailActivity
            intent.putExtra("titleCourses",item.getTitle());
            intent.putExtra("descCourses",item.getDescription());
            intent.putExtra("imgCourses",item.getImageUrl());

            //start DetailActivity
            v.getContext().startActivity(intent);
        });
    }

    //create view holder
    public static class AllCoursesViewHolder extends RecyclerView.ViewHolder {

        //create obj binding
        private final ViewHolderAllcoursesBinding itemAllCoursesBinding;
        public AllCoursesViewHolder(ViewHolderAllcoursesBinding itemAllCoursesBinding){
            super(itemAllCoursesBinding.getRoot());
            this.itemAllCoursesBinding = itemAllCoursesBinding;
        }

        //bind data
        public void bind(Courses courses){

            //bind data into view_holder_allcourses
            itemAllCoursesBinding.txtAllCoursesTitle.setText(courses.getCode());
            itemAllCoursesBinding.txtAllCoursesDesc.setText(courses.getTitle());
            Picasso.get().load(courses.getImageUrl()).into(itemAllCoursesBinding.imgAllCourses);
        }
    }


}
