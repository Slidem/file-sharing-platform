package com.file.sharing.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
		
		//Query directories
		CriteriaQuery<DirectoryItem> cqDir = cb.createQuery(DirectoryItem.class);
		Root<DirectoryItem> dirRoot = cqDir.from(DirectoryItem.class);
		cqDir.select(dirRoot);
		cqDir.where(cb.equal(dirRoot.get("parent"), parentId));
		result.addAll(entityManager.createQuery(cqDir).getResultList());
		
		//Query files
		CriteriaQuery<FileItem> cqFile = cb.createQuery(FileItem.class);
		Root<FileItem> fileRoot = cqFile.from(FileItem.class);
		cqFile.select(fileRoot);
		cqFile.where(cb.equal(fileRoot.get("parent"), parentId));
		result.addAll(entityManager.createQuery(cqFile).getResultList());
		
		return result;
	}
}
