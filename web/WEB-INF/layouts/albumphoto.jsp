<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/css/styles.css"/>" rel="stylesheet" type="text/css" />
        <title>${page.titlePage} | Album Photo</title>
    </head>
    <body>
        <div class="well">
            <div id="titre">Album Photo</div>
        </div>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span2">
                    <div class="well" id="titre2">
                        ${page.namePage}
                    </div>
                    <c:choose>
                        <c:when test="${!empty sessionScope.user}">
                            <jsp:include page="menu_on.jsp" />
                        </c:when>
                        <c:otherwise>
                            <jsp:include page="menu_off.jsp" />
                        </c:otherwise>  
                    </c:choose>
                </div>
                    
                <!--Body content-->
                <jsp:include page="/WEB-INF/${view}" />
            </div>
        </div>
    </body>
</html>
