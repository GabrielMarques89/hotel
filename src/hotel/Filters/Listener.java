package hotel.Filters;

import hotel.controller.SessionBean;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Listener implements PhaseListener {
	private static final long serialVersionUID = 8907028710750653269L;
	@Inject
	private SessionBean sessionBean;
	private static String loginPage = "/login.xhtml";
	private static String indexPage = "/index.xhtml";
	private final List<String> urlsPermitidas;
	private FacesContext facesContext;


	public Listener() {
		this.urlsPermitidas = new ArrayList<>();
		this.urlsPermitidas.add("/login.xhtml");
		this.urlsPermitidas.add("/registro.xhtml");
	}

	@Override
	public void afterPhase(PhaseEvent phaseEvent) {
		facesContext = phaseEvent.getFacesContext();
		String pagina = facesContext.getViewRoot().getViewId();
		if (pagina == null || this.urlsPermitidas.contains(pagina)) {
			if (sessionBean.isLoggedIn()) {
				redirectTo(indexPage);
			}
		}else if(!sessionBean.isLoggedIn()){
			redirectTo(loginPage);
		}
	}


	private void redirectTo(String urlToRedirect){
		ExternalContext extContext = facesContext.getExternalContext();
		String url = extContext.encodeActionURL(facesContext.getApplication().getViewHandler().getActionURL(facesContext, urlToRedirect));
		try {
			extContext.redirect(url);
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	}

}
