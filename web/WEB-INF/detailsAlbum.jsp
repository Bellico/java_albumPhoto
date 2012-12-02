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
    <table class="table table-bordered">
        <thead>
            <th>Titre</th>
                <td><c:out value="${details[1]}"/></td>
            </tr>
            <tr>
                <th>Utilisateur</th>
                <td><c:out value="${details[0]}"/></td>
            </tr>
          
                		  
            <tr>
                <th>Description</th>
                <td><c:out value="${details[2]}"/></td>
            </tr>
            
             <tr>
                <th>Nombres de photo</th>
                <td><c:out value="${details[3]}"/></td>
            </tr>
          
        </thead>
    </table>
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
                <th>Options</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listImg}" var="img"  >
                <tr>
                    <td class="photocadre"><img class="imagemin" alt="" src="<c:url value="/${img[0]}"/>"></td>
                    <td class="utilisateurs"><c:out value="${img[1]}"/></td>
                    <td class="titrealbum"><c:out value="${img[2]}"/></td>
                    <td class="titrealbum"><c:out value="${img[3]}"/></td>
                    <td class="description"><c:out value="${img[4]}"/></td>
                    <td class="dateajout"><c:out value="${img[5]}"/> </td>
                    <td class="dateajout"><c:out value="${img[6]}"/> </td>
                    <td> <a href="photos/<c:out value="${img[7]}"/>"><button class="btn btn-primary" type="button">Voir Détails</button></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>