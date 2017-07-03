<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <img src="/DL14-master/images/logo.jpg">
        <title>Gestion de chantier</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/mycss.css"/>" />
    </head>
    <body>
        <h1>Bienvenu sur la gestion de chantier !</h1>
        <div>
            <form method="post" action="<c:url value="/Page1"/>">
                <fieldset>
                    <legend>Veuillez entrer votre login</legend>
    
                    <label for="login">Login <span class="requis">*</span></label>
                    <input type="text" id="login" name="login" value="" size="4" maxlength="4" />
                    <br />                    
                </fieldset>
                <input type="submit" value="ENTREZ" name="doIt" />
            </form>   
        <c:if test="${ !empty form.resultat }"><p><c:out value="${ form.resultat }" /></p></c:if>        
    </body>
</html>
