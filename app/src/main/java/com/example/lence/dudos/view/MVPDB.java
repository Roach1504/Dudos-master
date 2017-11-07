package com.example.lence.dudos.view;

import com.example.lence.dudos.model.UserModel;

import java.util.List;

public interface MVPDB {
    void insert(UserModel insertUser);
    List<UserModel> getUsers();
    void delete(String id);
    void upDate(String id, UserModel upUser);
}
