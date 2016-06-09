package hotel.controller;

import hotel.dao.BaseDAO;
import hotel.model.Usuario;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by grupoeuropa on 09/06/16.
 */

public class UsuarioBean extends BaseDAO<Usuario,Long> {

	public UsuarioBean(){};

	@Inject
	@SessionScoped
	public SessionBean session;

	public UsuarioBean(SessionBean session) {
		this.session = session;
	}

	public Boolean Login(String email, String senha) throws Exception{
		StringBuilder queryHql = new StringBuilder("SELECT USR FROM Usuario USR ");
		queryHql.append("WHERE USR.email = :email AND USR.senha = :senha");
		try{
			Query query = this.entityManager.createQuery(queryHql.toString());
			query.setParameter("email", email);
			query.setParameter("senha", senha);
			Usuario user = (Usuario)query.getSingleResult();
			session.setUsuarioLogado(user);
			return true;
		} catch (NoResultException e){
			return false;
		} catch (Exception e){
			throw e;
		}
	}
}
