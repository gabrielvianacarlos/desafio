package com.estudo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.estudo.repository.entity.BankSlip;

public interface BankSlipService {
	BankSlip create(BankSlip bankSlip);

	List<BankSlip> retriveAll();

	void cancelBankSlip(UUID id);

	Optional<BankSlip> payBankSlip(UUID id, Date paymentDate);

	Optional<BankSlip> findById(UUID id);
}
