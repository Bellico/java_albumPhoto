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
        <li class="active"><a href="<c:url value="/utilisateurs"/>"><i class="icon-user"></i> Utilisateurs</a></li>
        <li><a href="<c:url value="/albums"/>"><i class="icon-tag"></i> Albums</a></li>
        <li><a href="<c:url value="/photos"/>"><i class="icon-picture"></i> Photos</a></li>
    </ul>
</div>

<div class="span10"> 
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Pseudo</th>
                <th>Albums</th>
                <th>Photos</th>
                <th>Membres depuis</th>
                <th>Dernière mise à jour</th>
                <c:if test="${!empty sessionScope.user}">
                    <th>Option</th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${user}" var="us">
                <tr>
                    <td> <c:out value="${us.name}"/></td> 
                    <td> <c:out value="${us.firstName}"/></td> 
                    <td> <c:out value="${us.login}"/></td> 
                    <td> <c:out value="${us.getNbAlbum()}"/></td> 
                    <td> <c:out value="${us.getNbPhoto()}"/></td> 
                    <td> <c:out value="${us.getDateCreated()}"/></td> 
                    <td> <c:out value="${us.getDateLastUpdate()}"/></td> 
                    <c:if test="${!empty sessionScope.user}">
                        <td> 
                            <c:if test="${!empty sessionScope.admin || us.idUser==sessionScope.user.idUser}">
                                <p> <a href="<c:url value="/modif/utilisateur/${us.idUser}"/>"><button class="btn btn-small btn-inverse" type="button">Modifier</button></a> </p>
                                <p> <a href="<c:url value="/supp/utilisateur/${us.idUser}"/>"><button class="btn btn-small btn-danger" type="button">Supprimer</button></a> </p>
                            </c:if>
                        </td> 
                    </c:if>
                </tr>
            </c:forEach>  
        </tbody>
    </table>
</div>