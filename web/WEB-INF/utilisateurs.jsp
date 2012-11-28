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
        <li><a href="#"><i class="icon-tag"></i> Albums</a></li>
        <li><a href="#"><i class="icon-picture"></i> Images</a></li>
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
        
        <% UserMap p = new UserMap();
   ArrayList<UserBean> tab= p.getAll();
    
   for (UserBean user : tab)
          {
       
       
          %> 
        <tbody>
            <tr>
                
                <td ><% out.println(user.getName()); %></td>
                <td ><% out.println(user.getFirstName()); %></td>
                <td ></td>
                <td >12</td>
                <td ><% out.println(user.getDate_created()); %></td>
        
            </tr>
                     <%   
                         }

%>
        </tbody>
    </table>

</div>