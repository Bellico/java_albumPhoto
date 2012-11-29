<%@page import="java.util.ArrayList"%>
<%@page import="bean.PhotoBean"%>
<%@page import="bdd.PhotoMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="span10">
    <!--Sidebar content-->
    <ul class="nav nav-tabs">
        <li class="active"><a href="#"><i class="icon-home"></i> Accueil</a></li>
        <li><a href="utilisateurs"><i class="icon-user"></i> Utilisateurs</a></li>
        <li><a href="albums"><i class="icon-tag"></i> Albums</a></li>
        <li><a href="images"><i class="icon-picture"></i> Images</a></li>
    </ul>
</div>

<div class="span10"> 

    <div id="myCarousel" class="carousel slide">
        <!-- Carousel items -->
        <div class="carousel-inner">
            <div class="active item"><img class="imagecaro" alt="" src="<c:url value="/img/tnn_010.jpg"/>"> <div class="carousel-caption">
                    <h4>Publie tes photos préférées</h4>
                    <p>Grâce à notre tout nouvel outil !</p>
                </div></div>
            <div class="item"><img class="imagecaro" alt="" src="<c:url value="/img/tnn_233.jpg"/>"><div class="carousel-caption">
                    <h4>Le monde va te connaître</h4>
                    <p>Partarge tes photos avec qui tu veux !</p>
                </div></div>
            <div class="item"><img class="imagecaro" alt="" src="<c:url value="/img/tnn_200.jpg"/>"><div class="carousel-caption">
                    <h4>Navigation intuitive</h4>
                    <p>C'est tellement facile de naviguer dans ses menus !</p>
                </div></div>
        </div>
        <!-- Carousel nav -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div>
</div>

<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>$('.carousel').carousel()</script>


