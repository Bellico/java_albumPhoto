<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="span10">
    <!--Sidebar content-->
    <ul class="nav nav-tabs">
        <li class="active"><a href="<c:url value="/index"/>"><i class="icon-home"></i> Accueil</a></li>
        <li><a href="<c:url value="/utilisateurs"/>"><i class="icon-user"></i> Utilisateurs</a></li>
        <li><a href="<c:url value="/albums"/>"><i class="icon-tag"></i> Albums</a></li>
        <li><a href="<c:url value="/photos"/>"><i class="icon-picture"></i> Photos</a></li>
    </ul>
</div>

<div class="span10"> 
      <h3>${page.messagePage}</h3>
    <form action="<c:url value="/albums/nouveau"/>" method="post">
        <fieldset>
            <legend>Formulaire d'ajout d'album</legend>
            Titre album : <input name="name" type="text" /> ${form.name}<br>
            Description : <textarea name="description" type="text" /></textarea> ${form.description}<br/>
            <button type="submit" class="btn"/>Creer</button></fieldset>
    </form>
</div>