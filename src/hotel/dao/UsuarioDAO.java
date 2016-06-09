package hotel.dao;

import hotel.model.Usuario;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by grupoeuropa on 09/06/16.
 */
public class UsuarioDAO extends BaseDAO<Usuario, Long>{

	public Usuario Login(String email, String senha) throws Exception{
		StringBuilder queryHql = new StringBuilder("SELECT USR FROM Usuario USR ");
		queryHql.append("WHERE USR.email = :email AND USR.senha = :senha");
		try{
			Query query = this.entityManager.createQuery(queryHql.toString());
			query.setParameter("email", email);
			query.setParameter("senha", senha);
			Usuario user = (Usuario)query.getSingleResult();
			return user;
		} catch (NoResultException e){
			return null;
		} catch (Exception e){
			throw e;
		}
	}

}
