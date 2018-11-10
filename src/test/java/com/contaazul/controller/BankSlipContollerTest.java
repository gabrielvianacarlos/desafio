package com.contaazul.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.contaazul.DesafioApplication;
import com.contaazul.contants.Constants;
import com.contaazul.repository.entity.BankSlip;
import com.contaazul.repository.model.Status;
import com.contaazul.service.BankSlipService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DesafioApplication.class)
@AutoConfigureMockMvc
@Transactional
public class BankSlipContollerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private BankSlipService service;

	@Test
	public void shouldCreateAndReturnStatus201() throws Exception {

		JSONObject request = new JSONObject();
		request.put("due_date", "2019-01-03");
		request.put("total_in_cents", "100000");
		request.put("customer", "Gabriel");

		mock.perform(post(Constants.URI.BASE).content(request.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void shouldReturnStatus400WhenBankSlipNotProvided() throws Exception {

		mock.perform(post(Constants.URI.BASE).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void shouldReturnStatus422WhenInvalidBankSlipProvided() throws Exception {

		JSONObject request = new JSONObject();
		request.put("due_date", "2018-01-03");
		request.put("total_in_cents", "100");
		request.put("customer", null);

		mock.perform(post(Constants.URI.BASE).content(request.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnprocessableEntity());

	}

	@Test
	public void shouldReturn200WhenGetAllBankSlips() throws Exception {

		BankSlip bankSlip1 = createBankSlip("Gabriel", new Date(), null, new BigDecimal(1000));
		service.create(bankSlip1);

		BankSlip bankSlip2 = createBankSlip("Viana", new Date(), null, new BigDecimal(2000));
		service.create(bankSlip2);

		mock.perform(get(Constants.URI.BASE).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].customer", is("Gabriel")))
				.andExpect(jsonPath("$[1].customer", is("Viana")));

	}

	@Test
	public void shouldReturnStatus204WhenCancelBankSlipIsOk() throws Exception {
		BankSlip bankSlip = createBankSlip("Gabriel", new Date(), null, new BigDecimal(1000));
		BankSlip saved = service.create(bankSlip);

		mock.perform(delete(Constants.URI.PATH_VARIABLE_ID, saved.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void shouldReturnStatus404WhenBankSlipToCancelNotFound() throws Exception {
		mock.perform(delete(Constants.URI.PATH_VARIABLE_ID, "123e4567-e89b-12d3-a456-556642440000")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	private BankSlip createBankSlip(String customer, Date dueDate, Status status, BigDecimal totalInCents) {
		BankSlip bankSlip = new BankSlip();
		bankSlip.setCustomer(customer);
		bankSlip.setDueDate(dueDate);
		bankSlip.setStatus(status);
		bankSlip.setTotalInCents(totalInCents);
		return bankSlip;
	}

}
