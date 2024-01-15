package com.teachmeskills.mycourse.currency_convert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConvert {
    /**
     * Метод для конвертации валют
     *
     */
    public static double convertToUSD(Double amount, String currencyCode) {
        Double convertedAmount;

        switch(currencyCode.toLowerCase()) {
            case "eur":
                convertedAmount = amount * 1.09;
                break;
            case "gbp":
                convertedAmount = amount * 1.27;
                break;
            default:
                convertedAmount = amount;  // По умолчанию считаем, что сумма уже в USD
        }

        return convertedAmount;
    }

}
