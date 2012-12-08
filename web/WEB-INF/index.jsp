<%@page import="java.util.ArrayList"%>
<%@page import="bean.PhotoBean"%>
<%@page import="bdd.PhotoMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="span10">
    <!--Sidebar content-->
    <ul class="nav nav-tabs">
        <li class="active"><a href="<c:url value="/index"/>"><i class="icon-home"></i> Accueil</a></li>
        <li><a href="<c:url value="/utilisateurs"/>"><i class="icon-user"></i> Utilisateurs</a></li>
        <li><a href="<c:url value="/albums"/>"><i class="icon-tag"></i> Albums</a></li>
        <li><a href="<c:url value="/photos"/>"><i class="icon-picture"></i> Photos</a></li>
    </ul>
</div>

<div class="span10"> 

    <div id="myCarousel" class="carousel slide">
        <!-- Carousel items -->
        <div class="carousel-inner">
          
            <c:forEach items="${listImg}"  begin="0"  end="4" var="img"  >
                 <div class="item"><img class="imagecaro" alt="" src="<c:url value="/${img[0]}"/>"><div class="carousel-caption">
                    <h4><c:out value="${img[3]}"/></h4>
                    <p><c:out value="${img[4]}"/></p>
                </div></div>
                
            </c:forEach>
           
        </div>
        <!-- Carousel nav -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div>
</div>

<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/bootstrap.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script>$('.carousel').carousel()</script>


