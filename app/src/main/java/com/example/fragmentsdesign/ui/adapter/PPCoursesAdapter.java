package com.example.fragmentsdesign.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdesign.api.model.Courses;
import com.example.fragmentsdesign.databinding.ViewHolderPpcoursesBinding;
import com.squareup.picasso.Picasso;

// adapter class we used  it to combine data and items of view(ui)
// in adapter it must be has view-holder<<view from recyclerview>> and dataset<<data from recyclerview>>
public class PPCoursesAdapter extends ListAdapter<Courses, PPCoursesAdapter.PPCoursesViewHolder> {

    public PPCoursesAdapter() {
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
    public PPCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //generate view item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderPpcoursesBinding binding = ViewHolderPpcoursesBinding.inflate(layoutInflater,parent,false);
        return new PPCoursesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PPCoursesViewHolder holder, int position) {

        //bind data into each item
        Courses item = getItem(position);
        holder.bind(item);
    }


    //create view holder
    public static class PPCoursesViewHolder extends RecyclerView.ViewHolder{

        //create obj binding of PPCourses and AllCourses
        private final ViewHolderPpcoursesBinding itemPopularCoursesBinding;
        public PPCoursesViewHolder(ViewHolderPpcoursesBinding itemPopularCoursesBinding) {
            super(itemPopularCoursesBinding.getRoot());
            this.itemPopularCoursesBinding = itemPopularCoursesBinding;
        }

        //bind data
        public void bind(Courses courses){

            //bind data into view_holder_ppcourses
            itemPopularCoursesBinding.txtPPCourses.setText(courses.getCode());
            Picasso.get().load(courses.getImageUrl()).into(itemPopularCoursesBinding.imgPPCourses);

        }
    }
}
