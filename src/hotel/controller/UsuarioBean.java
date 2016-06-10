package hotel.controller;

import hotel.dao.UsuarioDAO;
import hotel.model.Usuario;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

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

	private static String loginPage = "/login.xhtml";
	private static String indexPage = "/index.xhtml";
	private static String cadastroPage = "/registro.xhtml";

//	private String email;
//	private String senha;
	private Usuario user = new Usuario();

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getSenha() {
//		return senha;
//	}
//
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}

	public String login() throws Exception{
		Usuario usuario = userDAO.Login(user.getEmail(),user.getSenha());
		if(usuario!=null){
			sessionBean.setUsuarioLogado(usuario);
			return indexPage;
		}
		return loginPage;
	}

	public String logout() throws Exception{
		sessionBean.getUsuarioLogado().setUltimoAcesso(new Date());
		userDAO.merge(sessionBean.getUsuarioLogado());
		sessionBean.setUsuarioLogado(null);
		return loginPage;
	}

	public String cadastro(Usuario user) throws Exception{
		Usuario usuario = userDAO.merge(user);
		if(user!=null){
			sessionBean.setUsuarioLogado(usuario);
			return indexPage;
		}
		return cadastroPage;
	}
}
