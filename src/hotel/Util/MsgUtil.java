package hotel.Util;

import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MsgUtil {

    public static final String EMPTY_MESSAGE = "standard.msg.blank";
    public static final String MESSAGE_ERROR_STRING = "standard.msg.error";
    public static final String MESSAGE_WARN_STRING = "standard.msg.warn";

    private static final String INVERSED_BAR = "\\";
    private static final String MESSAGES = "messages";
    private static final String PARAM_PREFIX = INVERSED_BAR + "{";
    private static final String PARAM_SUFIX = INVERSED_BAR + "}";
    private static final String PROJMESSAGES = "messages";
    private static final String TWO_INVERSED_BAR = INVERSED_BAR + INVERSED_BAR;
    private static final String FOUR_INVERSED_BAR = TWO_INVERSED_BAR + TWO_INVERSED_BAR;
    private static final Logger LOGGER = Logger.getLogger(LoggerUtil.LOGGER_INFO);

    private static ResourceBundle configurationBundle;

    private MsgUtil() {}

    public static void addErrorMessage(String messageKey, Object... params) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage(messageKey, params), getMessage(messageKey, params)));
    }

    public static void addInfoMessage(String messageKey, Object... params) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage(messageKey, params), getMessage(messageKey, params)));
    }

    public static void addWarnMessage(String messageKey, Object... params) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage(messageKey, params), getMessage(messageKey, params)));
    }

    public static String getConfiguration(String key) {
        if (configurationBundle == null) {
            configurationBundle = ResourceBundle.getBundle("configuration");
        }
        return configurationBundle.getString(key);
    }

    private static ResourceBundle getJavaliMessagesBundle() {
        Locale locale = null;
        if (FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().getViewRoot() != null && FacesContext.getCurrentInstance().getViewRoot().getLocale() != null) {
            locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        } else {
            locale = Locale.getDefault();
        }
        return ResourceBundle.getBundle(PROJMESSAGES, locale);
    }

    private static String getMessage(ResourceBundle messageBundle, String messageKey, Object... params) {
        String message = messageBundle.getString(messageKey);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                message = message.replaceAll(PARAM_PREFIX + i + PARAM_SUFIX, params[i] != null ? params[i].toString().replaceAll(TWO_INVERSED_BAR, FOUR_INVERSED_BAR) : null);
            }
        }
        return message;
    }

    public static String getMessage(String messageKey, Object... params) {
        if (messageKey == null) {
            return getMessagesBundle().getString(EMPTY_MESSAGE);
        }
        try {
            return getMessage(getMessagesBundle(), messageKey, params);
        } catch (MissingResourceException e) {
            LOGGER.error("", e);
        }
        try {
            return getMessage(getJavaliMessagesBundle(), messageKey, params);
        } catch (MissingResourceException e1) {
            LOGGER.error("", e1);
        }
        return messageKey;
    }

    private static ResourceBundle getMessagesBundle() {
        Locale locale = null;
        if (FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().getViewRoot() != null && FacesContext.getCurrentInstance().getViewRoot().getLocale() != null) {
            locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        } else {
            locale = Locale.getDefault();
        }
        return ResourceBundle.getBundle(MESSAGES, locale);
    }

    public static void persistirMensagens() {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public Boolean getHasGlobalMessage() {
        Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages(null);
        if (messages != null && messages.hasNext()) {
            return true;
        }
        return false;
    }

}