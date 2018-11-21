package com.estudo.repository.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.estudo.repository.model.PaymentLate;

@RunWith(SpringRunner.class)
public class PaymentLateTest {

	@Test
	public void shouldReturnFineOfHalfAPercentWhenPaymentIsLateLessThanTenDays() {
		BigDecimal fine = PaymentLate.valueOf(5).calculate(new BigDecimal(1000));
		assertThat(fine, comparesEqualTo(new BigDecimal(50)));
	}

	@Test
	public void shouldReturnFineOfHalfAPercentWhenPaymentIsLateForTenDays() {
		BigDecimal fine = PaymentLate.valueOf(5).calculate(new BigDecimal(1000));
		assertThat(fine, comparesEqualTo(new BigDecimal(50)));
	}

	@Test
	public void shouldReturnFineOfHalfAPercentWhenPaymentIsLateForOneDay() {
		BigDecimal fine = PaymentLate.valueOf(5).calculate(new BigDecimal(1000));
		assertThat(fine, comparesEqualTo(new BigDecimal(50)));
	}

	@Test
	public void shouldReturnFineOfZeroWhenPaymentIsNotLate() {
		BigDecimal fine = PaymentLate.valueOf(0).calculate(new BigDecimal(1000));
		assertNull(fine);
	}

	@Test
	public void shouldReturnFineOfOnePercentWhenPaymentIsLateMoreThanTenDays() {
		BigDecimal fine = PaymentLate.valueOf(11).calculate(new BigDecimal(1000));
		assertThat(fine, comparesEqualTo(new BigDecimal(100)));
	}

}
