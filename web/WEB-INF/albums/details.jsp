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
                <td><c:out value="${details.nameAlbum}"/></td>
            </tr>
            <tr>
                <th>Auteur</th>
                <td><c:out value="${details.userName}"/></td>
            </tr>		  
            <tr>
                <th>Description</th>
                <td><c:out value="${details.albumDescr}"/></td>
            </tr>
            <tr>
                <th>Nombre de photos</th>
                <td><c:out value="${details.nbPhoto}"/></td>
            </tr> 
            <tr>
                <th>Statut</th>
                <td><c:out value="${details.albumStatut}"/></td>
            </tr> 
        </thead>
    </table>
</div>

<div class="span10"> 
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Photo</th>
                <th>Auteur</th>
                <th>Album</th>
                <th>Titre</th>
                <th>Description</th>
                <th>Date de création</th>
                <th>Dernière mise à jour</th>
                <th>Options</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listImg}" var="img"  >
                <tr>
                    <td class="photocadre">
                        <a class="fancybox-thumb" rel="fancybox-thumb" href="<c:url value="/${img.url}"/>" title="<c:out value="${img.titlePhoto}"/>">
                            <img class="img-rounded" alt="" src="<c:url value="/${img.url}"/>">
                        </a>
                    </td>
                    <td class="utilisateurs"><c:out value="${img.userName}"/></td>
                    <td class="titrealbum"><c:out value="${img.nameAlbum}"/></td>
                    <td class="titrealbum"><c:out value="${img.titlePhoto}"/></td>
                    <td class="description"><c:out value="${img.PhotoDescr}"/></td>
                    <td class="dateajout"><c:out value="${img.PhotoDateCreated}"/> </td>
                    <td class="dateajout"><c:out value="${img.PhotoLastUp}"/> </td>
                    <td class="options">
                        <p> <a href="<c:url value="/photos/${img.idPhoto}"/>"><button class="btn btn-small btn-primary" type="button">Voir Détails</button></a> </p>
                        <c:if test="${!empty sessionScope.admin || (!empty sessionScope.user && sessionScope.user.idUser==details.idUser)}">
                            <p> <a href="<c:url value="modif/photo/${img.idPhoto}"/>"><button class="btn btn-small btn-inverse" type="button">Modifier</button></a> </p>
                            <p> <a href="<c:url value="/supp/photo/${img.idPhoto}"/>"><button class="btn btn-small btn-danger" type="button">Supprimer</button></a> </p>
                        </c:if>
                        <c:if test="${img.isUpdate=='1'}">
                            <p> <a href="<c:url value="modif/photo/${img.idPhoto}"/>"><button class="btn btn-small btn-inverse" type="button">Modifier</button></a> </p>
                        </c:if>
                        <c:if test="${img.isDelete=='1'}">
                            <p> <a href="<c:url value="/supp/photo/${img.idPhoto}"/>"><button class="btn btn-small btn-danger" type="button">Supprimer</button></a> </p>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>