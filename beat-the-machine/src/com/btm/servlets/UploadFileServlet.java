package com.btm.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btm.mapreduce.ImportFileMapper;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.tools.mapreduce.MapReduceJob;
import com.google.appengine.tools.mapreduce.MapReduceSettings;
import com.google.appengine.tools.mapreduce.MapReduceSpecification;
import com.google.appengine.tools.mapreduce.Marshallers;
import com.google.appengine.tools.mapreduce.inputs.BlobstoreInput;
import com.google.appengine.tools.mapreduce.outputs.NoOutput;
import com.google.appengine.tools.mapreduce.reducers.NoReducer;

@SuppressWarnings("serial")
public class UploadFileServlet extends HttpServlet {
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UploadFileServlet.class.getName());
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("data");

		for(BlobKey blobKey : blobKeys){
	        mapreduce(blobKey.getKeyString(), 5, 5);
		}
        res.getWriter().print("done");
	}

	private String mapreduce(String blobKey, int mapShardCount, int reduceShardCount) {
		return MapReduceJob.start(
			MapReduceSpecification.of(
				"Import file",
				new BlobstoreInput(blobKey,(byte)'\n',mapShardCount),
				new ImportFileMapper(),
				Marshallers.getVoidMarshaller(),
				Marshallers.getVoidMarshaller(),
				NoReducer.<Void, Void, Void> create(),
				NoOutput.<Void, Void> create(1)), getSettings());
	}

	private MapReduceSettings getSettings() {
		MapReduceSettings settings = new MapReduceSettings();
		settings.setWorkerQueueName("mapreduce-workers");
		settings.setBucketName("mreduce");
		return settings;
	}

}
