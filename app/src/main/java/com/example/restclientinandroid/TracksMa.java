package com.example.restclientinandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;


public class TracksMa extends AppCompatActivity {
    private ArrayList<Track>listaTracks;
    private RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracks_ma);
        listaTracks=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HttpLoggingInterceptor login = new HttpLoggingInterceptor();
        login.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(login);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        TracksService productos = retrofit.create(TracksService.class);
        Call<ArrayList<Track>> call = productos.getTracks();
        call.enqueue(new Callback<ArrayList<Track>>() {
            @Override
            public void onResponse(Call<ArrayList<Track>>call,Response<ArrayList<Track>>response){
                if(response.isSuccessful()){
                    listaTracks=response.body();
                    MyAdapter adapter=new MyAdapter(listaTracks,TracksMa.this);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle miBundle=new Bundle();
                            miBundle.putString("id",listaTracks.get(recyclerView.getChildAdapterPosition(view)).getId());
                            miBundle.putString("título",listaTracks.get(recyclerView.getChildAdapterPosition(view)).getTitle());
                            miBundle.putString("cantante",listaTracks.get(recyclerView.getChildAdapterPosition(view)).getSinger());
                            Intent intent = new Intent(TracksMa.this,trackLista.class);
                            intent.putExtras(miBundle);
                            startActivity(intent);
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Track>> call, Throwable t) {
                Toast.makeText(TracksMa.this,"error",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
