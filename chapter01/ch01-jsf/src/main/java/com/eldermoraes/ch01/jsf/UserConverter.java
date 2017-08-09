package com.eldermoraes.ch01.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("userConverter")
public class UserConverter implements Converter<User> {

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, User user) {
        return user.getName() + "|" + user.getEmail();
    }

    @Override
    public User getAsObject(FacesContext fc, UIComponent uic, String string) {
        return new User(string.substring(0, string.indexOf("|")), string.substring(string.indexOf("|") + 1));
    }

}
