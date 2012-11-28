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
        <li class="active"><a href="#"><i class="icon-user"></i> Utilisateurs</a></li>
        <li><a href="albums"><i class="icon-tag"></i> Albums</a></li>
        <li><a href="images"><i class="icon-picture"></i> Images</a></li>
    </ul>
</div>

<div class="span10"> 


    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Albums</th>
                <th>Images</th>
                <th>Membres depuis</th>

            </tr>
        </thead>

        <tbody>
            <c:forEach items="${User}" var="u">
                <tr>
                    <td > ${u.name}</td> 
                    <td > ${u.firstName}</td>
                    <td ></td>
                    <td >12</td>
                    <td >${u.date_created}</td>

                </tr>

            </c:forEach>  

        </tbody>
    </table>

</div>