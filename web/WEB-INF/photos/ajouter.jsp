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

    <span class="btn btn-${form.getResultType()}">${form.getResultMessage()}</span>

    <legend>Envoyer une nouvelle photo</legend>

    <form class="form-horizontal" action="<c:url value="/upload/up"/>" method="post" enctype="multipart/form-data">

        <div class="control-group">
            <label class="control-label">Titre de la photo : </label> 
            <div class="controls">
                <input name="titre" id="inputEmail" value="<c:out value="${form.getValue('titre')}"/>" type="text" />
                <span class="label label-${form.getType("titre")}">${form.getMessage("titre")}</span>
            </div>
        </div>


        <div class="control-group">
            <label class="control-label">Album : </label> 
            <div class="controls">
                <select name="album" type="text" />
                <c:forEach items="${listAlbum}" var="al" >
                    <c:choose>
                        <c:when test="${form.getValue('album')==al.idAlbum}">
                            <option selected="selected" value="${al.idAlbum}">${al.nameAlbum}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${al.idAlbum}">${al.nameAlbum}</option>
                        </c:otherwise>  
                    </c:choose>
                </c:forEach>
                </select>
                <a href="<c:url value="/albums/nouveau"/>"><button class="btn btn-small btn-inverse" type="button">Nouvel Album ?</button></a>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">Description : </label> 
            <div class="controls">
                <textarea name="description" type="text" /><c:out value="${form.getValue('description')}"/></textarea>
                <span class="label label-${form.getType("description")}">${form.getMessage("description")}</span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">Choisir une Photo : </label><div class="controls"><input type="file" id="fichier" name="file"  />         
            </div>
        </div>

        <div class="control-group">
            <div class="controls">
                <button class="btn btn-primary" type="submit" class="btn"/>Envoyer</button>        
            </div>
        </div>

    </form>

</div>