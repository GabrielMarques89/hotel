package hotel.dao;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.model.Reserva;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ReservaDAO extends BaseDAO<Reserva, Long> {

    public List<Reserva> listarReservasUsuarioById(Long id){
        StringBuilder queryHql = new StringBuilder();
        queryHql.append("SELECT R from Reserva R ");
        queryHql.append("Where R.usuario.id = :userId");
        try{
            Query query = this.entityManager.createQuery(queryHql.toString());
            query.setParameter("userId",id);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<>();
        }
    }
}
