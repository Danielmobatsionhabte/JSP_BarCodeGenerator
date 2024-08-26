<%-- 
    Document   : index
    Created on : Jan 9, 2019, 8:22:13 PM
    Author     : Dani-Boy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="barcodercontroller" method="post">
            <input type="text" name="barcode" placeholder="Enter name here">
            <input type="submit" value="generate">
        </form>
    </body>
</html>
