package com.btm.endpoints;

import static com.btm.dao.OfyService.ofy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Named;

import com.btm.dto.YoutubeWrapper;
import com.btm.entity.Classification;
import com.btm.entity.Youtube;
import com.btm.utils.UrlUtils;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;

@Api(name = "btm", version = "v1")
public class YoutubeEndpoint {

	public void add(YoutubeWrapper wrapper) throws BadRequestException, ConflictException {
		if(wrapper.getEntries() != null){
			List<Youtube> youtubeList = new ArrayList<Youtube>();
			for(Youtube newYoutube : wrapper.getEntries()){
				if(newYoutube.getUrl() != null)
					newYoutube.setContentId(UrlUtils.extractIdFromUrl(newYoutube.getUrl()));
				
				Youtube existYoutube = ofy().load().type(Youtube.class).id(newYoutube.getContentId()).now();
				
				List<Classification> classifications = newYoutube.getClassifications();
				if(classifications == null)
					throw new BadRequestException(
							"Clasification can't be empty(contentId=" + newYoutube.getContentId()+")");
				else{
					if(existYoutube == null)
						youtubeList.add(newYoutube);
					else{
						for(Classification existClassification : existYoutube.getClassifications()){
							for(Classification newClassification : newYoutube.getClassifications()){
								if(existClassification.getClassification().equals(newClassification.getClassification()))
									throw new ConflictException("ContentId="+newYoutube.getContentId() + " with classification " + newClassification.getClassification() + " already exists");
							}
						}
						existYoutube.getClassifications().addAll(newYoutube.getClassifications());
						youtubeList.add(existYoutube);
					}
				}					
			}
			ofy().save().entities(youtubeList).now();
		}
	}

	@ApiMethod(name = "getClassifications", path="getClassifications")
	public Collection<Classification> getClassifications(@Named("contentId") String contentId) throws NotFoundException {
		Youtube youtube = ofy().load().type(Youtube.class).id(contentId).now();
		if(youtube == null)
			throw new NotFoundException("Content with id='" + contentId + "' is not found");
		return youtube.getClassifications();
	}

}
