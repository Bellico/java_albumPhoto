<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/css/styles.css"/>" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="<c:url value="/css/jquery.fancybox.css?v=2.1.3"/>" type="text/css" media="screen" />
        <link rel="stylesheet" href="<c:url value="/css/jquery.fancybox-buttons.css?v=1.0.5"/>" type="text/css" media="screen" />
        <link rel="stylesheet" href="<c:url value="/css/jquery.fancybox-thumbs.css?v=1.0.7"/>" type="text/css" media="screen" />
        
        
        
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
                        <c:when test="${empty sessionScope.user}">
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
            
            
            
            
            <script src="js/jquery.js"></script>
            <script type="text/javascript" src="js/jquery.mousewheel-3.0.6.pack.js"></script>
            <script type="text/javascript" src="js/jquery.fancybox.pack.js?v=2.1.3"></script>
            <script type="text/javascript" src="js/jquery.fancybox-buttons.js?v=1.0.5"></script>
            <script type="text/javascript" src="js/jquery.fancybox-media.js?v=1.0.5"></script>
            <script type="text/javascript" src="js/jquery.fancybox-thumbs.js?v=1.0.7"></script>
             <script type="text/javascript">
	$(document).ready(function() {
	$(".fancybox-thumb").fancybox({
		prevEffect	: 'none',
		nextEffect	: 'none',
		helpers	: {
			title	: {
				type: 'outside'
			},
			thumbs	: {
				width	: 50,
				height	: 50
			}
		}
	});
});
</script>
    </body>
</html>
