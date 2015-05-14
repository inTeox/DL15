<%@ page contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <img src="/DL10/images/logo.jpg">
        <title>Création Client</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/mycss.css"/>" />
    </head>
    <body>
        <BR> </BR>
        <BR> </BR> 
        <form method="post" action="<c:url value="/Client"/>" >
        <c:if test="${ !empty form.resultat }"><p><c:out value="${ form.resultat }" /></p></c:if>
        <c:if test="${ !empty sessionScope.personnes }">
                    <div id="ancienPersonne">
                        <select name="listePersonnes" id="listePersonnes">
                            <option value="">Choisissez une entrée du carnet...</option>
                            <%-- Boucle sur la map des personnes --%>
                            <c:forEach items="${ sessionScope.personnes }" var="mapPersonnes">
                            <%--  L'expression EL ${mapPersonnes.value} permet de cibler l'objet Personne stocké en tant que valeur dans la Map, 
                                  et on cible ensuite simplement ses propriétés nom et prenom comme on le ferait avec n'importe quel bean. --%>
                            <option value="${ mapPersonnes.key }">${ mapPersonnes.value.prenom } ${ mapPersonnes.value.nom } </option>
                            </c:forEach>
                        </select>
                    </div>
        </c:if>
        <table border="0">
            <tbody>
                <tr>
                    <td><label for="idPersonne">N°</label> 
                    <input type="text" id="idClient" name="idPersonne" 
                           value="<c:out value="${client.idPersonne}"/>" size="10" />
                    <span class="erreur">${form.erreurs['idPersonne']}</span><BR> </BR>                   
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><label for="choixGroupeClient"> </label>
                    <input type="radio" id="choixGroupeClient" name="choixGroupeClient" value="prospect" checked /> prospect
                    <input type="radio" id="choixGroupeClient" name="choixGroupeClient" value="client" /> client
                    </td>
                </tr>
                <tr>
                    <td><label for="fax">Fax </label> 
                    <input type="text" id="fax" name="fax" 
                           value="<c:out value="${client.fax}"/>" size="10" maxlength="10" />
                    <span class="erreur">${form.erreurs['fax']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><label for="telephone">Autre téléphone </label> 
                    <input type="text" id="telephone" name="telephone" 
                           value="<c:out value="${client.telephone}"/>" size="10" maxlength="10" />
                    <span class="erreur">${form.erreurs['telephone']}</span> </td>
                </tr>
                <tr>
                    <td><label for="note">Note </label> 
                    <input type="text" id="note" name="note" 
                           value="<c:out value="${client.note}"/>" size="30" maxlength="500" />
                    <span class="erreur">${form.erreurs['note']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td></td>
                </tr>
                <BR> </BR>
            </tbody>
        </table>       
        
                <BR> </BR>
                <BR> </BR>      
        <input type="submit" value="VALIDEZ" name="doIt" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="<c:url value="/CreationCarnetAdresses"/>">retour écran précédent</a>
        </form>
    </body>
    <c:import url="/inc/inc_bas_page.jsp" />
</html>
