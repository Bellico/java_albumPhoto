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
    <h3>${page.messagePage}</h3>
    <form method="post" action="<c:url value="/inscription/new"/>">
        <table  border="0">	   
            <tr>
                <td> <label for="for1"> nom </label> </td>
                <td> <input id="for1" name="nom" value="<c:out value="${nom}"/>" type="text" /> </td> <td>${form.nom}</td>
            </tr>

            <tr>
                <td> <label for="for2"> prenom </label> </td>
                <td> <input id="for1" name="prenom" value="<c:out value="${prenom}" />" type="text" /> </td> <td><c:out value="${form.prenom}" /></td>
            </tr>

            <tr>
                <td> <label for="for2"> login</label> </td>
                <td> <input id="for1" name="login"  value="${requestScope.login}" type="text" /> </td> <td>${form.login}</td>
            </tr>
            <tr>
                <td> <label for="for2">pass </label> </td>
                <td> <input id="for1" name="pass"   value="${requestScope.pass}"type="text" /> </td>
            </tr>
            <tr>
                <td> <label for="for2"> pass conform </label> </td>
                <td> <input id="for1" name="passc"   value="${requestScope.passc}" type="text" /> </td> <td>${form.passc}</td>
            </tr>
            <tr>
                <td> <label for="for2"> tok </label> </td>
                <td>  <input id="for1"  type="submit" /> </td>
            </tr>

        </table>
    </form>
</div>