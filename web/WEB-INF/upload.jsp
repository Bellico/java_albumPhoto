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
    <legend>Envoyer une photo</legend>

    <form class="form-horizontal" action="<c:url value="/upload/up"/>" method="post" enctype="multipart/form-data">
        <div class="control-group">
            <label class="control-label">Titre Photo</label> 
            <div class="controls">
                <input name="titre" id="inputEmail" type="text" />  ${form.titre} 
            </div>
        </div>


        <div class="control-group">

            <label class="control-label">Album</label> 
            <div class="controls">
                <select name="album" type="text" value="album1" />
                <option value="album1">album1</option>
                <option value="album2">album2</option>
                </select> ${form.album} 
            </div></div>

        <div class="control-group">
            <label class="control-label">Description</label> 
            <div class="controls">
                <textarea name="description" type="text" /></textarea> ${form.description}
            </div></div>
        <div class="control-group">
            <label class="control-label">Choisir un fichier</label><div class="controls"><input type="file" id="fichier" name="file"  />         
            </div></div>
        <div class="control-group">
            <div class="controls">
                <button type="submit" class="btn"/>Envoyer</button>        
            </div>  </div>
    </form>

</div>