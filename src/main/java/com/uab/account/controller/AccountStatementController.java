package com.uab.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.uab.account.dto.BaseResponse;
import com.uab.account.dto.StatementFilters;
import com.uab.account.service.AccountStatementService;

@RestController
public class AccountStatementController {

	@Autowired
	private AccountStatementService accountStatementService;

	@GetMapping(path = "/accounts/{accountId}/statement", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> viewStatement(@PathVariable(name = "accountId") Integer accountId,
			StatementFilters statementFilters) {

		BaseResponse accountStatementResponse = accountStatementService.getAccountStatement(accountId, statementFilters);
		return new ResponseEntity<>(accountStatementResponse, HttpStatus.OK);
	}
}
