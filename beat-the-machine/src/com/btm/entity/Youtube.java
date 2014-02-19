package com.btm.entity;

import java.util.List;

import com.btm.enums.ContentType;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Youtube {

	@Id
	private String contentId;
	private String category;
	private ContentType contentType;
	private String url;
	@Index
	private List<Classification> classifications;
	
	@SuppressWarnings("unused")
	private Youtube(){}

	public Youtube(String category, String contentId,
			ContentType contentType, String url, List<Classification> classifications) {
		this.category = category;
		this.contentId = contentId;
		this.contentType = contentType;
		this.url = url;
		this.classifications = classifications;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Classification> getClassifications() {
		return classifications;
	}

	public void setClassifications(List<Classification> classifications) {
		this.classifications = classifications;
	}

}
