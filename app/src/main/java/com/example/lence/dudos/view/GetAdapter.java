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

public class GetAdapter extends RecyclerView.Adapter<GetAdapter.Holder> {

    private List<String> mGetList = new ArrayList<>();

    public GetAdapter(List<String> mGetList) {
        this.mGetList = mGetList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.get_item, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.mName.setText((position+1)+": "+mGetList.get(position));

    }

    @Override
    public int getItemCount() {
        return mGetList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView mName;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
