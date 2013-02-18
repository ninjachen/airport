package com.wonders.security.entity;

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OrderBy
import javax.persistence.Table
import javax.persistence.Version
import javax.validation.constraints.NotNull

import org.springframework.data.jpa.domain.AbstractPersistable

@Entity
@Table(name = "sec_account")
public class Account extends AbstractPersistable<Long> {
	
	@Version
	int version

	@NotNull
	@Column(name = "login_name", unique = true)
	String loginName
	
	@NotNull
	String password
	
	boolean enabled
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	User user
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	Group group

	@ManyToMany
	@JoinTable(name = "sec_acc_role",
		joinColumns = @JoinColumn(name = "acc_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	@OrderBy
	Set<Role> roles = new HashSet<>(0)
	
	@ManyToMany
	@JoinTable(name = "sec_acc_auth",
		joinColumns = @JoinColumn(name = "acc_id"),
		inverseJoinColumns = @JoinColumn(name = "auth_id")
	)
	@OrderBy
	Set<Authority> authorities = new HashSet<>(0)
	
	boolean getEnabled() {
		this.enabled
	}
	
}