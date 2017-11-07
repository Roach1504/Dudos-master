package com.example.lence.dudos.view;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lence.dudos.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {


    private List<String> mPostList = new ArrayList<>();


    public void addPost(String post){
        mPostList.add(post);

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);

        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        position++;
        holder.mTitle.setText(position+": "+mPostList.get(position-1));
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView mTitle;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
