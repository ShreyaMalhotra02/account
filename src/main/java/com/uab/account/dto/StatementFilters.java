package com.uab.account.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementFilters {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fromDate;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate toDate;
	
	private BigDecimal fromAmount;
	private BigDecimal toAmount;

}
