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
                <th>Titre</th>
                <td><c:out value="${album.nameAlbum}"/></td>
            </tr>		  
            <tr>
                <th>Description</th>
                <td><c:out value="${album.descr}"/></td>
            </tr>
            <tr>
                <th>Nombres de photo</th>
                <td><c:out value="${album.getNbPhoto()}"/></td>
            </tr> 
        </thead>
    </table>

    <span class="btn btn-${form.getResultType()}">${form.getResultMessage()}</span>

    <legend>Partager cet album avec d'autre personnes</legend>

    <form class="form-vertical" action="<c:url value="/partage/${album.idAlbum}"/>" method="post">

        <div class="control-group">
            <label class="control-label">Saisissez le login de l'utilisateur : </label> 
            <div class="controls">
                <input name="name" type="text" />
                <label class="control-label"> ou recherchez le dans la liste : </label> 
                <select name="nameSelect" type="text" />
                <option> </option>
                <c:forEach items="${listUsers}" var="users"  > 
                    <option value="${users.login}">${users.name} ${users.firstName}</option>
                </c:forEach>
                </select>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">Cette utilisateur pourra : </label> 
            <div class="controls">
                <label class="checkbox">
                    <input type="checkbox"  checked="checked" disabled="disabled" name="read" value="read">Voir le contenu de l'album, même si celui-ci est privé
                </label>
                <label class="checkbox">
                    <input type="checkbox" name="insert" value="insert">Insérer des images dans cet album
                </label>
                <label class="checkbox">
                    <input type="checkbox" name="update" value="update">Modifier les informations de l'album et les images qu'il contient
                </label>
                <label class="checkbox">
                    <input type="checkbox" name="delete" value="delete">Supprimer les images
                </label>
            </div>
        </div>

        <div class="control-group">
            <div class="controls">
                <button class="btn btn-primary" type="submit" class="btn"/>Partager !</button>        
            </div>
        </div>

    </form>

    <legend>Actuellement, cet album partagé avec ces personnes</legend>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Login</th>
                <th>Lecture</th>
                <th>Insertion</th>
                <th>Modication</th>
                <th>Suppression</th>
                <th>Options</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${collaborateurs}" var="col"  > 
                <tr>
                    <td class="partage"><c:out value="${col[0]}"/></td>
                    <td class="partage"><c:out value="${col[1]}"/></td>
                    <td class="partage"><c:out value="${col[2]}"/></td>
                    <td class="partage"><img class="img-rounded" alt="" src="<c:url value="/img/${col[3]}.png"/>" /></td>
                    <td class="partage"><img class="img-rounded" alt="" src="<c:url value="/img/${col[4]}.png"/>" /></td>
                    <td class="partage"><img class="img-rounded" alt="" src="<c:url value="/img/${col[5]}.png"/>" /></td>
                    <td class="partage"><img class="img-rounded" alt="" src="<c:url value="/img/${col[6]}.png"/>" /></td>
                    <td>
                        <p> <button class="btn btn-small btn-inverse" type="button">Modifier ses droits</button></p>
                        <p> <button class="btn btn-small btn-danger" type="button">Ne plus partager</button></p>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
