package hotel.dao;

import hotel.model.Enum.TipoUsuario;
import hotel.model.Usuario;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grupoeuropa on 09/06/16.
 */
@Stateless
public class UsuarioDAO extends BaseDAO<Usuario, Long>{

	private String selecionarUsuario = "SELECT USR FROM Usuario USR ";

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario login(String email, String senha) throws Exception{
		StringBuilder queryHql = new StringBuilder(selecionarUsuario);
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

	public List<Usuario> listarClientes(){
		StringBuilder queryHql = new StringBuilder(selecionarUsuario);
		queryHql.append("WHERE USR.tipoUsuario = :tipoUsuario");
		try{
			Query query = this.entityManager.createQuery(queryHql.toString());
			query.setParameter("tipoUsuario", TipoUsuario.CLIENTE);
			return query.getResultList();
		}catch (NoResultException e){
			return new ArrayList<>();
		}catch (Exception e){
			throw e;
		}
	}

	public Boolean emailUnico(String email)throws Exception {
		StringBuilder queryHql = new StringBuilder(selecionarUsuario);
		Query query = this.entityManager.createQuery(queryHql.toString());
		queryHql.append("WHERE USR.email = :email");
		query.setParameter("email", email);
		Usuario usuario = (Usuario) query.getSingleResult();
		return usuario.equals(null);
	}
}
