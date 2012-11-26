<%@page import="tools.Tools"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="<c:url value="do/upload" />" method="post" enctype="multipart/form-data">
    <fieldset>
        <legend>Envoi de fichier</legend>
        <label for="fichier">Fichier </label>
        <input type="file" id="fichier" name="file"  />               <br />
        <input type="submit" value="Envoyer" class="sansLabel" />            
    </fieldset>
</form>
