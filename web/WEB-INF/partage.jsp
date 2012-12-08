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

    <span class="btn btn-${form.getResultType()}"><c:out value="${form.getResultMessage()}"/></span>

    <legend>Partager cet album avec d'autre personnes</legend>

    <form class="form-vertical" action="<c:url value="/partage/${album.idAlbum}"/>" method="post">

        <div class="control-group">
            <c:choose>
                <c:when test="${!empty param.modif}">
                    <h5> Login de l'utilisateur : <c:out value="${param.modif}"/> </h5>  
                    <input name="name" value="<c:out value="${param.modif}"/>" type="hidden" />
                </c:when>
                <c:otherwise>
                    <label class="control-label">Saisissez le login de l'utilisateur : </label> 
                    <div class="controls">
                        <input name="name" type="text" />
                        <label class="control-label"> ou recherchez le dans la liste : </label> 
                        <select name="nameSelect" type="text" />
                        <option> </option>
                        <c:forEach items="${listUsers}" var="users"  > 
                            <option value="<c:out value="${users.login}"/>"><c:out value="${users.name}"/>  <c:out value="${users.firstName}"/></option>
                        </c:forEach>
                        </select>
                    </div>
                </c:otherwise>  
            </c:choose>
        </div>

        <div class="control-group">
            <label class="control-label">Cette utilisateur pourra : </label> 
            <div class="controls">
                <label class="checkbox">
                    <input type="checkbox"  checked="checked" disabled="disabled" name="read" value="read">Voir le contenu de l'album, même si celui-ci est privé
                </label>
                <label class="checkbox">
                    <input type="checkbox" ${param.insert==1 ?  'checked="checked"' : "" } name="insert" value="insert">Insérer des images dans cet album
                </label>
                <label class="checkbox">
                    <input type="checkbox" ${param.update==1 ?  'checked="checked"' : "" } name="update" value="update">Modifier les informations de l'album et les images qu'il contient
                </label>
                <label class="checkbox">
                    <input type="checkbox" ${param.delete==1 ?  'checked="checked"' : "" } name="delete" value="delete">Supprimer les images
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

    <table class="tableright table table-bordered">
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
                    <td class="partage"><img class="img-rounded" alt="" src="<c:url value="/img/"/>${col[3]=="1" ?  "validate" : "error" }.png" /></td>
                    <td class="partage"><img class="img-rounded" alt="" src="<c:url value="/img/"/>${col[4]=="1" ?  "validate" : "error" }.png" /></td>
                    <td class="partage"><img class="img-rounded" alt="" src="<c:url value="/img/"/>${col[5]=="1" ?  "validate" : "error" }.png" /></td>
                    <td class="partage"><img class="img-rounded" alt="" src="<c:url value="/img/"/>${col[6]=="1" ?  "validate" : "error" }.png" /></td>
                    <td>
                        <p> <a href="<c:url value="/partage/${album.idAlbum}?modif=${col[2]}&insert=${col[4]}&update=${col[5]}&delete=${col[6]}"/>"><button class="btn btn-small btn-inverse" type="button">Modifier ses droits</button></a></p>
                        <p> <button class="btn btn-small btn-danger" type="button">Ne plus partager</button></p>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
