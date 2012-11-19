<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>debu template</h1>
        <jsp:include page="/WEB-INF/${view}" />
        <h1>fin template</h1>
    </body>
</html>
