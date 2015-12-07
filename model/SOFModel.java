package com.shivamdev.rxdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shivamchopra on 20/11/15.
 */
public class SOFModel {

	public static class Items {

		@SerializedName("items")
		public List<Question> items;

		public List<Question> getItems() {
			return items;
		}

		public void setItems(List<Question> items) {
			this.items = items;
		}
	}

	public static class Question {

		@SerializedName("title")
		public String title;

		@SerializedName("link")
		public String link;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}
	}
}