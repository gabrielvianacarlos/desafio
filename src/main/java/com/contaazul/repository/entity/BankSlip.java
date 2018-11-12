package com.contaazul.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
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

import com.contaazul.contants.Constants;
import com.contaazul.repository.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat(pattern = Constants.DATE_PATTERN.YYYY_MM_DD, locale = Constants.LOCALE.PT_BR, timezone = Constants.TIME_ZONE.AMERICA_SAO_PAULO)
	@DateTimeFormat(pattern = Constants.DATE_PATTERN.YYYY_MM_DD)
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
	@JsonFormat(pattern = Constants.DATE_PATTERN.YYYY_MM_DD, locale = Constants.LOCALE.PT_BR, timezone = Constants.TIME_ZONE.AMERICA_SAO_PAULO)
	@DateTimeFormat(pattern = Constants.DATE_PATTERN.YYYY_MM_DD)
	private Date paymentDate;

	@Getter
	@Setter
	@Transient
	private BigDecimal fine;

	public long calculateDaysOfLate() {
		java.time.temporal.Temporal now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
		LocalDateTime localDate = LocalDateTime
				.parse(new SimpleDateFormat(Constants.DATE_PATTERN.YYYY_MM_DD_T_HH_MM_SS).format(this.dueDate));

		Duration duration = Duration.between(localDate, now);
		return duration.toDays();
	}

}
