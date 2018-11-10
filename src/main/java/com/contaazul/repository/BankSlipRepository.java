package com.contaazul.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contaazul.repository.entity.BankSlip;

public interface BankSlipRepository extends JpaRepository<BankSlip, UUID> {

}
