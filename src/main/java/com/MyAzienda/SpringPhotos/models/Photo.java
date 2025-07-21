package com.MyAzienda.SpringPhotos.models;

public class Photo {
	
	private int id;
	private String url;
	
	public Photo() {}

	public Photo(int id, String url) {
		super();
		this.id = id;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

}
