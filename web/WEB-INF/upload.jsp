<%@page import="tools.Tools"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    <form action="<c:url value="/upload/up"/>" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Envoi de fichier</legend>
            Titre Photo: <input name="titre" type="text" />  ${form.titre} <br>
            Album : <input name="album" type="text" value="album1" /> ${form.album} <br>
            Description : <textarea name="description" type="text" /></textarea> ${form.description}<br/>
            Fichier : <input type="file" id="fichier" name="file"  />            <br />
            <button type="submit" class="btn"/>Envoyer</button>        
        </fieldset>
    </form>
</div>