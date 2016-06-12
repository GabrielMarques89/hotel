package hotel.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;

import hotel.Util.MsgUtil;
//import org.apache.commons.lang3.StringUtils;

@FacesValidator("validatorCPF")
public class ValidatorCPF implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent toValidate, Object value) {
        String cpf = (String) value;
//        cpf = cpf.replace(".", "");
//        cpf = cpf.replace("-", "");
//        if (StringUtils.isEmpty(cpf)) {
//            return;
//        }
//        if (!Util.validaCPF(cpf)) {
//            ((UIInput) toValidate).setValid(false);
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, MsgUtil.getMessage("msg.cpf.invalido"), "");
//            context.addMessage(toValidate.getClientId(context), message);
//        }
    }
}
