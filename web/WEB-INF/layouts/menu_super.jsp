<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="nav nav-tabs nav-stacked">
    <li><a href="<c:url value="/index"/>"><i class="icon-wrench"></i>Supprimer tous les utilisateurs</a></li>
    <li><a href="<c:url value="/albums/mesAlbums"/>"><i class="icon-wrench"></i>Supprimer tous les albums</a></li>
    <li><a href="<c:url value="/photos/mesPhotos"/>"><i class="icon-wrench"></i> Supprimer toutes les photos</a></li>
</ul>