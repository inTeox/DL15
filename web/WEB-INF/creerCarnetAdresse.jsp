<%@ page contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <img src="/DL10/images/logo.jpg">
        <title>Gestion de chantier</title>
        <title>Création d'un client</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/mycss.css"/>" />
    </head>
    <body>
        <c:if test="${ !empty form.resultat }"><p><c:out value="${ form.resultat }" /></p></c:if>
        <form method="post" action="<c:url value="/CreationCarnetAdresses"/>" >
        <table border="0">
            <tbody>
                <tr>
                    <td><label for="idPersonne">N° &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="idPersonne" name="idPersonne" 
                           value="<c:out value="${personne.idPersonne}"/>" size="10" />
                    <span class="erreur">${form.erreurs['idPersonne']}</span><BR> </BR>                   
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><label for="choixGroupePersonne"> </label>
                    <input type="radio" id="choixGroupePersonne" name="choixGroupePersonne" value="prospect" checked /> prospect
                    <input type="radio" id="choixGroupePersonne" name="choixGroupePersonne" value="client" /> client
                    <input type="radio" id="choixGroupePersonne" name="choixGroupePersonne" value="employe" /> Employé
                    </td>
                </tr>
                <tr>
                    <td><label for="nomPersonne">Nom <span class="requis">*</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="nomPersonne" name="nomPersonne" 
                           value="<c:out value="${personne.nom}"/>" size="30" maxlength="50" />
                    <span class="erreur">${form.erreurs['nomPersonne']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><label for="prenomPersonne">Prénom </label> 
                    <input type="text" id="prenomPersonne" name="prenomPersonne" 
                           value="<c:out value="${personne.prenom}"/>" size="30" maxlength="50" />
                    <span class="erreur">${form.erreurs['prenomPersonne']}</span> </td>
                </tr>
                <BR> </BR>
            </tbody>
        </table>       
        <c:import url="/inc/inc_adresse_form.jsp" />
        <table border="0">
            <tbody>
                <tr>
                    <td><label for="telephone">téléphone <span class="requis">*</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="telephone" name="telephone" 
                           value="<c:out value="${personne.telephone}"/>" size="10" maxlength="10"/>
                    <span class="erreur">${form.erreurs['telephone']}</span>                 
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><label for="mobile">téléphone &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="mobile" name="mobile" 
                           value="<c:out value="${personne.mobile}"/>" size="10" maxlength="10"/>
                    <span class="erreur">${form.erreurs['mobile']}</span>                   
                    </td>
                </tr>
                <tr>
                    <td><label for="mail">mail &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="mail" name="mail" 
                           value="<c:out value="${personne.mail}"/>" size="30" maxlength="250" />
                    <span class="erreur">${form.erreurs['mail']}</span> </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                    <label for="noteP">note &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                    <input type="text" id="noteP" name="noteP" 
                           value="<c:out value="${personne.noteP}"/>" size="30" maxlength="500" />
                    <span class="erreur">${form.erreurs['noteP']}</span>                  
                    </td>
                </tr>
            </tbody>
        </table>
                <BR> </BR>
                <BR> </BR>      
        <input type="submit" value="VALIDEZ" name="doIt" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="<c:url value="/Employe"/>">EMPLOYE</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<c:url value="/Client"/>">CLIENT</a>
        </form>
    </body>
    <c:import url="/inc/inc_bas_page.jsp" />
</html>
