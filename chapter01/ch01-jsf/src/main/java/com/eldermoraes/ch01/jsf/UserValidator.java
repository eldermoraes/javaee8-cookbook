package com.eldermoraes.ch01.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("userValidator")
public class UserValidator implements Validator<User> {

    @Override
    public void validate(FacesContext fc, UIComponent uic, User user) throws ValidatorException {
        if(!user.getEmail().contains("@")){
            throw new ValidatorException(new FacesMessage(null, "Malformed e-mail"));
        }
    }
    
}