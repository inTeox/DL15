/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dl1.form;

import com.dl1.beans.Login;
import javax.servlet.http.HttpServletRequest;

public class LoginForm {
     private String resultat;
     
     public Login verifierIdentifiants(HttpServletRequest request) {
    	 
         String login = request.getParameter("login");
         
    	 Login code = new Login();
         
    	 if (login.equals("1234")) {
    		 resultat = "vous êtes bien connecté";
                 code.setCode(login);
    	 }
    	 else {
    		 resultat = "login incorrect";
    	 }
         return code;
     }

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
     
}
