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
    <form  class="form-horizontal" method="post" action="<c:url value="/inscription/new"/>">
        <legend>Inscription d'un nouvel utilisateur</legend>
            <div class="control-group">
            <label class="control-label">Nom</label> 
            <div class="controls">
                <input id="for1" name="nom" value="<c:out value="${nom}"/>" type="text" /> ${form.nom}
            </div></div>   
           
            <div class="control-group">
            <label class="control-label">Prénom</label> 
            <div class="controls">
                <input id="for1" name="prenom" value="<c:out value="${prenom}" />" type="text" /> <c:out value="${form.prenom}" />
            </div></div>  
            
               <div class="control-group">
            <label class="control-label">Login</label> 
            <div class="controls">
                <input id="for1" name="login"  value="${requestScope.login}" type="text" /> ${form.login}
            </div></div>  
            
                <div class="control-group">
            <label class="control-label">Mot de passe</label> 
            <div class="controls">
                <input id="for1" name="pass"   value="${requestScope.pass}"type="password" /> 
            </div></div>  
                  
            <div class="control-group">
            <label class="control-label">Mot de passe confirmation</label> 
            <div class="controls">
                <input id="for1" name="passc"   value="${requestScope.passc}" type="password" /> ${form.passc}
            </div></div>

              <div class="control-group">
 
            <div class="controls">
               <input id="for1"  type="submit" value="Enregistrement"/>
            </div></div>
   
    </form>
</div>