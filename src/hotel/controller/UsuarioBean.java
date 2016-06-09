package hotel.controller;

import hotel.dao.UsuarioDAO;
import hotel.model.Usuario;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class UsuarioBean extends BaseBean{
	private static final long serialVersionUID = 1L;


	@Inject
	private UsuarioDAO userDAO;

	@PostConstruct
	public void init() {
		System.out.println("asd");
	}

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

	public String login() throws Exception{
		Usuario user = userDAO.Login(email, senha);
		if(user!=null){
			sessionBean.setUsuarioLogado(user);
			return "index.xhtml";
		}
		return "login.xhtml";
	}
}
