package com.uab.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "statement")
public class Statement {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "accountid")
	private Long accountId;

	@Column(name = "datefield")
	private String dateField;

	@Column(name = "amount")
	private String amount;

}
