package com.example.mobilelab2;

import retrofit2.http.Body;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @GET("/student")
    Call<List<Student>> getStudentList();

    @POST("/student")
    Call<Student> createStudent(@Body Student student);
}
