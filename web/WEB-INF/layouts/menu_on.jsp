<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="nav nav-tabs nav-stacked">
    <li><a href="<c:url value="/index"/>"><i class="icon-home"></i> Accueil</a></li>
    <li><a href="<c:url value="/photos/mesPhotos"/>"><i class="icon-picture"></i> Mes Photos</a></li>
    <li><a href="<c:url value="/albums/mesAlbums"/>"><i class="icon-tag"></i> Mes Albums</a></li>
    <li><a href="<c:url value="/partage"/>"><i class="icon-user"></i> Albums partagés</a></li> 
    <li><a href="<c:url value="/albums/nouveau"/>"><i class="icon-plus"></i> Ajouter un album  <i class="icon-tag"></i></a></li>
    <li><a href="<c:url value="/upload"/>"><i class="icon-plus"></i> Ajouter une photo  <i class="icon-picture"></i></a></li>
</ul>

