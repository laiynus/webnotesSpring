package by.khrapovitsky.validator;

import org.apache.commons.lang3.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("registrationValidator")
public class RegistrationValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String login = o.toString();
        UIInput uiInputPassword = (UIInput) uiComponent.getAttributes().get("password");
        String password = uiInputPassword.getSubmittedValue().toString();
        UIInput uiInputConfirmPassword = (UIInput) uiComponent.getAttributes().get("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();
        if (password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty() || login == null || login.isEmpty()) {
            return;
        }
        if(!StringUtils.isAlphanumeric(login)){
            throw new ValidatorException(new FacesMessage("Login must contain only letters and numbers."));
        }
        if (!password.equals(confirmPassword)) {
            uiInputConfirmPassword.setValid(false);
            throw new ValidatorException(new FacesMessage("Password must match confirm password."));
        }
    }
}
