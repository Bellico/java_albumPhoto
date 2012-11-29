<%-- 
    Document   : newalbum
    Created on : 29 nov. 2012, 00:38:53
    Author     : Emilien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <div class="span10">
                    <!--Sidebar content-->
                    <ul class="nav nav-tabs">
                        <li >
                            <a href="index"><i class="icon-home"></i> Accueil</a></li>
                        <li><a href="utilisateurs"><i class="icon-user"></i> Utilisateurs</a></li>
                        <li><a href="albums"><i class="icon-tag"></i> Albums</a></li>
                        <li><a href="images"><i class="icon-picture"></i> Images</a></li>
                    </ul>
                </div>

<div class="span10"> 
    <form action="do/" method="post">
        <fieldset>
    <legend>Formulaire d'ajout d'album</legend>
        Titre album : <input name="titre" type="text" /><br>
        Description : <textarea name="description" type="text" /></textarea><br/>
        <button type="submit" class="btn"/>Creer</button></fieldset>
    </form>
 </div>