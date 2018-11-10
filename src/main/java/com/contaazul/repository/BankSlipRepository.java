package com.contaazul.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contaazul.repository.entity.BankSlip;

@Repository
public interface BankSlipRepository extends JpaRepository<BankSlip, UUID> {

}
