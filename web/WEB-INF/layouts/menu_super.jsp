<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<i>Chaque action entraine toutes les actions descandantes</i>
<ul class="nav nav-tabs nav-stacked">
    <li><a href="<c:url value="/admin/deleteUsers"/>"><i class="icon-wrench"></i> Supprimer tous les utilisateurs</a></li>
    <li><a href="<c:url value="/admin/deleteAlbums"/>"><i class="icon-wrench"></i> Supprimer tous les albums</a></li>
    <li><a href="<c:url value="/admin/deletePhotos"/>"><i class="icon-wrench"></i> Supprimer toutes les photos</a></li>
    <li><a href="<c:url value="/admin/deleteRight"/>"><i class="icon-wrench"></i> Supprimer touts les droits</a></li>
</ul>