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

	/**
	 * Searches for all the items inside a directory by their parentId.
	 * 
	 * @return list of items (files and directories) contained by a folder
	 */
	@Override
	public List<Item> getItemsByParentId(Integer parentId) {		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		List<Item> result = new ArrayList<Item>();
		result.addAll(getItems(cb,entityManager,DirectoryItem.class,parentId));
		result.addAll(getItems(cb,entityManager,FileItem.class,parentId));
		return result;
	}
	
	/**
	 * Helper method used to create and run a query which returns items
	 * by a given parentId.
	 * 
	 * @param cb
	 * @param em
	 * @param itemType
	 * @param parentId
	 * @return
	 */
	private <E extends Item> List<E> getItems(CriteriaBuilder cb, EntityManager em, Class<E> itemType, Integer parentId){
		CriteriaQuery<E> query = cb.createQuery(itemType);
		Root<E> itemRoot = query.from(itemType);
		query.select(itemRoot);
		query.where(getParentIdPredicate(cb, itemRoot.get("parent"), parentId));
		return em.createQuery(query).getResultList();
	}
	
	/**
	 * Predicate method used to change a given <code>CriteriaBuilder</code> argument testing
	 * for both cases where the parentId can be null or non-null.
	 * 
	 * @param cb
	 * @param path
	 * @param parentId
	 * @return
	 */
	private Predicate getParentIdPredicate(CriteriaBuilder cb, Path<? extends Item> path, Integer parentId) {
		return ofNullable(parentId)
				.map(pi -> cb.equal(path, pi))
				.orElse(cb.isNull(path));
	}
}
