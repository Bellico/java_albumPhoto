<%@page import="bdd.UserMap"%>
<%@page import="bean.UserBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Page index</h1>
<h1>${ok}</h1>

<form action="<c:url value="/acc.do" />" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>Envoi de fichier</legend>

                <label for="description">Description du fichier</label>
                <input type="text" id="description" name="description" value="<c:out value="${fichier.descr}"/>" />
                <span class="erreur">${form.erreurs['description']}</span>
                <br />
                

                <label for="fichier">Emplacement du fichier <span class="requis">*</span></label>
                <input type="file" id="fichier" name="fichier" value="<c:out value="${fichier.title}"/>" />
                <span class="erreur">${form.erreurs['fichier']}</span>
                <br />
                
                <input type="submit" value="Envoyer" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>        
            </fieldset>
        </form>