package com.btm.endpoints;

import static com.btm.dao.OfyService.ofy;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.btm.dto.ChannelWithClassifications;
import com.btm.dto.ChannelsWrapper;
import com.btm.entity.Channel;
import com.btm.entity.Classification;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

@Api(name = "btm", version = "v1")
public class ChannelEndpoint {

	public void addChannel(ChannelsWrapper wrapper) {
		for(ChannelWithClassifications entry : wrapper.getEntries()){
			Channel channel = entry.getChannel();
			List<Classification> classifications = entry.getClassifications();
			for(Classification c : classifications){
				channel.addClassification(c.getClassification());
			}
			ofy().save().entity(channel).now();
			ofy().save().entities(classifications).now();
		}
	}

	@ApiMethod(name = "getClassifications", path="/getClassifications")
	public Collection<Classification> getClassifications(@Named("contentId") String contentId) {
		Channel channel = ofy().load().type(Channel.class).filter("contentId", contentId).first().now();
		Map<String, Classification> map = ofy().load().type(Classification.class).ids(channel.getClassifications());
		return map.values();
	}
}
