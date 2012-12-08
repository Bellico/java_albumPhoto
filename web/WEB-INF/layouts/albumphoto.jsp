<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/css/styles.css"/>" rel="stylesheet" type="text/css" />  
        <link rel="stylesheet" href="<c:url value="/js/fancybox/jquery.fancybox.css"/>" type="text/css" media="screen" />
        <link rel="stylesheet" href="<c:url value="/js/fancybox/helpers/jquery.fancybox-buttons.css"/>" type="text/css" media="screen" />
        <link rel="stylesheet" href="<c:url value="/js/fancybox/helpers/jquery.fancybox-thumbs.css"/>" type="text/css" media="screen" />
        <link href='http://fonts.googleapis.com/css?family=Marcellus+SC' rel='stylesheet' type='text/css'>
        <title>${titlePage} | Album Photo</title>
    </head>
    <body>
        <div id="banniere" class="well">
            <div class="optAdmin">
                <c:if test="${empty sessionScope.admin}">
                    <a href="<c:url value="/admin"/>"><button class="btn btn-warning">Devenir Admin</button> </a>
                </c:if>
                <c:if test="${!empty sessionScope.user}">
                    <a href="<c:url value="/utilisateurs/logOut"/>"><button class="btn btn-danger">Se deconnecter</button> </a>
                </c:if>
            </div>
            <div id="titre">Album Photo</div>
        </div>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span2">
                    <div class="well titre2">
                        ${namePage}
                    </div>
                    <c:choose>
                        <c:when test="${!empty sessionScope.user}">
                            <jsp:include page="menu_on.jsp" />
                        </c:when>
                        <c:otherwise>
                            <jsp:include page="menu_off.jsp" />
                        </c:otherwise>  
                    </c:choose>
                    <c:if test="${!empty sessionScope.admin}">
                        <div class="well titre2">
                            Super Actions
                        </div>
                        <jsp:include page="menu_super.jsp" />
                    </c:if>
                </div>

                <!--Body content-->
                <jsp:include page="/WEB-INF/${view}" />
            </div>
        </div>


        <script src="<c:url value="/js/jquery.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/fancybox/jquery.fancybox.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/fancybox/jquery.easing-1.3.pack.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/fancybox/jquery.mousewheel-3.0.6.pack.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/fancybox/helpers/jquery.fancybox-buttons.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/fancybox/helpers/jquery.fancybox-media.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/fancybox/helpers/jquery.fancybox-thumbs.js"/>"></script>

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

            $("#single_1").fancybox({
                helpers: {
                    title : {
                        type : 'float'
                    }
                }
            });
        });
    </script>
    </body>
</html>
