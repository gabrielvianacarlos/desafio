package com.estudo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudo.repository.entity.BankSlip;

@Repository
public interface BankSlipRepository extends JpaRepository<BankSlip, UUID> {

}
