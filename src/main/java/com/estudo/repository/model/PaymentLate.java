package com.estudo.repository.model;

import java.math.BigDecimal;

public enum PaymentLate implements Fine {
	FIRST_RANGE_OF_FINE {
		@Override
		public BigDecimal calculate(BigDecimal totalInCents) {
			return totalInCents.multiply(BigDecimal.valueOf((double) 5 / 100));
		}
	},
	SECOND_RANGE_OF_FINE {
		@Override
		public BigDecimal calculate(BigDecimal totalInCents) {
			return totalInCents.multiply(BigDecimal.valueOf((double) 10 / 100));
		}

	},
	NO_FINE {

		@Override
		public BigDecimal calculate(BigDecimal totalInCents) {
			return null;
		}
	};

	public static PaymentLate valueOf(long days) {
		if (days > 0 && days <= 10) {
			return FIRST_RANGE_OF_FINE;
		} else if (days > 10) {
			return SECOND_RANGE_OF_FINE;
		}
		return NO_FINE;
	}

}
