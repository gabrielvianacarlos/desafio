package com.contaazul.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.contaazul.exception.BankSlipNotFoundException;
import com.contaazul.repository.BankSlipRepository;
import com.contaazul.repository.entity.BankSlip;
import com.contaazul.repository.model.Status;

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
	public void shouldCallRepositoryOnceWhenRetrieveAllBankSlips() throws Exception {
		this.service.retriveAll();
		verify(repository, times(1)).findAll();
	}

	@Test
	public void shouldCallRepositoryOnceWhenCancelBankSlipIsOK() {
		BankSlip bankSlip = new BankSlip();
		bankSlip.setStatus(Status.PENDING);

		BankSlip bankSlipMock = new BankSlip();
		UUID id = new UUID(111222, 333444);
		bankSlipMock.setId(id);
		when(repository.save(bankSlip)).thenReturn(bankSlipMock);
		when(repository.findById(id)).thenReturn(Optional.ofNullable(bankSlipMock));

		BankSlip bankSlipSaved = this.repository.save(bankSlip);

		this.service.cancelBankSlip(bankSlipSaved.getId());
		verify(repository, times(1)).save(bankSlipSaved);
	}

	@Test(expected = BankSlipNotFoundException.class)
	public void shouldThrowBankSlipNotFoundExceptionWhenNotFindBankSlipId() {

		UUID id = new UUID(13413, 341341);
		this.service.cancelBankSlip(id);
		verify(repository, times(1)).save(new BankSlip());
	}

}
