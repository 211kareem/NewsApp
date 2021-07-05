package com.route.newsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.route.newsapp.api.ApiManager;
import com.route.newsapp.model.NewsResponse.NewsResponse;
import com.route.newsapp.model.sourcesResponse.SourcesItem;
import com.route.newsapp.model.sourcesResponse.SourcesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    protected TabLayout tablayout;
    protected RecyclerView recyclerView;
    protected ProgressBar progressBar;
    protected View noData;
    NewsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRecyclerView();
        getNewsSources();


    }

    public void initRecyclerView(){
        adapter =new NewsAdapter(null);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
    public void getNewsSources() {
        ApiManager.getApis()
                .getNewsSource(Constants.API_KEY)
                .enqueue(new Callback<SourcesResponse>() {
                    @Override
                    public void onResponse(Call<SourcesResponse> call,
                                           Response<SourcesResponse> response) {
                      //  progressBar.setVisibility(View.GONE);
                        if ("ok".equals(response.body().getStatus())) {
                            List<SourcesItem> sourcesItems = response
                                    .body().getSources();
                            initTabLayout(sourcesItems);
                        } else {
                            Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SourcesResponse> call,
                                          Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        noData.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void initTabLayout(List<SourcesItem> sourcesItems) {
        for(SourcesItem source: sourcesItems){
           TabLayout.Tab tab =  tablayout.newTab();
           tab.setText(source.getName());
           tab.setTag(source);
           tablayout.addTab(tab);
        }
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                SourcesItem sourcesItem = ((SourcesItem) tab.getTag());
                getNewsBySourceId(sourcesItem.getId());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                SourcesItem sourcesItem = ((SourcesItem) tab.getTag());
                getNewsBySourceId(sourcesItem.getId());

            }
        });
        tablayout.getTabAt(0).select();
    }

    public void getNewsBySourceId(String sourceID){
        ApiManager.getApis().getNewsBySourceId(Constants.API_KEY,
                sourceID)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call,
                                           Response<NewsResponse> response) {
                        progressBar.setVisibility(View.GONE);
                        if(response.isSuccessful()){
                            if(response.body()!=null)
                                if("ok".equals(response.body().status)){
                                    adapter.changeData(response.body().articles);
                                }
                        }

                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        noData.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                });

    }
    private void initView() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        noData =  findViewById(R.id.no_data_view);
    }
}
