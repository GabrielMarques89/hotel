package hotel.controller;

import hotel.model.Usuario;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionBean implements Serializable {

	private Usuario usuarioLogado;

	@PostConstruct
	public void init() {
		this.usuarioLogado = null;
	}

	public String logout() {
		this.usuarioLogado = null;
		return "/login.jsf";
	}

	public String goHome() {
		return "/index.jsf?faces-redirect=true";
	}

	public boolean isLoggedIn() {
		return this.usuarioLogado != null && this.usuarioLogado.getId() != null && this.usuarioLogado.getId() > 0;
	}

	public Usuario getUsuarioLogado() {
		return this.usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
