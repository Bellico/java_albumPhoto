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

    <span class="btn btn-${form.getResultType()}">${form.getResultMessage()}</span>

    <legend>Inscription d'un nouvel utilisateur</legend>

    <form class="form-horizontal" method="post" action="<c:url value="/inscription"/>">

        <div class="control-group">
            <label class="control-label">Nom : </label> 
            <div class="controls">
                <input name="nom" value="<c:out value="${form.getValue('nom')}"/>" type="text" />
                <span class="label label-${form.getType("nom")}">${form.getMessage("nom")}</span>
            </div>
        </div>   

        <div class="control-group">
            <label class="control-label">Prénom : </label> 
            <div class="controls">
                <input name="prenom" value="<c:out value="${form.getValue('prenom')}" />" type="text" />
                <span class="label label-${form.getType("prenom")}">${form.getMessage("prenom")}</span>
            </div>
        </div>  

        <div class="control-group">
            <label class="control-label">Login : </label> 
            <div class="controls">
                <input name="login" value="<c:out value="${form.getValue('login')}" />" type="text" /> 
                <span class="label label-${form.getType("login")}">${form.getMessage("login")}</span>
            </div>
        </div>  

        <div class="control-group">
            <label class="control-label">Mot de passe : </label> 
            <div class="controls">
                <input name="pass" value="<c:out value="${form.getValue('pass')}" />"type="password" />
                <span class="label label-${form.getType("pass")}">${form.getMessage("pass")}</span>
            </div>
        </div>  

        <div class="control-group">
            <label class="control-label">Mot de passe de confirmation : </label> 
            <div class="controls">
                <input name="passc" type="password" />
                <span class="label label-${form.getType("passc")}">${form.getMessage("passc")}</span>
            </div>
        </div>

        <div class="control-group">
            <div class="controls">
                <input class="btn btn-primary" type="submit" value="Enregistrement"/>
            </div>
        </div>
    </form>

</div>

