package com.btm.dto;

import java.util.List;

import com.btm.entity.Channel;
import com.btm.entity.Classification;

public class ChannelWithClassifications {
	private Channel channel;
	private List<Classification> classifications;
	
	public ChannelWithClassifications(){
		
	}
	
	public ChannelWithClassifications(Channel channel,
			List<Classification> classifications) {
		this.channel = channel;
		this.classifications = classifications;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public List<Classification> getClassifications() {
		return classifications;
	}

	public void setClassifications(List<Classification> classifications) {
		this.classifications = classifications;
	}
}
