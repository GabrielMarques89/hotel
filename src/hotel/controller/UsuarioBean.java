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

	public Boolean login(String email, String senha) throws Exception{
		Usuario usuario = userDAO.Login(email,senha);
		if(usuario!=null){
			session.setUsuarioLogado(usuario);
			return true;
		}
		return false;
	}
}
