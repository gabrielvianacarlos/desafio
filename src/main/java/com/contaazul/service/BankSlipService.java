package com.contaazul.service;

import java.util.List;

import com.contaazul.repository.entity.BankSlip;

public interface BankSlipService {
	BankSlip create(BankSlip bankSlip);

	List<BankSlip> retriveAll();
}
