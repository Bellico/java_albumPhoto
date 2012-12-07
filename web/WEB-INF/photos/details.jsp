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
                        <a class="fancybox-thumb" rel="fancybox-thumb" href="<c:url value="/${details[0]}"/>" title="<c:out value="${details[3]}"/>">
                            <img alt="" src="<c:url value="/${details[0]}"/>">
                        </a>
                    </td>
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
                <td><c:out value="${details[3]}"/></td>
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
