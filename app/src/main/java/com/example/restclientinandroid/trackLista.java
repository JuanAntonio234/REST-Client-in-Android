package com.example.restclientinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class trackLista extends AppCompatActivity {

    TextView id,title,singer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_lista);
        id=(TextView) findViewById(R.id.id);
        title=(TextView) findViewById(R.id.title);
        singer=(TextView) findViewById(R.id.singer);

        Bundle bundle=this.getIntent().getExtras();
        if(bundle!=null){
            String ID=bundle.getString("id");
            id.setText("id: "+ID);
            String TITLE=bundle.getString("title");
            title.setText("Title: "+TITLE);
            String SINGER=bundle.getString("singer");
            singer.setText("Singer: "+SINGER);
        }
    }
}