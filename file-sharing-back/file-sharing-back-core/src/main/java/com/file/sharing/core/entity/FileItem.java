package com.file.sharing.core.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.file.sharing.core.objects.file.ItemType;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
@Entity
@Table(name = "file", schema = "public")
public class FileItem extends Item {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4679575857768871973L;

	@Column(name = "upload_time")
	private Timestamp uploadTime;

	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private DirectoryItem parent;

	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private FileItemCategory category;

	@Column(name = "size")
	private Long size;

	public FileItem() {
	}

	public FileItem(FileItemCategory category) {
		this.category = category;
	}


	public FileItemCategory getCategory() {
		return category;
	}

	public void setCategory(FileItemCategory category) {
		this.category = category;
	}

	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public ItemType getItemType() {
		return ItemType.FILE;
	}

	@Override
	public DirectoryItem getParent() {
		return parent;
	}

	@Override
	public void setParent(DirectoryItem directory) {
		this.parent = directory;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
}
