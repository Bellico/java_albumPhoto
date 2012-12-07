<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="nav nav-tabs nav-stacked">
    <li>
        <a>
            <div class="formulaire">
                <span class="label label-${form.getResultType()}">${form.getResultMessage()}</span>
                <form action="<c:url value="/connexion"/>" method="post">
                    Nom : <input name="login" type="text" value="<c:out value="${form.getValue('login')}"/>" id="inputEmail" /><br>
                    Mot de passe : <input name="password" value="<c:out value="${form.getValue('password')}"/>" type="password" id="inputPassword" />
                    <button type="submit" class="btn btn-primary"/>Connexion</button>
                </form>
            </div>
        </a>
    </li>
    <li><a href="<c:url value="/inscription"/>"><i class="icon-plus"></i> Nouvel Utilisateur</a></li>
</ul>

