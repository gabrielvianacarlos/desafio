package com.contaazul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contaazul.repository.BankSlipRepository;
import com.contaazul.repository.entity.BankSlip;

@Service
public class BankSlipServiceImpl implements BankSlipService {

	@Autowired
	private BankSlipRepository repository;

	@Override
	public void create(BankSlip bankSlip) {
		this.repository.save(bankSlip);
	}

}
