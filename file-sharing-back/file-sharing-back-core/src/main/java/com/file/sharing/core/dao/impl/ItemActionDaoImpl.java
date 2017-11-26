package com.file.sharing.core.dao.impl;

import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.NonUniqueResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.file.sharing.core.dao.AbstractDaoImpl;
import com.file.sharing.core.dao.ItemActionDao;
import com.file.sharing.core.entity.ItemActionEntity;

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

}
