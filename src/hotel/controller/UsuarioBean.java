package hotel.controller;

import hotel.dao.UsuarioDAO;
import hotel.model.Enum.StatusHospede;
import hotel.model.Enum.TipoUsuario;
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

	private Usuario user;

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String login() throws Exception{
		Usuario usuario = userDAO.Login(user.getEmail(),user.getSenha());
		if(usuario!=null){
			sessionBean.setUsuarioLogado(usuario);
			return indexPage;
		}
		return loginPage;
	}

	@Override
	@PostConstruct
	public void postConst() {
		user = new Usuario();
	}

	public String logout() throws Exception{
		Usuario userToSave = sessionBean.getUsuarioLogado();
		userToSave.setUltimoAcesso(new Date());
		userDAO.merge(userToSave);
		//Todo: Descobrir pq merge não salva o horário .... o persist salva, mas gera uma exceção de detatched entity..
		sessionBean.setUsuarioLogado(null);
		return loginPage;
	}

	@Override
	//TODO: VALIDAR OS CAMPOS ÚNICOS
	public String cadastro() throws Exception{
		//Inserindo a data de criação
		user.setDataCriacao(new Date());

		//Inserindo o status do usuário
		user.setStatus(StatusHospede.ATIVO);

		//Inserido o tipo de usuário
		user.setTipoUsuario(TipoUsuario.CLIENTE);

		Usuario usuario = userDAO.merge(user);

		if(usuario != null){
			sessionBean.setUsuarioLogado(usuario);
			return indexPage;
		}
		return cadastroPage;
	}

	public String editarDados(){
		return null;
	}
}
