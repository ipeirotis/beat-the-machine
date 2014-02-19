package com.btm.utils;

import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

public class UrlUtilsTest {

	@Test
	public void run() throws URISyntaxException {
		
		String id = UrlUtils.extractIdFromUrl("http://www.youtube.com/channel/SBsCd0BUaGCwQ");

		Assert.assertNotNull(id);
	}
}
