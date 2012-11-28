<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


       <ul class="nav nav-tabs nav-stacked">
                        <li>
                            <a><div class="formulaire">
                                    <form action="do/connexion" method="post">
                                        Nom : <input name="name" type="text" id="inputEmail" /><br>
                                        Mot de passe : <input name="password" type="password" id="inputPassword" />
                                        <button type="submit" class="btn"/>Connexion</button>
                                    </form>
                                </div></a>
                        </li>
                        <li><a href="#"><i class="icon-plus"></i> Nouvel Utilisateur</a></li>
                    </ul>

