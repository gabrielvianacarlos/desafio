package com.contaazul.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contaazul.repository.BankSlipRepository;
import com.contaazul.repository.entity.BankSlip;
import com.contaazul.repository.model.Status;

@Service
public class BankSlipServiceImpl implements BankSlipService {

	@Autowired
	private BankSlipRepository repository;

	@Override
	public BankSlip create(BankSlip bankSlip) {
		bankSlip.setStatus(Status.PENDING);
		return this.repository.save(bankSlip);
	}

	@Override
	public List<BankSlip> retriveAll() {
		return this.repository.findAll();
	}

}
