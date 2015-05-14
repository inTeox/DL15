<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <img src="/DL10/images/logo.jpg">
        <title>Gestion de chantier</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/mycss.css"/>" />
    </head>
    <body>
        <c:if test="${ !empty form.resultat }"><p><c:out value="${ form.resultat }" /></p></c:if>
        <table border="0">
            <thead>
                <tr>
                    <th><h1>Carnet d'adresses</h1></th>
                    <th><h1>&nbsp &nbsp &nbsp</h1></th>
                    <th><h1>Gestion de chantiers</h1></th>
                    <th><h1>&nbsp &nbsp &nbsp</h1></th>
                    <th><h1>Gestion du personnel</h1></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><a href="<c:url value="/ListerCarnet"/>">Lister le carnet d'adresses</a></td>
                    <td>&nbsp &nbsp &nbsp</td>
                    <td><a href="<c:url value="/ListerChantier"/>">Lister les chantiers</a></td>
                    <td>&nbsp &nbsp &nbsp</td>
                    <td><a href="<c:url value="/ListerContrat"/>">Lister le personnel</a></td>
                </tr>
                <tr>
                    <td><a href="<c:url value="/CreationCarnetAdresses"/>">Ajouter un contact</a></td>
                    <td>&nbsp &nbsp &nbsp</td>
                    <td><a href="<c:url value="/CreationChantier"/>">Ajouter un chantier</a></td>
                    <td>&nbsp &nbsp &nbsp</td>
                    <td><a href="<c:url value="/CreationContrat"/>">Ajouter un contrat</a></td>
                </tr>
                <tr>
                    <td>Supprimer un contact</td>
                    <td>&nbsp &nbsp &nbsp</td>
                    <td>Supprimer un chantier</td>
                    <td>&nbsp &nbsp &nbsp</td>
                    <td>Supprimer un contrat</td>
                </tr>
                <tr>
                    <td></td>
                    <td>&nbsp &nbsp &nbsp</td>
                    <td>Statistiques chantiers</td>
                    <td>&nbsp &nbsp &nbsp</td>
                    <td><a href="<c:url value="/CreationTravail"/>">Suivi du personnel</a></td>
                </tr>
                <tr>
                    <td></td>
                    <td>&nbsp &nbsp &nbsp</td>
                    <td></td>
                    <td>&nbsp &nbsp &nbsp</td>
                    <td></td>
                </tr>
            </tbody>
        </table>

        
     <br><a href="<c:url value="/Page1"/>">Retour &eacute;cran de login</a>  
    </body>
</html>
