package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String url="http://newsapi.org/v2/everything?q=bitcoin&from=2020-12-11&sortBy=publishedAt&apiKey=d95502bbd4b841ff9b4f07f925a470a9";
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Article> mArticles;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mArticles = new ArrayList<>();

        loadDataFromUrl();
    }

    public void loadDataFromUrl() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
              fetchData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }
    public void fetchData(JSONObject response) {
        try {
            JSONArray jsonArray = response.getJSONArray("articles");
            for(int i = 0; i < jsonArray.length(); i++) {
                Article article = new Article();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                article.setTitle(jsonObject.getString("title"));
                article.setDescription(jsonObject.getString("description"));
                article.setUrlToImage(jsonObject.getString("urlToImage"));
                article.setContent(jsonObject.getString("content"));
                mArticles.add(article);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        newsAdapter = new NewsAdapter(mArticles,this);
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
    }

}