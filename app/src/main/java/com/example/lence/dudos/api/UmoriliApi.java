package com.example.lence.dudos.api;




import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UmoriliApi {
    @GET("/users")
    Call<ResponseBody> get();             //полдучения\ постов


    @GET("/posts/{id}")
    Call<ResponseBody> post(@Path("id") String id);

}
