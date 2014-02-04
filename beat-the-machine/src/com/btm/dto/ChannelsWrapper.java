package com.btm.dto;

import java.util.List;

public class ChannelsWrapper{	
	
	private List<ChannelWithClassifications> entries;
	
	public ChannelsWrapper() {
	}
	
	public ChannelsWrapper(List<ChannelWithClassifications> entries) {
		this.entries = entries;
	}

	public List<ChannelWithClassifications> getEntries() {
		return entries;
	}

	public void setEntries(List<ChannelWithClassifications> entries) {
		this.entries = entries;
	}
	
}
