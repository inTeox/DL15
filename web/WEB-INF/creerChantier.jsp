<%@ page contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <img src="/DL10/images/logo.jpg">
        <title>Création d'un chantier</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/mycss.css"/>" />
    </head>
    <body>
        <H1>Création d'un chantier</H1>
        <c:if test="${ !empty form.resultat }"><p><c:out value="${ form.resultat }" /></p></c:if>
        <form method="post" action="<c:url value="/CreationChantier"/>" >
        <table border="0">
            <tbody>
                <tr>
                    <td><label for="idChantier">N° &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="idChantier" name="idChantier" 
                           value="<c:out value="${chantier.idChantier}"/>" size="10" />
                    <span class="erreur">${form.erreurs['idChantier']}</span><BR> </BR>                   
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td></td>
                </tr>
                <tr>
                    <td><label for="nomChantier">Nom <span class="requis">*</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="nomChantier" name="nomChantier" 
                           value="<c:out value="${chantier.nomChantier}"/>" size="30" maxlength="50" />
                    <span class="erreur">${form.erreurs['nomChantier']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td> <select name="listePersonnes" id="listePersonnes">
                            <option value="">Choisissez un client...</option>
                            <%-- Boucle sur la map des proprietaires --%>
                            <c:forEach items="${ sessionScope.personnes }" var="mapProprietaires">
                            <option value="${ mapProprietaires.key }">${ mapProprietaires.value.prenom } ${ mapProprietaires.value.nom }</option>
                            </c:forEach>
                         </select>  </td>
                </tr>
                <BR> </BR>
            </tbody>
        </table>       
        <c:import url="/inc/inc_adresse_form.jsp" />
        <table border="0">
            <tbody>
                <tr>
                    <td><label for="nbrJourDevis">jours devis <span class="requis">*</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="nbrJourDevis" name="nbrJourDevis" 
                           value="<c:out value="${chantier.nbrJourDevis}"/>" size="10" maxlength="10"/>
                    <span class="erreur">${form.erreurs['nbrJourDevis']}</span>                 
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><label for="nbrJourReal">jour réalisé &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="nbrJourReal" name="nbrJourReal" 
                           value="<c:out value="${chantier.nbrJourReal}"/>" size="18" maxlength="15"/>
                    <span class="erreur">${form.erreurs['nbrJourReal']}</span>                   
                    </td>
                </tr>
                <tr>
                    <td><label for="mtInitial">montant devis &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="mtInitial" name="mtInitial" 
                           value="<c:out value="${chantier.mtInitial}"/>" size="18" maxlength="15" />
                    <span class="erreur">${form.erreurs['mtInitial']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                    <label for="mtRealise">montant total &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="mtRealise" name="mtRealise" 
                           value="<c:out value="${chantier.mtRealise}"/>" size="18" maxlength="15" />
                    <span class="erreur">${form.erreurs['mtRealise']}</span>                  
                    </td>
                </tr>
                <tr>
                    <td><label for="dateDebPrevu">date début prévue &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="dateDebPrevu" name="dateDebPrevu" 
                           value="<c:out value="${chantier.dateDebPrevu}"/>" size="10" maxlength="10" />
                    <span class="erreur">${form.erreurs['dateDebPrevu']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                    <label for="dateDebReel">date début réel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="dateDebReel" name="dateDebReel" 
                           value="<c:out value="${chantier.dateDebReel}"/>" size="10" maxlength="10" />
                    <span class="erreur">${form.erreurs['dateDebReel']}</span>                  
                    </td>
                </tr>
                <tr>
                    <td><label for="dateFinPrevu">date fin prévue &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="dateFinPrevu" name="dateFinPrevu" 
                           value="<c:out value="${chantier.dateFinPrevu}"/>" size="10" maxlength="10" />
                    <span class="erreur">${form.erreurs['dateFinPrevu']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                    <label for="dateFinReel">date fin réelle &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="dateFinReel" name="dateFinReel" 
                           value="<c:out value="${chantier.dateFinReel}"/>" size="10" maxlength="10" />
                    <span class="erreur">${form.erreurs['dateFinReel']}</span>                  
                    </td>
                </tr>
                <tr>
                    <td><select name="listeStatus" id="listeStatus">
                            <option value="ENCOURS">en cours</option>
                            <option value="PREPARATION">en préparation</option>
                            <option value="TERMINE">terminé</option>
                            <option value="LIVRE">livré</option>
                            <option value="ARCHIVE">archivé</option>
                            <option value="ABANDONNE">abandonné</option>
                            <%--   <option value="${ mapProprietaires.key }">${ mapProprietaires.value.prenom } ${ mapProprietaires.value.nom }</option>
                            --%>
                        </select></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td></td>
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
