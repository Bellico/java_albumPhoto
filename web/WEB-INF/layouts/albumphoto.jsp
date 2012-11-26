<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/css/styles.css"/>" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="well">
            <div id="titre">AlbumPhoto</div>
        </div>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span2">
                    <div class="well" id="titre2">
                        Accueil
                    </div>
                    <ul class="nav nav-tabs nav-stacked">
                        <li>
                            <a><div class="formulaire">
                                    <form>
                                        Nom : <input type="text" id="inputEmail" /><br>
                                        Mot de passe : <input type="password" id="inputPassword" />
                                        <button type="submit" class="btn"/>Connexion</button>
                                    </form>
                                </div></a>
                        </li>
                        <li><a href="#"><i class="icon-plus"></i> Nouvel Utilisateur</a></li>
                    </ul>

                </div>
                <div class="span10">
                    <!--Sidebar content-->
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#"><i class="icon-home"></i> Accueil</a></li>
                        <li><a href="#"><i class="icon-user"></i> Utilisateurs</a></li>
                        <li><a href="#"><i class="icon-tag"></i> Albums</a></li>
                        <li><a href="images.html"><i class="icon-picture"></i> Images</a></li>
                    </ul>
                </div>

                <div class="span10"> 
                    <!--Body content-->
                    <jsp:include page="/WEB-INF/${view}" />
                </div>
            </div>
        </div>


    </body>
</html>
