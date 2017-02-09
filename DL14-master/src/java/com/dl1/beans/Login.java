/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dl1.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Login  implements Serializable{

    private String code;
    
        

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
