package com.example.fragmentsdesign.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdesign.api.model.Courses;
import com.example.fragmentsdesign.databinding.ViewHolderAllcoursesBinding;
import com.squareup.picasso.Picasso;

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
    }

    //create view holder
    public static class AllCoursesViewHolder extends RecyclerView.ViewHolder {

        //create obj binding
        private final ViewHolderAllcoursesBinding itemAllCoursesBinding;
        public AllCoursesViewHolder(ViewHolderAllcoursesBinding itemAllCoursesBingin){
            super(itemAllCoursesBingin.getRoot());
            this.itemAllCoursesBinding = itemAllCoursesBingin;
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
