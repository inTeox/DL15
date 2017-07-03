<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Liste des chantiers</title>
        <img src="/DL14-master/images/logo.jpg">
        <H1>Liste des chantiers</H1>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/mycss2.css"/>" />  
    </head>
    <body>
        <br> </BR>
        <br> </BR>
        <c:if test="${ !empty form.resultat }"><p><c:out value="${ form.resultat }" /></p></c:if>
        <div id="corps">
        <c:choose>
            <%-- Si aucun chantier n'existe en session, affichage d'un message par défaut. --%>
            <c:when test="${ empty sessionScope.chantier }">
                <p class="erreur">Aucune chantier n'est enregistrée.</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>Nom</th>
                    <th>Propriétaire</th>
                    <th>Adresse</th>
                    <th>Jours prévus</th>
                    <th>Montant devis</th>
                    <th>début prévu</th>
                    <th>Fin prévue</th>
                    <th>Status</th>
                    <th>Jours réalisés</th>
                    <th>Montant total</th>
                    <th>début réel</th>
                    <th>Fin réelle</th>
                    <th class="action">Action</th>                    
                </tr>
                <%-- Parcours de la Map des personnes en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.chantier }" var="mapChantiers" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Perrsonne, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ mapChantiers.value.nomChantier }"/></td>
                    <td><c:out value="${ mapChantiers.value.personne.nom }"/></td>
                    <td><c:out value="${ mapChantiers.value.adresse.ligne1 }"/></td>
                    <td><c:out value="${ mapChantiers.value.nbrJourDevis }"/></td>
                    <td><c:out value="${ mapChantiers.value.mtInitial }"/></td>
                    <td><c:out value="${ mapChantiers.value.dateDebPrevu }"/></td>
                    <td><c:out value="${ mapChantiers.value.dateFinPrevu }"/></td>
                    <td><c:out value="${ mapChantiers.value.status }"/></td>
                    <td><c:out value="${ mapChantiers.value.nbrJourReal }"/></td>
                    <td><c:out value="${ mapChantiers.value.mtRealise }"/></td>
                    <td><c:out value="${ mapChantiers.value.dateDebReel }"/></td>
                    <td><c:out value="${ mapChantiers.value.dateFinReel }"/></td>
                   <%-- Lien vers la servlet de suppression, avec passage de la date de la commande - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>
                    <td class="action">
                        <a href="<c:url value="/SuppressionChantier"><c:param name="idChantier" value="${ mapChantiers.key }" /></c:url>">
                            <img src="<c:url value="/images/suppr.png"/>" alt="Supprimer" />
                        </a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="<c:url value="/ModifChantier"><c:param name="idChantierM" value="${ mapChantiers.key }" /></c:url>">
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
