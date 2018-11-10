package com.contaazul.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DesafioApplication.class)
@AutoConfigureMockMvc
@Transactional
public class BankSlipContollerTest {

	@Autowired
	private MockMvc mock;

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

}
