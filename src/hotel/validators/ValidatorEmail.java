package hotel.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;

import hotel.Util.MsgUtil;
import hotel.Util.Utilities;

@FacesValidator("validatorEmail")
public class ValidatorEmail implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent toValidate, Object value) {
        String email = (String) value;
        if (!Utilities.emailValido(email)) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, MsgUtil.getMessage("O campo 'E-mail' é inválido."), "");
            context.addMessage(toValidate.getClientId(context), message);
            return;
        }
    }

}
