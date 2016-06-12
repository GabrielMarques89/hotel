package hotel.controller;

import hotel.Util.MsgUtil;
import hotel.dao.UsuarioDAO;
import hotel.model.Usuario;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class SessionBean extends BaseBean{

	@Inject
	private UsuarioDAO usuarioDAO;

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

	@Override
	public String salvar() throws Exception {
		return null;
	}

	public String editarDados() throws Exception {
		Usuario usuarioDB = usuarioDAO.findById(usuarioLogado.getId());
		if(usuarioDB != null){
			//TODO: PRECISA UTILIZAR O HASH AQUI
			if(!usuarioDB.getSenha().equals(usuarioLogado.getSenha())){
				//usuarioDB.setSenha(Hash(getUsuarioLogado().getSenha()));
			}

			if(!usuarioDB.getEmail().equals(usuarioLogado.getEmail())){
				//TODO: NÃO ESTÁ FUNCIONANDO
//				if(!usuarioDAO.emailUnico(usuarioLogado.getEmail())) {
//					MsgUtil.addErrorMessage("Este e-mail já está sendo utilizado por outro usuário.", "");
//				}
			}else{
				usuarioDAO.merge(usuarioDB);
				setUsuarioLogado(usuarioDB);
				MsgUtil.addInfoMessage("Dados salvos com sucesso!", "");
			}
		}else{
			MsgUtil.addErrorMessage("Desculpe, mas não foi possível salvar os dados.", "");
		}
		return editarDados;
	}
}
