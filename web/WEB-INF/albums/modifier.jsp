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

    <legend>Saissisez vos nouvelles données</legend>

    <form  class="form-horizontal" action="<c:url value="/modif/album/${album.idAlbum}"/>" method="post">

        <div class="control-group">
            <label class="control-label">Titre Album : </label> 
            <div class="controls">
                <input name="name" value="<c:out value="${form.getValue('name')}"/>" type="text" />
                <span class="label label-${form.getType("name")}">${form.getMessage("name")}</span>
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
            <label class="control-label">Déclaré cet album comme étant : </label> 
            <div class="controls">
                <select name="statut" />
                <c:choose>
                    <c:when test="${empty form.getValue('statut') || form.getValue('statut')==0}">
                        <option selected="selected" value="0">Public</option>
                        <option value="1">Privé</option>
                    </c:when>
                    <c:otherwise>
                        <option value="0">Public</option>
                        <option selected="selected" value="1">Privé</option>
                    </c:otherwise>  
                </c:choose>
                </select>
            </div>
        </div>

        <div class="control-group">
            <div class="controls">
                <button class="btn btn-primary" type="submit"/>Modifier</button>
            </div>
        </div>

    </form

</div>

