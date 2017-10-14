package com.file.sharing.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

/******
 * 
 * @author Alexandru
 *
 */
@Entity
@Table(name = "user") // TODO Add default schema and remove prefix (or schema declaration)
@Lazy(value = false) // Because there is a 1:1 relation, there will not be a significant stress for
// the DB even in get all users scenario
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
	@SequenceGenerator(name = "user_gen", sequenceName = "seq_id_user", allocationSize = 1)
	private Integer id;

	private String name;

	private String surname;

	private String password;

	@Column(unique = true)
	private String email;
	
	@Lob
	@Column(columnDefinition="bytea")
	private byte[] picture;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accstats_id")
	private AccStats accStats;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private Role role;

	public User() {
	}

	public User(Integer id, String name, String surname, String password, String email, byte[] picture,
			AccStats accStats, Role role) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.picture = picture;
		this.accStats = accStats;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surName) {
		this.surname = surName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public AccStats getAccStats() {
		return accStats;
	}

	public void setAccStats(AccStats accStats) {
		this.accStats = accStats;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return email.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof User)) {
			return false;
		}

		User user = (User) obj;
		return this.email.equals(user.getEmail());

	}

}
