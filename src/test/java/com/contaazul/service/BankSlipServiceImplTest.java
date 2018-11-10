package com.contaazul.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.contaazul.repository.BankSlipRepository;
import com.contaazul.repository.entity.BankSlip;

@RunWith(SpringRunner.class)
public class BankSlipServiceImplTest {

	@TestConfiguration
	static class BankSlipServiceImplTestContextConfiguration {

		@Bean
		public BankSlipService employeeService() {
			return new BankSlipServiceImpl();
		}
	}

	@Autowired
	private BankSlipService service;

	@MockBean
	private BankSlipRepository repository;

	@Test
	public void shouldCallRepositoryOnceWhenCreateBankSlip() throws Exception {
		BankSlip bankSlip = new BankSlip();
		this.service.create(bankSlip);
		verify(repository, times(1)).save(bankSlip);
	}

	@Test
	public void shouldCallRepositoryOnceRetrieveAllBankSlips() throws Exception {
		this.service.retriveAll();
		verify(repository, times(1)).findAll();
	}

}
