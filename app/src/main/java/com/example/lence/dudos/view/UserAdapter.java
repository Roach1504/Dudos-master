package com.example.lence.dudos.view;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lence.dudos.R;
import com.example.lence.dudos.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder>{

    List<UserModel> list = new ArrayList<>();
    MVPUpDate mvp;

    public UserAdapter(List<UserModel> list, MVPUpDate mvp) {
        this.mvp = mvp;
        this.list = list;
    }

    @Override
    public UserAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(UserAdapter.Holder holder, final int position) {
        holder.info.setText(list.get(position).getId()+": "
                +list.get(position).getName()+" "
                +list.get(position).getAge()+" "
                +list.get(position).getGender()+" "
                +(list.get(position).getMarried().equals("true") ? "married" : "unmarried"));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mvp.delete(list.get(position).getId());
            }
        });
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mvp.upDate(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.info)
        TextView info;
        @BindView(R.id.delete)
        Button delete;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
