package hotel.controller;

import hotel.Util.MsgUtil;
import hotel.dao.UsuarioDAO;
import hotel.model.Enum.TipoUsuario;
import hotel.model.Usuario;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named
@SessionScoped
public class SessionBean extends BaseBean{

	@Inject
	private UsuarioDAO usuarioDAO;

	private Usuario usuarioLogado;

	public String logout() {
        Long id = sessionBean.getUsuarioLogado().getId();
        Usuario userToSave = usuarioDAO.findById(id);
        userToSave.setUltimoAcesso(new Date());
        usuarioDAO.merge(userToSave);
        usuarioLogado = null;
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

	@Override
	@PostConstruct
	public void postConst() {
		usuarioLogado = new Usuario();
	}

	@Override
	public String salvar() throws Exception {
		return null;
	}

	public Boolean isAdmin(){
        TipoUsuario tipoUser = this.getUsuarioLogado().getTipoUsuario();
        return tipoUser == TipoUsuario.ADMINISTRADOR || tipoUser == TipoUsuario.FUNCIONARIO;
    }

	public String editarDados() throws Exception {
		Usuario usuarioDB = usuarioDAO.findById(usuarioLogado.getId());
		usuarioDAO.merge(usuarioDB);
		setUsuarioLogado(usuarioDB);
		MsgUtil.addInfoMessage("Dados salvos com sucesso!", "");
		return editarDados;
	}
}
