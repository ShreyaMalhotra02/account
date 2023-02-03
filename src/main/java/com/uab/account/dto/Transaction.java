package com.uab.account.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

	private LocalDate transactionDate;
	private BigDecimal amount;

}
