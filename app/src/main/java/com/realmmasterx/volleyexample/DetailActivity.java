package com.realmmasterx.volleyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.realmmasterx.volleyexample.MainActivity.EXTRA_CREATOR;
import static com.realmmasterx.volleyexample.MainActivity.EXTRA_LIKES;
import static com.realmmasterx.volleyexample.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        int likeCount = intent.getIntExtra(EXTRA_LIKES, 0);

        ImageView imageView = findViewById(R.id.imageViewDetail);
        TextView textViewCreator = findViewById(R.id.textViewCreatorDetail);
        TextView textViewLikes = findViewById(R.id.textViewLikesDetail);

        Picasso.get().load(imageUrl).fit().into(imageView);
        textViewLikes.setText("Likes: " + likeCount);
        textViewCreator.setText(creatorName);
    }
}