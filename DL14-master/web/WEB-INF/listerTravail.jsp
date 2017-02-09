<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="utf-8" />
        <img src="/DL14/images/logo.jpg">
        <title>Saisie hebdomadaire des temps</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/mycss.css"/>" />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <div id="datepicker"></div>
 
        <script>
            $(function() {
            
            $( "#from" ).datepicker({
                defaultDate: "+1w",
                changeMonth: true,
                numberOfMonths: 1,
                dateFormat: "dd-mm-yy",
                showWeek: true,
                closeText: 'Fermer',
                prevText: 'Précédent',
                nextText: 'Suivant',
                currentText: 'Aujourd\'hui',
                monthNames: ['janvier', 'février', 'mars', 'avril', 'mai', 'juin',
                    'juillet', 'août', 'septembre', 'octobre', 'novembre', 'décembre'],
                monthNamesShort: ['janv.', 'févr.', 'mars', 'avr.', 'mai', 'juin',
                    'juil.', 'août', 'sept.', 'oct.', 'nov.', 'déc.'],
                dayNames: ['dimanche', 'lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi'],
                dayNamesShort: ['dim.', 'lun.', 'mar.', 'mer.', 'jeu.', 'ven.', 'sam.'],
                dayNamesMin: ['D','L','M','M','J','V','S'],
                weekHeader: 'Sem.',
                onClose: function( selectedDate ) {
            $( "#from" ).datepicker( "option", "minDate", selectedDate );
            }
            });
            });
        </script>
    </head>
    <body>
    <H1>Planning</H1>
    
    
        <c:if test="${ !empty form.resultat }"><p><c:out value="${ form.resultat }" /></p></c:if>
        
        <div id="corps">
        <c:choose>
            <%-- Si aucune entrée dans la table travail n'existe en session, affichage d'un message par défaut. --%>
            <c:when test="${ empty sessionScope.travail }">
                <p class="erreur">pas de travail en base de données.</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>N°</th>
                    <th>Nom prénom</th>
                    <th>tel.mobile</th>
                    <th COLSPAN=2 ROWSPAN=1 >date planning</th>
                    <th class="action">Action</th>                    
                </tr>
                 <tr>
                    <th> </th>
                    <th> </th>
                    <th> </th>
                    <th>am</th>
                    <th>pm</th>                    
                </tr>
                <%-- Parcours de la Map des travail en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.travail }" var="mapTravaux" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Perrsonne, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ mapTravaux.value.idContrat }"/></td>
                    <td><c:out value="${ mapTravaux.value.employe.nom } ${ mapTravaux.value.employe.prenom }"/></td>
                    <td><c:out value="${ mapTravaux.value.chantier.nomchantier }"/></td>
                    <td><c:out value="${ mapTravaux.value.dateplanning }"/></td>
                    <td><c:out value="${ mapTravaux.value.presenceAmPlanning }"/></td>
                    <td><c:out value="${ mapTravaux.value.presencePmPlanning }"/></td>
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