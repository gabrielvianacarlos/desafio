package com.contaazul.repository.model;

import java.math.BigDecimal;

public interface Fine {
	BigDecimal calculate(BigDecimal totalInCents);
}
