<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h2>Select files for upload</h2>
<form method="POST" action="Servlet1"
      enctype="multipart/form-data" >
    <input type="file" name="file" multiple="multiple"/>
    <input type="submit" value="Upload Images"/>
</form>
<hr/>
<br/>
<h2>Go to view page</h2>
<form method="POST" action="Servlet2">
    <input type="submit" value="View Page" name="showImages"/>
</form>
</body>
</html>