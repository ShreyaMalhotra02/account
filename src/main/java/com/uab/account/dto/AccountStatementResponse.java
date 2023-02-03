package com.uab.account.dto;

import java.math.BigInteger;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountStatementResponse extends BaseResponse {

	private BigInteger accountId;
	private String accountNumber;
	private String accountType;
	private List<Transaction> transactions;
	
	public AccountStatementResponse withSuccess() {
		this.setResponseCode(200);
		this.setResponseStatus("Success");
		return this;
	}
}
