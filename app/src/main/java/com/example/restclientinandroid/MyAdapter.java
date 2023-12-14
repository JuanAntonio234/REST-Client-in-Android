package com.example.restclientinandroid;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Track> listaTracks;
    private Context context;
    private View.OnClickListener listener;
    public View layout;
////////////////
    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtTrack, txtTitle, txtSinger;
    public ViewHolder(@NonNull View view) {
            super(view);

            txtTrack = (TextView) view.findViewById(R.id.id_track);
            txtTitle = (TextView) view.findViewById(R.id.title_track);
            txtSinger = (TextView) view.findViewById(R.id.singer_track);
        }
    }
    ////////////
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tracks,parent,false);
        view.setOnClickListener(listener);
        return new ViewHolder(view);
    }
   ///////////////
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.txtTrack.setText(listaTracks.get(position).getId().toString());
        holder.txtTitle.setText(listaTracks.get(position).getTitle().toString());
        holder.txtSinger.setText(listaTracks.get(position).getSinger().toString());

    }
    ///////////////
    @Override
    public int getItemCount(){
        return listaTracks.size();
    }
    //////////////////
    public void setOnItemClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    //////////////////
    public void onClick(View view){
        if(listener!=null){
            listener.onClick(view);
        }
    }
    ////////////////
    public  MyAdapter(ArrayList<Track>listaTracks,Context context){
        this.listaTracks=listaTracks;
        this.context=context;
    }
}
