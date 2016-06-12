package hotel.dao;

import hotel.model.Enum.TipoUsuario;
import hotel.model.Usuario;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by grupoeuropa on 09/06/16.
 */
@Stateless
public class UsuarioDAO extends BaseDAO<Usuario, Long>{

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario login(String email, String senha) throws Exception{
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

	public List<Usuario> listarPorTipo(TipoUsuario tipoUsuario)throws Exception {
		try {
			StringBuilder queryHql = new StringBuilder("SELECT USR FROM Usuario USR ");
			Query query = this.entityManager.createQuery(queryHql.toString());
			queryHql.append("WHERE USR.email = :email AND USR.senha = :senha");
			query.setParameter("tipoUsuario", tipoUsuario);
			List<Usuario> lista = query.getResultList();
			return lista;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw e;
		}
	}
}
