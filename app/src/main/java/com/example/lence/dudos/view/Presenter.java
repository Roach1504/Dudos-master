package com.example.lence.dudos.view;


import android.util.Log;

import com.example.lence.dudos.api.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {
    MVP mvp;

    public Presenter(MVP MVP) {
        mvp = MVP;
    }

    public void loadGet() {
        App.getApi().get().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                List<String> getList = new ArrayList<>();
                String json = null;
                JSONArray jsonArray;
                try {
                    json = response.body().string();

                    jsonArray = new JSONArray(json);
                    JSONObject jsonObject;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        getList.add(jsonObject.getString("name"));

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    mvp.showIsEmpty();
                    e.printStackTrace();
                }
                mvp.showGet(getList);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("get", "error: " + t);
            }


        });

    }

    public void loadPost(int k) {

        App.getApi().post(String.valueOf(k)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("Error", response.message() + " "+response.code());
                String json = null;
                JSONObject jsonObject;

                try {
                    json = response.body().string();
                    jsonObject = new JSONObject(json);
                    json  = jsonObject.getString("title");
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("post", json);
                if(json != null){
                    mvp.showPost(json);
                }
                else{
                    Log.e("POST", "isEmpty");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("get", "error: " + t);
            }


        });


    }
}
