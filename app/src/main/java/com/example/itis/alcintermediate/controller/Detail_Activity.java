package com.example.itis.alcintermediate.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.itis.alcintermediate.GlideCircleTransformation;
import com.example.itis.alcintermediate.R;

/**
 * Created by enigma on 8/21/17.
 */

public class Detail_Activity extends AppCompatActivity{

    TextView Link, Username;
    Toolbar mActionBarToolbar;
    ImageView imageView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.user_image_header);
        Username = (TextView) findViewById(R.id.username);

        Link = (TextView) findViewById(R.id.githublink1);

        String username = getIntent().getExtras().getString("login");
        String avatarURl = getIntent().getExtras().getString("avatar_url");
        String link = getIntent().getExtras().getString("html_url");

        Link.setText(link);
        Linkify.addLinks(Link, Linkify.WEB_URLS);

        Username.setText(username);
        Glide.with(this)
                .load(avatarURl)
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.drawable.load)
                .error(R.mipmap.ic_launcher)
                .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        getSupportActionBar().setTitle("Details Activity");


    }

    private Intent createShareForcastIntent(){

        String username = getIntent().getExtras().getString("login");
        String link = getIntent().getExtras().getString("link");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("this developer way bad like that @ "+ username+","+link)
                .getIntent();
        return shareIntent;
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate (R.menu.detail,menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForcastIntent());
        return true;
    }
}

