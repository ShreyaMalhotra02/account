package com.uab.account.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.uab.account.dto.AccountStatementResponse;
import com.uab.account.dto.Transaction;
import com.uab.account.dto.StatementFilters;
import com.uab.account.entity.AccountStatement;
import com.uab.account.exception.AccountNotFoundException;
import com.uab.account.exception.InvalidRequestException;
import com.uab.account.repository.AccountStatementRepository;
import com.uab.account.util.MaskHelper;

@Service
public class AccountStatementService {

	@Autowired
	private AccountStatementRepository accountStatementRepository;

	public AccountStatementResponse getAccountStatement(Integer accountId, StatementFilters statementFilters) {
		validate(statementFilters);
		List<AccountStatement> accountStatements = accountStatementRepository.fetchAccountStatement(accountId);

		if (CollectionUtils.isEmpty(accountStatements)) {
			throw new AccountNotFoundException("Account not found with given account id");
		}

		List<AccountStatement> filteredAccountStatements = filterStatement(accountStatements, statementFilters);
		
		if(CollectionUtils.isEmpty(filteredAccountStatements)) {
			return buildAccountStatementWithoutTransactions(accountStatements);
		}
		return buildAccountStatementResponse(filteredAccountStatements);
	}

	private List<AccountStatement> filterStatement(List<AccountStatement> accountStatements,
			StatementFilters statementFilters) {

		if (Objects.isNull(statementFilters.getFromAmount()) && Objects.isNull(statementFilters.getFromDate())) {
			LocalDate to = LocalDate.now().plusDays(1);
			LocalDate from = LocalDate.now().minusMonths(3).minusDays(1);

			return accountStatements.stream()
					.filter(stmt -> stmt.getDateField().isAfter(from) && stmt.getDateField().isBefore(to))
					.collect(Collectors.toList());

		} else if (!Objects.isNull(statementFilters.getFromDate())) {
			LocalDate to = statementFilters.getToDate().plusDays(1);
			LocalDate from = statementFilters.getFromDate().minusDays(1);

			return accountStatements.stream()
					.filter(stmt -> stmt.getDateField().isAfter(from) && stmt.getDateField().isBefore(to))
					.collect(Collectors.toList());
		} else {
			return accountStatements.stream()
					.filter(stmt -> stmt.getAmount().compareTo(statementFilters.getFromAmount()) > 0
							&& stmt.getAmount().compareTo(statementFilters.getToAmount()) < 0)
					.collect(Collectors.toList());
		}

	}

	private AccountStatementResponse buildAccountStatementResponse(List<AccountStatement> accountStatements) {
		AccountStatementResponse accountStatementResponse = new AccountStatementResponse().withSuccess();

		accountStatementResponse.setAccountId(accountStatements.get(0).getAccountId());
		accountStatementResponse.setAccountType(accountStatements.get(0).getAccountType());
		accountStatementResponse.setAccountNumber(MaskHelper.maskNumber(accountStatements.get(0).getAccountNumber()));
		List<Transaction> transactions = accountStatements.stream()
				.map(accountStatement -> new Transaction(accountStatement.getDateField(), accountStatement.getAmount()))
				.collect(Collectors.toList());

		accountStatementResponse.setTransactions(transactions);
		return accountStatementResponse;
	}
	
	private AccountStatementResponse buildAccountStatementWithoutTransactions(
			List<AccountStatement> accountStatements) {
		AccountStatementResponse accountStatementResponse = new AccountStatementResponse().withSuccess();

		accountStatementResponse.setAccountId(accountStatements.get(0).getAccountId());
		accountStatementResponse.setAccountType(accountStatements.get(0).getAccountType());
		accountStatementResponse.setAccountNumber(MaskHelper.maskNumber(accountStatements.get(0).getAccountNumber()));
		accountStatementResponse.setTransactions(Collections.emptyList());
		return accountStatementResponse;
	}

	private void validate(StatementFilters viewStatementRequest) {

		if ((!Objects.isNull(viewStatementRequest.getFromDate()) && Objects.isNull(viewStatementRequest.getToDate()))
				|| (Objects.isNull(viewStatementRequest.getFromDate())
						&& !Objects.isNull(viewStatementRequest.getToDate()))) {
			throw new InvalidRequestException("FromDate and ToDate both are mandatory to view statement");
		}

		if ((!Objects.isNull(viewStatementRequest.getFromAmount())
				&& Objects.isNull(viewStatementRequest.getToAmount()))
				|| (Objects.isNull(viewStatementRequest.getFromAmount())
						&& !Objects.isNull(viewStatementRequest.getToAmount()))) {
			throw new InvalidRequestException("FromAmount and ToAmount both are mandatory to view statement");
		}

	}

}
