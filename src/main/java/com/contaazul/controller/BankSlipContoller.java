package com.contaazul.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.contaazul.contants.Constants;
import com.contaazul.exception.BankSlipNotFoundException;
import com.contaazul.repository.entity.BankSlip;
import com.contaazul.service.BankSlipService;
import com.contaazul.validator.BankSlipValidator;

import io.swagger.annotations.ApiOperation;

@RestController
public class BankSlipContoller {

	@Autowired
	private BankSlipService bankSlipService;

	@Autowired
	private BankSlipValidator bankSlipValidator;

	@InitBinder("request")
	public void setupBinder(WebDataBinder binder) {
		binder.addValidators(bankSlipValidator);
	}

	@PostMapping(value = Constants.URI.BASE)
	@ApiOperation(value = Constants.SWAGGER.DOC_CREATE)
	public ResponseEntity<BankSlip> create(@Valid @RequestBody BankSlip request) {
		BankSlip bankslipSaved = this.bankSlipService.create(request);
		return new ResponseEntity<>(bankslipSaved, HttpStatus.CREATED);
	}

	@GetMapping(value = Constants.URI.BASE)
	@ApiOperation(value = Constants.SWAGGER.DOC_RETRIEVE_ALL)
	public List<BankSlip> retriveAll() {
		return this.bankSlipService.retriveAll();
	}

	@DeleteMapping(Constants.URI.PATH_VARIABLE_ID)
	@ApiOperation(value = Constants.SWAGGER.DOC_CANCEL)
	public ResponseEntity<BankSlip> cancelBankSlip(@PathVariable UUID id) {
		this.bankSlipService.cancelBankSlip(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(Constants.URI.URI_NAO_SEI)
	@ApiOperation(value = Constants.SWAGGER.DOC_PAY)
	public ResponseEntity<Object> payBankSlip(@PathVariable UUID id, @RequestBody BankSlip request) {
		return this.bankSlipService.payBankSlip(id, request.getPaymentDate())
				.map(bankslip -> ResponseEntity.noContent().build()).orElseThrow(() -> new BankSlipNotFoundException());
	}

	@GetMapping(Constants.URI.PATH_VARIABLE_ID)
	@ApiOperation(value = Constants.SWAGGER.DOC_RETRIVE_ONE, notes = Constants.SWAGGER.DOC_RETRIVE_ONDE_NOTES)
	public ResponseEntity<BankSlip> retriveBankSlip(@PathVariable UUID id) {
		return this.bankSlipService.findById(id).map(bankslip -> ResponseEntity.ok().body(bankslip))
				.orElseThrow(() -> new BankSlipNotFoundException());
	}

}
