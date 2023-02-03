package com.uab.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "account")
public class Account {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "accounttype")
	private String accountType;
	
	@Column(name = "accountnumber")
	private String accountNumber;

}
