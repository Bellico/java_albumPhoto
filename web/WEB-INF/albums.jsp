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
        <li > <a href="index"><i class="icon-home"></i> Accueil</a></li>
        <li ><a href="utilisateurs"><i class="icon-user"></i> Utilisateurs</a></li>
        <li class="active"><a href="#"><i class="icon-tag"></i> Albums</a></li>
        <li><a href="images"><i class="icon-picture"></i> Images</a></li>
    </ul>
</div>

<div class="span10"> 
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Album</th>
                <th>Auteur</th>
                <th>Description</th>
                <th>Images</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listAlbum}" var="album">
                <tr>
                    <td> <c:out value="${album[0]}"/></td> 
                    <td><c:out value="${album[1]}"/></td> 
                    <td><c:out value="${album[2]}"/></td> 
                    <td><c:out value="${album[3]}"/></td>
                </tr>
            </c:forEach>  
        </tbody>
    </table>
</div>