package hotel.dao;

import hotel.Util.ReflectionsUtil;
import hotel.model.Entidade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
@Stateless
public abstract class BaseDAO<T extends Entidade, K extends Serializable> implements Serializable {

	@PersistenceContext
	protected EntityManager entityManager;

	protected Class<T> persistenceClazz;

	public BaseDAO() {
		ReflectionsUtil reflections = new ReflectionsUtil();
		ParameterizedType parameterizedType = reflections.getParameterizedType(this.getClass());
		if (parameterizedType != null) {
			this.persistenceClazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		}
	}

	public BaseDAO(final Class<T> persistenceClazz, final EntityManager entityManager) {
		this.persistenceClazz = persistenceClazz;
		this.entityManager = entityManager;
	}

	public T findById(final K pk) {
		return this.entityManager.find(this.persistenceClazz, pk);
	}

	public List<T> listAll() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(this.persistenceClazz);
		Root<T> root = query.from(this.persistenceClazz);
		query.select(root);
		return this.entityManager.createQuery(query).getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public T merge(final T entity) {
		T t = this.entityManager.merge(entity);
		this.entityManager.flush();
		return t;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void persist(final T entity) {
		this.entityManager.persist(entity);
		this.entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(final T entity) {
		this.entityManager.remove(this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity));
	}

	public T getSingleOrDefault(Query query) {
		try {
			return (T) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
