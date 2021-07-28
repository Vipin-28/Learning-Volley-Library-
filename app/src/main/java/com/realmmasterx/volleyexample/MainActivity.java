package com.realmmasterx.volleyexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemListener {
    public static final String EXTRA_URL = "imageURL";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES = "likeCount";

    private RecyclerView recyclerView;
    private ExampleAdapter exampleAdapter;
    private ArrayList<ExampleItem> exampleList;
private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        exampleList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        parseJSON();

    }

    private void parseJSON(){
        String url = "https://pixabay.com/api/?key=22673646-b5bcb5cd163e53b17466e1714&q=yellow+flowers&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
           response -> {
               try {
                   JSONArray jsonArray = response.getJSONArray("hits");

                   for(int i = 0;i<jsonArray.length();i++){
                       JSONObject hit = jsonArray.getJSONObject(i);
                       String creatorName = hit.getString("user");
                       String imageUrl = hit.getString("webformatURL");
                       int likeCount = hit.getInt("likes");

                       exampleList.add(new ExampleItem(imageUrl,creatorName,likeCount));
                   }

                   exampleAdapter = new ExampleAdapter(this, exampleList);
                   recyclerView.setAdapter(exampleAdapter);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }, error -> {
            error.printStackTrace();
        });

        requestQueue.add(request);
    }

    @Override
    public void OnItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ExampleItem clickedItem = exampleList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR,clickedItem.getCreator());
        detailIntent.putExtra(EXTRA_LIKES, clickedItem.getLikes());
        startActivity(detailIntent);
    }
}