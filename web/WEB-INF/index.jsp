<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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