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

    <span class="btn btn-${form.getResultType()}">${form.getResultMessage()}</span>

    <legend>Partager cet album à d'autre personnes</legend>

    <form class="form-horizontal" action="<c:url value="/partage"/>" method="post">

        <div class="control-group">
            <label class="control-label">Titre de la photo : </label> 
            <div class="controls">
                <input name="name" id="inputEmail" value="<c:out value="${form.getValue('name')}"/>" type="text" />
                <span class="label label-${form.getType("name")}">${form.getMessage("name")}</span>
            </div>
        </div>


        <div class="control-group">
            <label class="control-label">Utilisateur : </label> 
            <div class="controls">
                <select name="name" type="text" />
                <c:forEach items="${listAlbum}" var="al" >
                    <c:choose>
                        <c:when test="${form.getValue('name')==al.idAlbum}">
                            <option selected="selected" value="${al.idAlbum}">${al.nameAlbum}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${al.idAlbum}">${al.nameAlbum}</option>
                        </c:otherwise>  
                    </c:choose>
                </c:forEach>
                </select>
            </div>
        </div>

        <div class="control-group">
            <div class="controls">
          <label class="checkbox">
              <input type="checkbox" value="">Lire
          </label>
                </div>
        </div>



        <div class="control-group">
            <div class="controls">
                <button class="btn btn-primary" type="submit" class="btn"/>Partager !</button>        
            </div>
        </div>

    </form>

</div>
