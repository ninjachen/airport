package com.wonders.security.entity;

import static javax.persistence.TemporalType.DATE

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Version
import javax.validation.constraints.NotNull

import org.springframework.data.jpa.domain.AbstractPersistable

@Entity
@Table(name = "data_dictionary_manage")
class DictionaryManage extends AbstractPersistable<Long> {

	@Version
	int version

	@NotNull
	String name
	
	String typecode

/*	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	Dictionary dictionary*/
	
	String description
	
}
