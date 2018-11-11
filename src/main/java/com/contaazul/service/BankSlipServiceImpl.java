package com.contaazul.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contaazul.exception.BankSlipNotFoundException;
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

	@Override
	public void cancelBankSlip(UUID id) {
		Optional<BankSlip> bankSlipSaved = this.repository.findById(id);
		if (bankSlipSaved.isPresent()) {
			BankSlip bankSlip = bankSlipSaved.get();
			bankSlip.setStatus(Status.CANCELED);
			this.repository.save(bankSlip);
		} else {
			throw new BankSlipNotFoundException();
		}
	}

	@Override
	public Optional<BankSlip> payBankSlip(UUID id, Date paymentDate) {
		Optional<BankSlip> bankslipOptional = this.repository.findById(id);
		if (bankslipOptional.isPresent()) {
			BankSlip bankSlip = bankslipOptional.get();
			bankSlip.setPaymentDate(paymentDate);
			bankSlip.setStatus(Status.PAID);
			return Optional.of(this.repository.save(bankSlip));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<BankSlip> findById(UUID id) {
		Optional<BankSlip> bankslipSaved = this.repository.findById(id);

		if (bankslipSaved.isPresent()) {
			BankSlip bankSlip = bankslipSaved.get();
			bankSlip.setFine(getFine(bankSlip.getDueDate(), bankSlip.getTotalInCents()));
			return Optional.of(bankSlip);
		} else {
			return Optional.empty();
		}
	}

	public BigDecimal getFine(Date dueDate, BigDecimal totalInCents) {
		BigDecimal fine = null;
		long diff = getDifferenceInDaysOfTwoDates(dueDate);
		if (diff > 0 && diff <= 10) {
			fine = totalInCents.multiply(BigDecimal.valueOf((double) 5 / 100));
		} else if (diff > 10) {
			fine = totalInCents.multiply(BigDecimal.valueOf((double) 10 / 100));
		}
		return fine;
	}

	private long getDifferenceInDaysOfTwoDates(Date dueDate) {

		java.time.temporal.Temporal now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
		LocalDateTime localDate = LocalDateTime.parse(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(dueDate));

		Duration duration = Duration.between(localDate, now);
		return duration.toDays();

	}

}
