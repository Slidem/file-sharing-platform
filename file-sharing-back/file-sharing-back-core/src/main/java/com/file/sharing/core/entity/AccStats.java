package com.file.sharing.core.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.file.sharing.common.user.AccountStatus;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
@Entity
@Table(name = "acc_stats", schema = "public")
public class AccStats {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;

	@Column(name = "status")
	@NotNull
	@Enumerated(EnumType.STRING)
	private AccountStatus status;

	/**
	 *
	 */
	public AccStats() {
	}

	/**
	 * @param subscription
	 * @param status
	 */
	public AccStats(Subscription subscription, AccountStatus status) {
		this.subscription = subscription;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

}