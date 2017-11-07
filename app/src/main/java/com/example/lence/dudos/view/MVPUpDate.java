package com.example.lence.dudos.view;


import com.example.lence.dudos.model.UserModel;

public interface MVPUpDate {
    void delete(String id);
    void upDate(UserModel user);
    void showNewUser();
}
