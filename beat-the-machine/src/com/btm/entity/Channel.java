package com.btm.entity;

import java.util.ArrayList;
import java.util.List;

import com.btm.enums.ContentType;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Channel {

	@Id
	private String id;
	private String category;
	@Index
	private String contentId;
	private ContentType contentType;
	@Index
	private List<String> classifications;
	
	@SuppressWarnings("unused")
	private Channel(){}

	public Channel(String category, String contentId, ContentType contentType, 
			List<String> classifications) {
		this.classifications = classifications;
		this.category = category;
		this.contentId = contentId;
		this.contentType = contentType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getClassifications() {
		return classifications;
	}

	public void setClassifications(List<String> classifications) {
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
	
	public void addClassification(String classification){
		if(classifications == null)
			classifications = new ArrayList<String>();
		
		classifications.add(classification);
	}

	public boolean containsClassification(String classification){
		for(String c : classifications){
			if(classification.equals(c))
				return true;
		}
		
		return false;
	}
}
