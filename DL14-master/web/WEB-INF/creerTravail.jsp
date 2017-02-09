<%@ page contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <H1>Saisie hebdomadaire des temps</H1>
        <c:if test="${ !empty form.resultat }"><p><c:out value="${ form.resultat }" /></p></c:if>
        <form method="post" action="<c:url value="/CreationTravail"/>" >
        
        <table border="0">
            <tbody>
                <tr>
                    <td> <select name="listeChantiers" id="listeChantiers">
                            <option value="">Choisissez un chantier...</option>
                            <%-- Boucle sur la map des chantier --%>
                            <c:forEach items="${ sessionScope.chantiers }" var="mapChantiers">
                            <option value="${ mapChantiers.key }">${ mapChantiers.value.personne.nom } - ${ mapChantiers.value.nomChantier }</option>
                            </c:forEach>
                         </select>  
                    </td>
                    <td><label for="from">Semaine du :</label>
                        <input type="text" id="from" name="from">
                    <span class="erreur">${form.erreurs['from']}</span></td>
                </tr>
            </tbody>
        </table>
        <table border="1">        
            <tbody> 
                <tr>
                    <td rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td colspan="2"><H3> LUNDI </H3></td>
                    <td colspan="2"><H3> MARDI </H3></td>
                    <td colspan="2"><H3> MERCREDI </H3></td>
                    <td colspan="2"><H3> JEUDI </H3></td>
                    <td colspan="2"><H3> VENDREDI </H3></td>
                </tr>
                <tr>
                    <td colspan="2"><H3> AM   -    PM </H3></td>
                    <td colspan="2"><H3> AM   -    PM </H3></td>
                    <td colspan="2"><H3> AM   -    PM </H3></td>
                    <td colspan="2"><H3> AM   -    PM </H3></td>
                    <td colspan="2"><H3> AM   -    PM </H3></td>
                </tr>    
                <tr>
                    <td> <select name="listeEmployes" id="listeEmployes">
                            <option value="">Choisissez un employe...</option>
                            <option value="ALL">Equipe de base</option>
                            <%-- Boucle sur la map des chantier --%>
                            <c:forEach items="${ sessionScope.employes }" var="mapEmployes">
                            <option value="${ mapEmployes.key }">${ mapEmployes.value.nom }  ${ mapEmployes.value.prenom }</option>
                            </c:forEach>
                         </select>  
                    </td>
                    <td><input type="checkbox" name="Luam" value="ON" checked="checked" /></td>
                    <td><input type="checkbox" name="Lupm" value="ON" checked="checked" /></td>
                    <td><input type="checkbox" name="Maam" value="ON" checked="checked" /></td>
                    <td><input type="checkbox" name="Mapm" value="ON" checked="checked" /></td>
                    <td><input type="checkbox" name="Meam" value="ON" checked="checked" /></td>
                    <td><input type="checkbox" name="Mepm" value="ON" checked="checked" /></td>
                    <td><input type="checkbox" name="Jeam" value="ON" checked="checked" /></td>
                    <td><input type="checkbox" name="Jepm" value="ON" checked="checked" /></td>
                    <td><input type="checkbox" name="Veam" value="ON" checked="checked" /></td>
                    <td><input type="checkbox" name="Vepm" value="ON" checked="checked" /></td>
                </tr>
                <BR> </BR>
            </tbody>
        </table>             
        <input type="submit" value="VALIDEZ" name="doIt" />
        </form>
    </body>
    <c:import url="/inc/inc_bas_page.jsp" />
</html>
