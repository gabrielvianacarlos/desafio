package com.contaazul.repository.entity;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.contaazul.repository.model.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
public class BankSlip implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7561422027033170965L;

	@Id
	@Getter
	@Setter
	@GeneratedValue
	private UUID id;

	@Getter
	@Setter
	@NotNull
	@Column(name = "due_date", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonProperty(value = "due_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@ApiModelProperty(notes = "Formato: yyyy-MM-dd")
	private Date dueDate;

	@Getter
	@Setter
	@NotNull
	@Column(name = "total_in_cents", nullable = false)
	@JsonProperty(value = "total_in_cents")
	@ApiModelProperty(notes = "valor em centavos")
	private BigDecimal totalInCents;

	@Getter
	@Setter
	@NotNull
	@Column(nullable = false)
	private String customer;

	@Getter
	@Setter
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@Getter
	@Setter
	@Temporal(TemporalType.DATE)
	@Column(name = "payment_date")
	@JsonProperty(value = "payment_date")
	private Date paymentDate;

	@Getter
	@Setter
	@Transient
	private BigDecimal fine;

}
