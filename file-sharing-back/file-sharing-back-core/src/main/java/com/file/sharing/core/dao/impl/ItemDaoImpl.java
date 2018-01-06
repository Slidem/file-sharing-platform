package com.file.sharing.core.dao.impl;

import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.file.sharing.core.dao.AbstractDaoImpl;
import com.file.sharing.core.dao.ItemDao;
import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.entity.FileItem;
import com.file.sharing.core.entity.Item;

/**
 * @author Alexandru Mihai
 * @created Nov 12, 2017
 */
@Repository
public class ItemDaoImpl extends AbstractDaoImpl<Item> implements ItemDao {

	@Override
	public List<Item> getItemsByParentId(Integer parentId) {		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		List<Item> result = new ArrayList<Item>();
		result.addAll(getItems(cb,entityManager,DirectoryItem.class,parentId));
		result.addAll(getItems(cb,entityManager,FileItem.class,parentId));
		return result;
	}
	
	private <E extends Item> List<E> getItems(CriteriaBuilder cb, EntityManager em, Class<E> itemType, Integer parentId){
		CriteriaQuery<E> query = cb.createQuery(itemType);
		Root<E> itemRoot = query.from(itemType);
		query.select(itemRoot);
		query.where(getParentIdPredicate(cb, itemRoot.get("parent"), parentId));
		return em.createQuery(query).getResultList();
	}
	
	private Predicate getParentIdPredicate(CriteriaBuilder cb, Path<? extends Item> path, Integer parentId) {
		return   ofNullable(parentId)
				.map(pi -> cb.equal(path, pi))
				.orElse(cb.isNull(path));
	}
}
