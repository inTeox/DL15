<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <img src="/DL14/images/logo.jpg">
        <title>Liste du carnet</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/mycss.css"/>" />
    </head>
<body>
        <div id="corps">
        <c:choose>
            <%-- Si aucune entrée dans lecarnet n'existe en session, affichage d'un message par défaut. --%>
            <c:when test="${ empty sessionScope.personnes }">
                <p class="erreur">Aucune entrée dans le carnet n'est enregistrée.</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>Nom</th>
                    <th>Prenom</th>
                    <th>Téléphone</th>
                    <th>Autre téléphone</th>
                    <th>Email</th>
                    <th>Notes</th>
                    <th>Date de création</th>
                    <th>Type</th>
                    <th class="action">Action</th>                    
                </tr>
                <%-- Parcours de la Map des personnes en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.personnes }" var="mapPersonnes" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Perrsonne, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ mapPersonnes.value.nom }"/></td>
                    <td><c:out value="${ mapPersonnes.value.prenom }"/></td>
                    <td><c:out value="${ mapPersonnes.value.telephone }"/></td>
                    <td><c:out value="${ mapPersonnes.value.mobile }"/></td>
                    <td><c:out value="${ mapPersonnes.value.email }"/></td>
                    <td><c:out value="${ mapPersonnes.value.note }"/></td>
                    <td><joda:format value="${ mapPersonnes.value.dateDebutP }" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td><c:out value="${ mapPersonne.value.type }"/></td>
                   <%-- Lien vers la servlet de suppression, avec passage de la date de la commande - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>
                    <td class="action">
                        <a href="<c:url value="/SuppressionPersonne"><c:param name="idPersonne" value="${ mapPersonnes.key }" /></c:url>">
                            <img src="<c:url value="/DL14/images/suppr.png"/>" alt="Supprimer" />
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>