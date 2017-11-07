package com.example.lence.dudos.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lence.dudos.R;
import com.example.lence.dudos.dateBase.DBManager;
import com.example.lence.dudos.model.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity implements MVPUpDate{

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.insert)
    Button insert;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    DBManager dbManager;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        dbManager = new DBManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(!dbManager.getUsers().isEmpty()){
            UserAdapter userAdapter = new UserAdapter(dbManager.getUsers(),this);
            recyclerView.setAdapter(userAdapter);
            recyclerView.getAdapter().notifyDataSetChanged();
        }
        else {
            Toast.makeText(this, "Not info of User", Toast.LENGTH_LONG).show();
        }

    }

    @OnClick(R.id.insert)
    public void onViewClicked() {
        if(insert.getText().equals("UpDate")){
            if ((name.getText().length() > 0) && (age.getText().length() > 0)) {
                UserModel userModel = new UserModel();
                userModel.setId(id);
                userModel.setName(name.getText().toString());
                userModel.setAge(age.getText().toString());
                userModel.setGender(spinner.getSelectedItem().toString());
                if (checkBox.isChecked()) {
                    userModel.setMarried("true");
                } else {
                    userModel.setMarried("false");
                }
                dbManager.upDate(id, userModel);
                insert.setText("INSERT");
                name.setText("");
                age.setText("");
                spinner.setSelection(0);
                checkBox.setChecked(false);
            }
            else{
                Toast.makeText(this, "Input info", Toast.LENGTH_LONG).show();
            }
        }
        else {
            if ((name.getText().length() > 0) && (age.getText().length() > 0)) {
                UserModel userModel = new UserModel();
                userModel.setName(name.getText().toString());
                userModel.setAge(age.getText().toString());
                userModel.setGender(spinner.getSelectedItem().toString());
                if (checkBox.isChecked()) {
                    userModel.setMarried("true");
                } else {
                    userModel.setMarried("false");
                }
                dbManager.insert(userModel);
                name.setText("");
                age.setText("");
                spinner.setSelection(0);
                checkBox.setChecked(false);
            }
            else{
                Toast.makeText(this, "Input info", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void delete(String id) {
        dbManager.delete(id);
    }

    @Override
    public void upDate(UserModel user) {
        insert.setText("UpDate");
        id = user.getId();
        name.setText(user.getName());
        age.setText(user.getAge());
        if(user.getMarried().equals("true")){
           // checkBox.setEnabled(true);
            checkBox.setChecked(true);
        }
        else{
            checkBox.setChecked(false);
        }
        if(user.getGender().equals("Male")){
            spinner.setSelection(1);
        }
        else{
            spinner.setSelection(0);
        }

    }

    @Override
    public void showNewUser() {
        UserAdapter userAdapter = new UserAdapter(dbManager.getUsers(),this);
        recyclerView.setAdapter(userAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
