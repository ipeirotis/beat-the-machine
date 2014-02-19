package com.btm.dto;

import java.util.List;

import com.btm.entity.Youtube;

public class YoutubeWrapper{	
	
	private List<Youtube> entries;
	
	public YoutubeWrapper() {
	}
	
	public YoutubeWrapper(List<Youtube> entries) {
		this.entries = entries;
	}

	public List<Youtube> getEntries() {
		return entries;
	}

	public void setEntries(List<Youtube> entries) {
		this.entries = entries;
	}
	
}
