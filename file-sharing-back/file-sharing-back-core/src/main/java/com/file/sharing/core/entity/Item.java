package com.file.sharing.core.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.file.sharing.core.objects.file.ItemType;

/**
 * @author Alexandru Mihai
 * @created Nov 12, 2017
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -711533308680470247L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
	@SequenceGenerator(name = "item_seq", sequenceName = "seq_id_item", allocationSize = 1)
	private Integer id;

	private String name;

	private String path;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public abstract ItemType getItemType();

	public abstract DirectoryItem getParent();

	public abstract void setParent(DirectoryItem parent);

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", user=" + user + "]";
	}


}
