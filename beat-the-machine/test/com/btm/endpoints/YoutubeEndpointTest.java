package com.btm.endpoints;

import static com.btm.dao.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.btm.dto.YoutubeWrapper;
import com.btm.entity.Classification;
import com.btm.entity.Youtube;
import com.btm.enums.ContentType;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class YoutubeEndpointTest {
	
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	
	private YoutubeEndpoint youtubeEndpoint = new YoutubeEndpoint();


	@Before
	public void setUp() {
		helper.setUp();
		
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void run() throws Exception {
		Youtube youtube = new Youtube("testCategory", null, ContentType.VIDEO, 
				"http://www.youtube.com/channel/SBsCd0BUaGCwQ", null );

		List<Classification> classifications = new ArrayList<Classification>();
		classifications.add(new Classification("testClassificationId", "testSource", 1.0f));
		
		youtube.setClassifications(classifications);
		
		List<Youtube> entries = new ArrayList<Youtube>();
		entries.add(youtube);
		
		YoutubeWrapper wrapper = new YoutubeWrapper();
		wrapper.setEntries(entries );
		
		youtubeEndpoint.add(wrapper);
		
		Youtube y = ofy().load().type(Youtube.class).first().now();
		
		Assert.assertEquals(y.getContentId(), "SBsCd0BUaGCwQ");
		Assert.assertNotNull(y.getClassifications());
	}
}
