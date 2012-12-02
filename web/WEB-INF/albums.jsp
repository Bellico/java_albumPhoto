<%@page import="bdd.UserMap"%>
<%@page import="bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.PhotoBean"%>
<%@page import="bdd.PhotoMap"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="span10">
    <!--Sidebar content-->
    <ul class="nav nav-tabs">
        <li><a href="<c:url value="/index"/>"><i class="icon-home"></i> Accueil</a></li>
        <li><a href="<c:url value="/utilisateurs"/>"><i class="icon-user"></i> Utilisateurs</a></li>
        <li class="active"><a href="<c:url value="/albums"/>"><i class="icon-tag"></i> Albums</a></li>
        <li><a href="<c:url value="/photos"/>"><i class="icon-picture"></i> Photos</a></li>
    </ul>
</div>

<div class="span10"> 
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Album</th>
                <th>Auteur</th>
                <th>Description</th>
                <th>Photos</th>
                <th>Options</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listAlbum}" var="album">
                <tr>
                    <td> <c:out value="${album[0]}"/></td> 
                    <td><c:out value="${album[1]}"/></td> 
                    <td><c:out value="${album[2]}"/></td> 
                    <td><c:out value="${album[4]}"/></td>
                    <td> <a href="albums/<c:out value="${album[3]}"/>"><button class="btn btn-primary" type="button">Voir Détails</button></a></td>
                </tr>
            </c:forEach>  
        </tbody>
    </table>
    
    
</div>
   