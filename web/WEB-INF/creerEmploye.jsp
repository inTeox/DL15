<%@ page contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <img src="/DL10/images/logo.jpg">
        <title>Création employé</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/mycss.css"/>" />
    </head>
    <body>
        <BR> </BR>
        <BR> </BR> 
        <form method="post" action="<c:url value="/Employe"/>" >
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
                    <input type="text" id="idPersonne" name="idPersonne" 
                           value="<c:out value="${employe.idPersonne}"/>" size="10" />
                    <span class="erreur">${form.erreurs['idPersonne']}</span><BR> </BR>                   
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td><label for="dateNaissance">Date Naissance </label> 
                    <input type="text" id="dateNaissance" name="dateNaissance" 
                           value="<c:out value="${employe.dateNaissance}"/>" size="10" maxlength="10" />
                    <span class="erreur">${form.erreurs['dateNaissance']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><label for="nationalite">nationalité </label> 
                    <input type="text" id="nationalite" name="nationalite" 
                           value="<c:out value="${employe.nationalite}"/>" size="30" maxlength="50" />
                    <span class="erreur">${form.erreurs['nationalite']}</span> </td>
                </tr>
                <tr>
                    <td><label for="securiteSociale">Sécurité sociale </label> 
                    <input type="text" id="securiteSociale" name="securiteSociale" 
                           value="<c:out value="${employe.securiteSociale}"/>" size="30" maxlength="50" />
                    <span class="erreur">${form.erreurs['dateNaissance']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><label for="titreSejour">titre de séjour </label> 
                    <input type="text" id="titreSejour" name="titreSejour" 
                           value="<c:out value="${employe.titreSejour}"/>" size="30" maxlength="50" />
                    <span class="erreur">${form.erreurs['titreSejour']}</span> </td>
                </tr>
                <tr>
                    <td><label for="experience">experience </label> 
                    <input type="text" id="experience" name="experience" 
                           value="<c:out value="${employe.experience}"/>" size="30" maxlength="50" />
                    <span class="erreur">${form.erreurs['experience']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><label for="permisTravail">permis de travail </label> 
                    <input type="text" id="permisTravail" name="permisTravail" 
                           value="<c:out value="${employe.permisTravail}"/>" size="30" maxlength="50" />
                    <span class="erreur">${form.erreurs['permisTravail']}</span> </td>
                </tr>
                <tr>
                    <td><label for="fonction">fonction </label> 
                    <input type="text" id="fonction" name="fonction" 
                           value="<c:out value="${employe.fonction}"/>" size="30" maxlength="50" />
                    <span class="erreur">${form.erreurs['fonction']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><label for="choixGroupeEquipe"> Equipe de base ?</label>
                    <input type="radio" id="choixGroupeEquipe" name="choixGroupeEquipe" value="base" checked /> oui
                    <input type="radio" id="choixGroupeEquipe" name="choixGroupeEquipe" value="nobase" /> non                    </td>
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
