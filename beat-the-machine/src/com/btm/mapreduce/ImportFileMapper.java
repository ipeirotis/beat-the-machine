package com.btm.mapreduce;

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
		Entity e = new Entity("Channel");
		e.setProperty("url", new String(value));
		batcher.put(e);
	}
}