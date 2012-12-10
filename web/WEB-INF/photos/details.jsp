<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="span10">
    <!--Sidebar content-->
    <ul class="nav nav-tabs">
        <li><a href="<c:url value="/index"/>"><i class="icon-home"></i> Accueil</a></li>
        <li><a href="<c:url value="/utilisateurs"/>"><i class="icon-user"></i> Utilisateurs</a></li>
        <li><a href="<c:url value="/albums"/>"><i class="icon-tag"></i> Albums</a></li>
        <li><a href="<c:url value="/photos"/>"><i class="icon-picture"></i> Photos</a></li>
    </ul>
</div>

<div class="span10"> 
    <table class="tabdetails table table-bordered">
        <thead>
            <tr>
                  <td colspan="2" class="photocadre">
                        <a id="single_1" class="fancybox-thumb" rel="fancybox-thumb" href="<c:url value="/${details.url}"/>" title="<c:out value="${details.tiltePhoto}"/>">
                            <img alt="" src="<c:url value="/${details.url}"/>">
                        </a>
                    </td>
            </tr>
            <tr>
                <th>Auteur</th>
                <td><c:out value="${details.userName}"/></td>
            </tr>
            <tr>
                <th>Album</th>
                <td><a href="<c:url value="/albums/${details.idAlbum}"/>"><c:out value="${details.nameAlbum}"/></a></td>
            </tr>
            <tr>
                <th>Titre</th>
                <td><c:out value="${details.titlePhoto}"/></td>
            </tr>		  
            <tr>
                <th>Description</th>
                <td><c:out value="${details.photoDescr}"/></td>
            </tr>	
            <tr>
                <th>Largeur</th>
                <td><c:out value="${details.widthPhoto}"/></td>
            </tr>	
            <tr>
                <th>Hauteur</th>
                <td><c:out value="${details.heightPhoto}"/></td>
            </tr>	
            <tr>
                <th>Date création</th>
                <td><c:out value="${details.photoDateCreated}"/></td>
            </tr>	
            <tr>
                <th>Date dernière modification</th>
                <td><c:out value="${details.photoLastUp}"/></td>
            </tr>	
        </thead>
    </table>
</div>
