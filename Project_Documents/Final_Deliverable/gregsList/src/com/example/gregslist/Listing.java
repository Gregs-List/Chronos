package com.example.gregslist;

import android.graphics.drawable.Drawable;
import android.util.Log;

public class Listing {
	private String listingID;
	private String userID;
	private String title;
	private String dateListed;
	private String category;
	private String price;
	private String description;

	public Listing(String listingID, String userID, String title, String dateListed, String category, String price, String description) {
		this.listingID = listingID;
	    this.userID = userID;
		this.title = title;
		this.dateListed = dateListed;
		this.category = category;
		this.price = price;
		this.description = description;
	}
	
	public String getListingID() {
		return listingID;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDateListed() {
		return dateListed;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
}