package com.example.lence.dudos.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lence.dudos.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MVP {


    RecyclerView mRecyclerView;
    @BindView(R.id.post_button)
    Button mPostButton;
    @BindView(R.id.get_button)
    Button mGetButton;
    @BindView(R.id.insert_button)
    Button mInsertButton;

    ProgressDialog dialog;

    GetAdapter mGetAdapter;
    PostAdapter mPostAdapter;
    Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new Presenter(this);
        dialog = new ProgressDialog(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @OnClick(R.id.post_button)
    public void onMPostButtonClicked() {
        mPresenter.loadPost(1);
        mPostAdapter = new PostAdapter();
        dialog.setTitle("Post");
        dialog.show();
    }

    @OnClick(R.id.get_button)
    public void onMGetButtonClicked() {
        dialog.setTitle("Get");
        dialog.show();
        mPresenter.loadGet();
    }

    @OnClick(R.id.insert_button)
    public void onMInsertButtonClicked() {
        startActivity(new Intent(this, Main2Activity.class));
    }

    @Override
    public void showGet(List<String> getList) {
        mGetAdapter = new GetAdapter(getList);
        mRecyclerView.setAdapter(mGetAdapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        dialog.dismiss();
    }


    @Override
    public void showIsEmpty() {

    }

    int k = 2;

    @Override
    public void showPost(String title) {
        mPostAdapter.addPost(title);
        mRecyclerView.setAdapter(mPostAdapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        if (k <= 10) {
            mPresenter.loadPost(k);
            k++;
        } else {
            dialog.dismiss();
            k = 2;
        }
    }


}