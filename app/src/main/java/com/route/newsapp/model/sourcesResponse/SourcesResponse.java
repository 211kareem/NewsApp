package com.route.newsapp.model.sourcesResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SourcesResponse{

	@SerializedName("sources")
	private List<SourcesItem> sources;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSources(List<SourcesItem> sources){
		this.sources = sources;
	}

	public List<SourcesItem> getSources(){
		return sources;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"SourcesResponse{" + 
			"sources = '" + sources + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}