package com.contaazul.service;

import java.util.List;
import java.util.UUID;

import com.contaazul.repository.entity.BankSlip;

public interface BankSlipService {
	BankSlip create(BankSlip bankSlip);

	List<BankSlip> retriveAll();

	void cancelBankSlip(UUID id);
}
