<%@ page contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <img src="/DL14-master/images/logo.jpg">
        <title>Création d'un contrat</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/mycss.css"/>" />
    </head>
    <body>
        <H1>Création d'un contrat</H1>
        <c:if test="${ !empty form.resultat }"><p><c:out value="${ form.resultat }" /></p></c:if>
        <form method="post" action="<c:url value="/CreationContrat"/>" >
        <table border="0">
            <tbody>
                <tr>
                    <td><label for="idContrat">N° &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="idContrat" name="idContrat" 
                           value="<c:out value="${contrat.idContrat}"/>" size="10" />
                    <span class="erreur">${form.erreurs['idContrat']}</span><BR> </BR>                   
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td></td>
                </tr>
                <tr>
                    <td><label for="nom">Nom  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="nom" name="nom" 
                           value="<c:out value="${contrat.employe.nom}"/>" size="30" maxlength="50" />
                    <span class="erreur">${form.erreurs['nom']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td> <select name="listeEmployes" id="listeEmployes">
                            <option value="">Choisissez un employe...</option>
                            <%-- Boucle sur la map des employés --%>
                            <c:forEach items="${ sessionScope.employes }" var="mapEmployes">
                            <option value="${ mapEmployes.key }">${ mapEmployes.value.prenom } ${ mapEmployes.value.nom }</option>
                            </c:forEach>
                         </select>  </td>
                </tr>
                <BR> </BR>
            </tbody>
        </table>       
        <table border="0">
            <tbody>
                <tr>
                    <td><label for="dateDebContrat">date de début <span class="requis">*</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="dateDebContrat" name="dateDebContrat" 
                           value="<c:out value="${contrat.dateDebContrat}"/>" size="10" maxlength="10"/>
                    <span class="erreur">${form.erreurs['dateDebContrat']}</span>                 
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><label for="dateFinContrat">date de fin de contrat </label> 
                    <input type="text" id="dateFinContrat" name="dateFinContrat" 
                           value="<c:out value="${contrat.dateFinContrat}"/>" size="10" maxlength="10"/>
                    <span class="erreur">${form.erreurs['dateFinContrat']}</span>                   
                    </td>
                </tr>
                <tr>
                    <td><label for="remunerationHoraire">rémunération horaire &nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="remunerationHoraire" name="remunerationHoraire" 
                           value="<c:out value="${contrat.remunerationHoraire}"/>" size="18" maxlength="15" />
                    <span class="erreur">${form.erreurs['remunerationHoraire']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>                  
                    </td>
                </tr>
                <tr>
                    <td><label for="poste">poste &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="poste" name="poste" 
                           value="<c:out value="${contrat.poste}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['dateDebPrevu']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                    <label for="qualif">qualification &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="qualif" name="qualif" 
                           value="<c:out value="${contrat.qualif}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['qualif']}</span>                  
                    </td>
                </tr>
            </tbody>
        </table>
                <BR> </BR>
                <BR> </BR>      
        <input type="submit" value="VALIDEZ" name="doIt" />
        </form>
    </body>
    <c:import url="/inc/inc_bas_page.jsp" />
</html>
