package com.contaazul.repository.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.contaazul.repository.model.Status;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class BankSlip {

	@Id
	@GeneratedValue
	private UUID id;

	@Setter
	@NotNull
	@Column(name = "due_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dueDate;

	@Setter
	@NotNull
	@Column(name = "total_in_cents", nullable = false)
	private BigDecimal totalInCents;

	@Setter
	@NotNull
	@Column(nullable = false)
	private String customer;

	@Setter
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
}
