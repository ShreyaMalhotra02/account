package com.uab.account.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountStatement {

	private BigInteger accountId;
	private String accountNumber;
	private String accountType;
	private LocalDate dateField;
	private BigDecimal amount;

}
