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

<div class="span4">
    <legend>Vous modifez cette photo</legend>
    <a class="fancybox-thumb" rel="fancybox-thumb" href="<c:url value="/${photo.img}"/>" title="<c:out value="${photo.title}"/>">
        <img class="img-rounded" alt="" src="<c:url value="/${photo.img}"/>">
    </a>
</div>
    
<div class="span4">

    <span class="btn btn-${form.getResultType()}">${form.getResultMessage()}</span>

    <legend>Saisisez vos nouvelles données</legend>

    <form class="form-vertical" action="<c:url value="/modif/photo/${photo.idPhoto}"/>" method="post">

        <div class="control-group">
            <label class="control-label">Titre de la photo : </label> 
            <div class="controls">
                <input name="titre" id="inputEmail" value="<c:out value="${form.getValue('titre')}"/>" type="text" />
                <span class="label label-${form.getType("titre")}">${form.getMessage("titre")}</span>
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
            <div class="controls">
                <button class="btn btn-primary" type="submit" class="btn"/>Modifier</button>        
            </div>
        </div>

    </form>

</div>

