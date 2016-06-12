package hotel.controller;

import hotel.Util.MsgUtil;
import hotel.dao.UsuarioDAO;
import hotel.model.Enum.StatusHospede;
import hotel.model.Enum.TipoUsuario;
import hotel.model.Usuario;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Named
@ConversationScoped
public class UsuarioBean extends BaseBean{
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO userDAO;

	private Usuario user;

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	private List<Usuario> listaUsuario;

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String login() throws Exception{
		Usuario usuario = userDAO.login(user.getEmail(),user.getSenha());
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
		listaUsuario = userDAO.listAll();
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
	public String salvar() throws Exception{
		if(user.getNascimento().equals(new Date()) || user.getNascimento().after(new Date())){
			MsgUtil.addWarnMessage("A data de nascimento não é valida.", "");
		}else{
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
		}
		return cadastroPage;
	}

	public String editarDados() throws Exception {
		Usuario usuarioLogado = sessionBean.getUsuarioLogado();
		Usuario usuario = userDAO.findById(usuarioLogado.getId());

		if(usuario != null){
            //TODO: PRECISA UTILIZAR O HASH AQUI
            if(usuario.getSenha() != usuarioLogado.getSenha()){
                usuario.setSenha(usuarioLogado.getSenha());
            }
            //TODO: PRECISA VALIDAR O E-MAIL
            usuario.setNomeCompleto(user.getEmail());
            usuario.setEmail(user.getEmail());
            userDAO.merge(usuario);
            MsgUtil.addInfoMessage("Dados salvos com sucesso!", "");
        }else{
            MsgUtil.addErrorMessage("Desculpe, mas não foi possível salvar os dados.", "");
        }
		return editarDados;
	}
}
