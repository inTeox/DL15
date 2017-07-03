<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <img src="/DL14-master/images/logo.jpg">
        <H1>Liste des contrats</H1>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/mycss2.css"/>" />
    </head>
    <body>
        <c:if test="${ !empty form.resultat }"><p><c:out value="${ form.resultat }" /></p></c:if>
        <div id="corps">
        <c:choose>
            <%-- Si aucune entrée dans lecarnet n'existe en session, affichage d'un message par défaut. --%>
            <c:when test="${ empty sessionScope.contrat }">
                <p class="erreur">Aucune contrat n'est enregistrée.</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>N°</th>
                    <th>Nom prénom</th>
                    <th>Poste</th>
                    <th>qualification</th>
                    <th>rémunération horaire</th>
                    <th>Début de contrat</th>
                    <th>Fin de contrat</th>
                    <th>Fonction</th>
                    <th>Expérience</th>
                    <th class="action">Action</th>                    
                </tr>
                <%-- Parcours de la Map des personnes en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.contrat }" var="mapContrats" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Perrsonne, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ mapContrats.value.idContrat }"/></td>
                    <td><c:out value="${ mapContrats.value.employe.nom } ${ mapContrats.value.employe.prenom }"/></td>
                    <td><c:out value="${ mapContrats.value.poste }"/></td>
                    <td><c:out value="${ mapContrats.value.qualif }"/></td>
                    <td><c:out value="${ mapContrats.value.remunerationHoraire }"/></td>
                    <td><c:out value="${ mapContrats.value.dateDebContrat }"/></td>
                    <td><c:out value="${ mapContrats.value.dateFinContrat }"/></td>
                    <td><c:out value="${ mapContrats.value.employe.fonction }"/></td>
                    <td><c:out value="${ mapContrats.value.employe.experience }"/></td>
                   <%-- Lien vers la servlet de suppression, avec passage de l'identifiant du contrat - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>
                    <td class="action">
                        <a href="<c:url value="/SuppressionContrat"><c:param name="idContrat" value="${ mapContrats.key }" /></c:url>">
                            <img src="<c:url value="/images/suppr.png"/>" alt="Supprimer" />
                        </a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="<c:url value="/ModifContrat"><c:param name="idContratM" value="${ mapContrats.key }" /></c:url>">
                            <img src="<c:url value="/images/crayon2.jpg"/>" alt="Modifier" />
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
    </div>  
        <c:import url="/inc/inc_bas_page.jsp" />
    </body>
</html>