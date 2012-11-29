<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="span10">
    <!--Sidebar content-->
    <ul class="nav nav-tabs">
        <li> <a href="index"><i class="icon-home"></i> Accueil</a></li>
        <li><a href="utilisateurs"><i class="icon-user"></i> Utilisateurs</a></li>
        <li><a href="albums"><i class="icon-tag"></i> Albums</a></li>
        <li><a href="images"><i class="icon-picture"></i> Images</a></li>
    </ul>
</div>

<div class="span10"> 
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Images</th>
                <td><img class="imagemin" alt="" src="<c:url value="/${details[0]}"/>"></td>
            </tr>
            <tr>
                <th>Utilisateur</th>
                <td><c:out value="${details[1]}"/></td>
            </tr>
            <tr>
                <th>Album</th>
                <td><c:out value="${details[2]}"/></td>
            </tr>
            <tr>
                <th>Titre</th>
                <td><c:out value="${details[3]}"/>t</td>
            </tr>		  
            <tr>
                <th>Description</th>
                <td><c:out value="${details[4]}"/></td>
            </tr>	
            <tr>
                <th>Largeur</th>
                <td><c:out value="${details[5]}"/></td>
            </tr>	
            <tr>
                <th>Hauteur</th>
                <td><c:out value="${details[6]}"/></td>
            </tr>	
            <tr>
                <th>Date création</th>
                <td><c:out value="${details[7]}"/></td>
            </tr>	
            <tr>
                <th>Date dernière modification</th>
                <td><c:out value="${details[8]}"/></td>
            </tr>	
        </thead>
    </table>
</div>