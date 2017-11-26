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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.file.sharing.core.objects.file.ItemType;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
@Entity
@Table(name = "directory", schema = "public")
public class DirectoryItem extends Item {

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "parent_id")
	private DirectoryItem parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = ALL, orphanRemoval = true)
	private List<FileItem> files;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = ALL, orphanRemoval = true)
	private List<DirectoryItem> directories;

	public List<FileItem> getFiles() {
		return files;
	}

	public void setFiles(List<FileItem> files) {
		this.files = files;
	}

	public List<DirectoryItem> getDirectories() {
		return directories;
	}

	public void setDirectories(List<DirectoryItem> directories) {
		this.directories = directories;
	}

	@Override
	public ItemType getItemType() {
		return ItemType.DIRECTORY;
	}

	@Override
	public DirectoryItem getParent() {
		return parent;
	}

	@Override
	public void setParent(DirectoryItem parent) {
		this.parent = parent;
	}

}
