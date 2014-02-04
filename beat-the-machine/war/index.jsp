<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();%>

<!DOCTYPE html>
<html>
<head>
<title>beat-the-machine</title>
</head>

<body>
	<form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
        <input type="file" name="data">
        <input type="submit" value="Submit">
    </form>
</body>
</html>
