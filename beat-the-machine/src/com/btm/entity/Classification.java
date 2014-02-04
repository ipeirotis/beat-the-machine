package com.btm.entity;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
@Cache
public class Classification {

	@Id
	private Long id;
	private String classification;
	private String source;
	private float probability = 0.0f;
	
	@SuppressWarnings("unused")
	private Classification(){}

	public Classification(String classification, String source,
			float probability) {
		this.classification = classification;
		this.source = source;
		this.probability = probability;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public float getProbability() {
		return probability;
	}

	public void setProbability(float probability) {
		this.probability = probability;
	}

}
