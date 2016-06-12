package hotel.controller;

import hotel.model.Usuario;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SessionBean extends BaseBean{

	private Usuario usuarioLogado;

	@PostConstruct
	public void init() {
		this.usuarioLogado = null;
	}

	public String logout() {
		this.usuarioLogado = null;
		return loginPage;
	}

	public String goHome() {
		return indexPage;
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

	@Override public void postConst() {
		usuarioLogado = null;
	}

	@Override public String salvar() throws Exception {
		return null;
	}
}
