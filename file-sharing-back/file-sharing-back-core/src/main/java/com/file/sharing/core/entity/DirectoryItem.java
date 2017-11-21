package com.file.sharing.core.entity;

import static javax.persistence.CascadeType.ALL;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.file.sharing.core.objects.file.ItemType;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
@Entity
@Table(name = "directory", schema = "public")
public class DirectoryItem extends Item {

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "parent_id")
	private DirectoryItem parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = ALL)
	private List<FileItem> files;

	public List<FileItem> getFiles() {
		return files;
	}

	public void setFiles(List<FileItem> files) {
		this.files = files;
	}

	public DirectoryItem getParent() {
		return parent;
	}

	public void setParent(DirectoryItem parent) {
		this.parent = parent;
	}

	@Override
	public ItemType getItemType() {
		return ItemType.DIRECTORY;
	}

}
