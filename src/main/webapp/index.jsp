<%-- The Java code in this JSP file runs on the server when the user navigates
     to the homepage. This allows us to insert the Blobstore upload URL into the
     form without building the HTML using print statements in a servlet. --%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    String uploadUrl = blobstoreService.createUploadUrl("/my-form-handler"); %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile setup page</title>
</head>
<body>
<h1>Profile setup page</h1>
<p>Type your intro and click submit:</p>

<form method="POST" enctype="multipart/form-data" action="<%= uploadUrl %>">
    <p>write something about yourself:</p>
    <textarea name="message"></textarea>
    <br/>
    <p>Upload an image:</p>
    <input type="file" name="image">
    <br/><br/>
    <button>Submit</button>
</form>
</body>
</html>