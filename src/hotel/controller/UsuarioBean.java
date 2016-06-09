package hotel.controller;

import hotel.dao.UsuarioDAO;
import hotel.model.Usuario;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by grupoeuropa on 09/06/16.
 */

@Named("UsuarioManagedBean")
@RequestScoped
public class UsuarioBean extends BaseBean{
	@Inject
	private UsuarioDAO userDAO;

	@Inject
	private SessionBean session;

	private String email;
	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsuarioDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UsuarioDAO userDAO) {
		this.userDAO = userDAO;
	}

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}

	public Boolean login() throws Exception{
		Usuario user = userDAO.Login(email, senha);
		if(user!=null){
			session.setUsuarioLogado(user);
			return true;
		}
		return false;
	}
}
