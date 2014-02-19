package com.btm.mapreduce;

import java.util.Arrays;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.mapreduce.DatastoreMutationPool;
import com.google.appengine.tools.mapreduce.Mapper;

public class ImportFileMapper extends Mapper<byte[], Void, Void> {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ImportFileMapper.class.getName());
	private static final long serialVersionUID = 1L;
	private transient DatastoreMutationPool batcher;

	public ImportFileMapper() {

	}

	@Override
	public void beginSlice() {
		batcher = DatastoreMutationPool.forManualFlushing();
	}

	@Override
	public void endSlice() {
		batcher.flush();
	}

	@Override
	public void map(byte[] value) {
		String line = new String(value);
		String[] values = line.split(";");
		
		String contentId = values[0];
		String category = values[1];
		String contentType = values[2];
		String url = values[3];
		
		String classification = values[4];
		String source = values[5];
		Float probability = Float.parseFloat(values[6]);
		
		Entity e = new Entity("Youtube", contentId);
		e.setProperty("category", category);
		e.setProperty("contentType", contentType);
		e.setProperty("url", url);
		
		e.setProperty("classifications.classification", Arrays.asList(classification));
		e.setProperty("classifications.source", Arrays.asList(source));
		e.setProperty("classifications.probability", Arrays.asList(probability));

		batcher.put(e);
	}
}