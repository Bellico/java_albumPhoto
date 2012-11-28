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
            <c:forEach items="${listImg}" var="img"  >
                <tr>
                    <td class="photocadre"><img class="imagemin" alt="" src="<c:out value="${img[0]}"/>"></td>
                    <td class="utilisateurs"><c:out value="${img[1]}"/></td>
                    <td class="titrealbum"><c:out value="${img[2]}"/></td>
                    <td class="titrealbum"><c:out value="${img[3]}"/></td>
                    <td class="description"><c:out value="${img[4]}"/></td>
                    <td class="dateajout"><c:out value="${img[5]}"/> </td>
                    <td class="dateajout"><c:out value="${img[6]}"/> </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
