package hotel.controller;

import hotel.ExceptionHandlers.ConstraintViolationHandler;
import hotel.Util.MsgUtil;
import hotel.dao.UsuarioDAO;
import hotel.model.Enum.StatusHospede;
import hotel.model.Enum.TipoUsuario;
import hotel.model.Usuario;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

import static hotel.Util.Utilities.ConstraintViolationException;

@Named
@ConversationScoped
public class UsuarioBean extends BaseBean {
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
		Usuario usuario = userDAO.login(user.getEmail(),encrypt(user.getSenha()));
		if(usuario!=null){
			sessionBean.setUsuarioLogado(usuario);
			return indexPage;
		}
        MsgUtil.addWarnMessage("Login ou senha inválidos.", "");
		return loginPage;
	}

	@Override
	@PostConstruct
	public void postConst() {
		user = new Usuario();
		listaUsuario = userDAO.listAll();
	}

    public String editar(){
        user = sessionBean.getUsuarioLogado();
        return editarDados;
    }

    public String edit() throws Exception {
        Usuario userToSave = userDAO.findById(user.getId());
        userToSave.setEmail(user.getEmail());
        userToSave.setSenha(user.getSenha());
        userToSave.setNomeCompleto(user.getNomeCompleto());
        user = userToSave;
        return salvar();
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
	public String salvar() throws Exception{
		Boolean existeErro = false;

		LocalDate now = new LocalDate();
		LocalDate nascimento = new LocalDate(user.getNascimento());
		Years age = Years.yearsBetween(nascimento, now);
		Years minAge = Years.years(18);
		if(!age.isGreaterThan(minAge)){
			MsgUtil.addWarnMessage("A data de nascimento não é valida.", "");
			existeErro = true;
		}

		if(!existeErro){
			if(user.getDataCriacao()==null){
                user.setDataCriacao(new Date());
            }
            if(user.getStatus()==null){
    			user.setStatus(StatusHospede.ATIVO);
            }
            if(user.getTipoUsuario()==null){
			    user.setTipoUsuario(TipoUsuario.CLIENTE);
            }
			user.setSenha(encrypt(user.getSenha()));
			try{
				Usuario usuario = userDAO.merge(user);
				if(usuario != null){
					sessionBean.setUsuarioLogado(usuario);
					return indexPage;
				}
			}catch (EJBException e){
				ConstraintViolationHandler handler = ConstraintViolationException(e);
				if(handler == null) {
					throw e;
				}
				if(handler.getHasError()){
					MsgUtil.addErrorMessage("Este e-mail já está sendo utilizado por outro usuário.", "");
					return cadastroPage;
				}
			}catch (Exception e){
				throw e;
			}
		}
		return cadastroPage;
	}
}
