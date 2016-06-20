package hotel.dao;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.model.CartaoMagnetico;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CartaoMagneticoDAO extends BaseDAO<CartaoMagnetico, Long> {
	public Boolean hasUniqueUuid(CartaoMagnetico entidade){
		StringBuilder hql = new StringBuilder();
		hql.append("Select Card from CartaoMagnetico Card where Card.codigo = :codigo");
		Query query = this.entityManager.createQuery(hql.toString());
		query.setParameter("codigo", entidade.getCodigo());
		try{
			query.getSingleResult();
			return false;
		}catch (NoResultException noResult){
			return true;
		}
	}

	public List<CartaoMagnetico> listAllFromReservaId(Long reservaId) throws Exception{
		StringBuilder hql = new StringBuilder();
		hql.append("Select Card from CartaoMagnetico Card where Card.reserva.id = :reservaId");
		try{
			Query query = this.entityManager.createQuery(hql.toString());
			query.setParameter("reservaId", reservaId);
			return query.getResultList();
		}catch (NoResultException e) {
			return new ArrayList<>();
		}
	}
}
