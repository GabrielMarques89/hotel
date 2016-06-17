package hotel.dao;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.model.Enum.SituacaoReserva;
import hotel.model.Reserva;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ReservaDAO extends BaseDAO<Reserva, Long> {

    private String baseHql = new String("SELECT R from Reserva R ");

    public List<Reserva> listarReservasUsuarioById(Long id){
        StringBuilder queryHql = appendToBaseHql("Where R.usuario.id = :userId");
        try{
            Query query = this.entityManager.createQuery(queryHql.toString());
            query.setParameter("userId",id);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<>();
        }
    }

    public StringBuilder appendToBaseHql(String string){
        StringBuilder myHql = new StringBuilder(baseHql);
        return myHql.append(string);
    }

    public List<Reserva> listarReservasByStatus(SituacaoReserva situ){
        StringBuilder queryHql = appendToBaseHql("Where R.situacaoReserva = :situ");
        try{
            Query query = this.entityManager.createQuery(queryHql.toString());
            query.setParameter("situ", situ);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<>();
        }
    }
}
