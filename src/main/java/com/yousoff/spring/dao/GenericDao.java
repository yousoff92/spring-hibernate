package com.yousoff.spring.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yousoff.spring.common.Expression;

@Repository
@Transactional
public class GenericDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public <T> Object getObjectById(T t, final int id) {
		return (T) getCurrentSession().get(t.getClass(), id);
	}
	
	public <T> List<T> getObjectListByCriteria(Class<T> t, List<Expression> exps) {
		List<T> result = null;
		CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(t);
		Root<T> root = criteriaQuery.from(t);
		
		// Select statement
		criteriaQuery.select(root);
		
		// Where statement
		if(!CollectionUtils.isEmpty(exps)) {
			List<Predicate> restrictions = getRestrictions(criteriaBuilder, root, exps);
			
			if(!CollectionUtils.isEmpty(restrictions)) {
				criteriaQuery.where(restrictions.toArray(new Predicate[] {}));
			}
		}
		
		// Query
		result = getCurrentSession().createQuery(criteriaQuery).getResultList();
		return result;
	}
	
	// TODO - get object by stored procedure
	@SuppressWarnings("unchecked")
	public List<Object[]> getObjectListByStoredProcedure(String procedureName) {
		StoredProcedureQuery storedProcedure = getCurrentSession().createStoredProcedureQuery(procedureName);
		storedProcedure.execute();
		return storedProcedure.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getObjectListByStoredProcedure(T t, String procedureName, String[] parameters) throws Exception {
		StoredProcedureQuery storedProcedure = getCurrentSession().createStoredProcedureQuery(procedureName, t.getClass());
		
		int i = 1;
		for(String param : parameters) {
			
			System.out.println(PropertyUtils.getPropertyType(t, param));
			System.out.println(PropertyUtils.getProperty(t, param).getClass());
			
			storedProcedure.registerStoredProcedureParameter(i, PropertyUtils.getPropertyType(t, param), ParameterMode.IN);
			storedProcedure.setParameter(i, PropertyUtils.getProperty(t, param));
			i++;
		}
		
		storedProcedure.execute();
		return storedProcedure.getResultList();
	}
	
	// TODO - get object with pagination
	
	private <T> List<Predicate> getRestrictions(CriteriaBuilder criteriaBuilder, Root<T> root, List<Expression> exps) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		for(Expression exp : exps) {
			switch (exp.getOperator()) {
			case EQUAL:
				predicates.add(criteriaBuilder.equal(root.get(exp.getKey()), exp.getValue()));
				break;
			// TODO - Other operator	
			default:
				break;
			}
		}
		return predicates;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> t) {
		Query<T> query = getCurrentSession().createQuery("from " + t.getSimpleName());
		return query.list();
	}

	public <T> void save(T t) {
		getCurrentSession().persist(t);
	}

	public <T> void update(T t) {
		getCurrentSession().update(t);
	}

	public <T> void delete(T t) {
		getCurrentSession().delete(t);
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
