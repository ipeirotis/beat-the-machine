package com.btm.entity;

import com.googlecode.objectify.annotation.Embed;

@Embed
public class Classification {

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
