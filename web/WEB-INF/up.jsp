<%@page import="tools.Tools"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <div class="span10">
                    <!--Sidebar content-->
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#"><i class="icon-home"></i> Accueil</a></li>
                        <li><a href="utilisateurs"><i class="icon-user"></i> Utilisateurs</a></li>
                        <li><a href="#"><i class="icon-tag"></i> Albums</a></li>
                        <li><a href="images"><i class="icon-picture"></i> Images</a></li>
                    </ul>
                </div>


<form action="<c:url value="do/upload" />" method="post" enctype="multipart/form-data">
    <fieldset>
        <legend>Envoi de fichier</legend>
        <label for="fichier">Fichier </label>
        <input type="file" id="fichier" name="file"  />               <br />
        <input type="submit" value="Envoyer" class="sansLabel" />            
    </fieldset>
</form>
