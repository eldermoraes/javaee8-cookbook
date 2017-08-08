/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eldermoraes.ch01.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import javax.faces.convert.FacesConverter;

@FacesConverter("userConverter")
public class UserConverter implements Converter<User> {

    @Override
    public User getAsObject(FacesContext fc, UIComponent uic, String string) {
        return new User(string.substring(0, string.indexOf("/")), string.substring(string.indexOf("/") + 1));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, User t) {
        return t.getName() + "/" + t.getEmail();
    }

}