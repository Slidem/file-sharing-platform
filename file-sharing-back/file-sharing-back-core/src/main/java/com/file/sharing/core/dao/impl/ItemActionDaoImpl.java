package com.file.sharing.core.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.NonUniqueResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.file.sharing.core.dao.AbstractDaoImpl;
import com.file.sharing.core.dao.ItemActionDao;
import com.file.sharing.core.entity.ItemActionEntity;
import com.file.sharing.core.search.OrderValue;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Repository
public class ItemActionDaoImpl extends AbstractDaoImpl<ItemActionEntity> implements ItemActionDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Optional<ItemActionEntity> findLastItemAction(Integer itemId) {
		
		TypedQuery<ItemActionEntity> query = entityManager.createQuery("from ItemActionEntity ia where ia.itemId=:itemId order by actionTime DESC", ItemActionEntity.class);
		query.setParameter("itemId", itemId);
		query.setMaxResults(1);
		try {
			return Optional.of(query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		} catch (NonUniqueResultException e) {
			logger.warn("More than one item actio found for itemId: " + itemId, e);
			throw e;
		}
	}

	@Override
	public List<ItemActionEntity> findActionsBasedOnItemId(Integer itemId, OrderValue actionTimeOrder) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ItemActionEntity> criteriaQuery = cb.createQuery(ItemActionEntity.class);
		Root<ItemActionEntity> root = criteriaQuery.from(ItemActionEntity.class);
		
		Path<Set<String>> actionTimePath = root.get("actionTime");
		
		Order order = OrderValue.ASC == actionTimeOrder ? cb.asc(actionTimePath) : cb.desc(actionTimePath);
		Predicate idPredicate = cb.equal(root.get("itemId"), itemId);

		TypedQuery<ItemActionEntity> typeQuery = entityManager.createQuery(criteriaQuery.select(root).where(idPredicate).orderBy(order));

		return typeQuery.getResultList();
	}

}
