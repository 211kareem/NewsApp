package com.route.newsapp.model.NewsResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse{

	@SerializedName("totalResults")
	public int totalResults;

	@SerializedName("articles")
	public List<ArticlesItem> articles;

	@SerializedName("status")
	public String status;

	@Override
 	public String toString(){
		return 
			"NewsResponse{" + 
			"totalResults = '" + totalResults + '\'' + 
			",articles = '" + articles + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}