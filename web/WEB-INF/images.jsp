<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="span10">
    <!--Sidebar content-->
    <ul class="nav nav-tabs">
        <li > <a href="index"><i class="icon-home"></i> Accueil</a></li>
        <li><a href="#"><i class="icon-user"></i> Utilisateurs</a></li>
        <li><a href="#"><i class="icon-tag"></i> Albums</a></li>
        <li class="active"><a href="#"><i class="icon-picture"></i> Images</a></li>
    </ul>
</div>

<div class="span10"> 


    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Images</th>
                <th>Utilisateur</th>
                <th>Album</th>
                <th>Titre</th>
                <th>Description</th>
                <th>Date de création</th>
                <th>Dernière mise à jour</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listPhoto}" var="lp">	
                <tr>
                    <td class="photocadre"><img class="imagemin" alt="" src="img/tn_010.jpg"></td>
                    <td class="utilisateurs">Emilien</td>
                    <td class="titrealbum">${listAlbum[lp.idAlbum].nameAlbum}</td>
                    <td class="titrealbum">${lp.title}</td>
                    <td class="description"><c:out value="${lp.descr}"/></td>
                    <td class="dateajout">15 janvier 2013 12:03:26</td>
                    <td class="datemodif">15 janvier 2013 12:03:26</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>