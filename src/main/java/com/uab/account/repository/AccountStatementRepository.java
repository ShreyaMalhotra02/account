package com.uab.account.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.uab.account.entity.AccountStatement;

@Repository
public class AccountStatementRepository {

	@PersistenceContext
	private EntityManager entityManager;

	private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
	@SuppressWarnings("unchecked")
	public List<AccountStatement> fetchAccountStatement(Integer accountId) {

		String sql = "select st.accountid, ac.accountnumber, ac.accounttype, st.datefield, st.amount from account ac join statement st on ac.id = st.accountid where ac.id = :accountId";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("accountId", accountId);

		List<Object[]> list = query.getResultList();
		
		return list.stream()
				.map(objects -> new AccountStatement((BigInteger) objects[0], (String) objects[1], (String) objects[2],
						LocalDate.parse((String) objects[3], FORMATTER), new BigDecimal((String) objects[4])))
				.collect(Collectors.toList());
	}

}
