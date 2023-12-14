package com.example.restclientinandroid;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TracksService {
    @GET("dsaApp/tracks")
    Call<ArrayList<Track>> getTracks();
}
