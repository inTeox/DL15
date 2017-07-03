<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <img src="/DL14-master/images/logo.jpg">
        <H1>Liste du carnet</H1>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/mycss2.css"/>" />
    </head>
    <body>
        <br> </BR>
        <c:if test="${ !empty form.resultat }"><p><c:out value="${ form.resultat }" /></p></c:if>
        <div id="corps">
        <c:choose>
            <%-- Si aucune entrée dans lecarnet n'existe en session, affichage d'un message par défaut. --%>
            <c:when test="${ empty sessionScope.carnet }">
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
                    <th>Adresse</th>
                    <th>C.P.</th>
                    <th>Expérience</th>
                    <th>Fonction</th>
                    <th class="action">Action</th>                    
                </tr>
                <%-- Parcours de la Map des personnes en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.carnet }" var="mapPersonnes" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Perrsonne, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ mapPersonnes.value.nom }"/></td>
                    <td><c:out value="${ mapPersonnes.value.prenom }"/></td>
                    <td><c:out value="${ mapPersonnes.value.telephone }"/></td>
                    <td><c:out value="${ mapPersonnes.value.mobile }"/></td>
                    <td><c:out value="${ mapPersonnes.value.mail }"/></td>
                    <td><c:out value="${ mapPersonnes.value.noteP }"/></td>
                    <td><c:out value="${ mapPersonnes.value.dateDebutP }"/></td>
                    <td><c:out value="${ mapPersonnes.value.type }"/></td>
                    <td><c:out value="${ mapPersonnes.value.adresse.ligne1 }"/></td>
                    <td><c:out value="${ mapPersonnes.value.adresse.cp }"/></td>
                    <td><c:out value="${ mapPersonnes.value.experience }"/></td>
                    <td><c:out value="${ mapPersonnes.value.fonction }"/></td>
                   <%-- Lien vers la servlet de suppression, avec passage de la date de la commande - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>
                    <td class="action">
                        <a href="<c:url value="/SuppressionPersonne"><c:param name="idPersonne" value="${ mapPersonnes.key }" /></c:url>">
                            <img src="<c:url value="/images/suppr.png"/>" alt="Supprimer" />
                        </a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="<c:url value="/ModifPersonne"><c:param name="idPersonneM" value="${ mapPersonnes.key }" /></c:url>">
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