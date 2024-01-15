package com.teachmeskills.mycourse.document;

import com.teachmeskills.mycourse.currency_convert.CurrencyConvert;
import com.teachmeskills.mycourse.logger.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CheckDocument {
    public static double statsCheck(List<File> files) {
        double totalAmount = 0;
        String strAmount;
        for (File file : files) {
            if (file.getName().toLowerCase().contains("bill")) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.toLowerCase().contains("total") || line.toLowerCase().contains("$")) {
                            Pattern pattern = Pattern.compile("([1-9][0-9]*)(\\.[0-9]+)?");
                            Matcher matcher = pattern.matcher(line);
                            while (matcher.find()) {
                                strAmount = line.substring(matcher.start(), matcher.end());
                                if (strAmount.contains(",")) {
                                    strAmount = strAmount.replace(",", "");
                                }
                                double amount = Double.parseDouble(strAmount);
                                if (line.toLowerCase().contains("euro")) {
                                    amount = CurrencyConvert.convertToUSD(amount, "EUR");
                                }
                                if (line.toLowerCase().contains("gbp")) {
                                    amount = CurrencyConvert.convertToUSD(amount, "GBP");
                                }
                                totalAmount += amount;
                                totalAmount = Math.round(totalAmount * 100.0) / 100.0;
                            }
                        }
                    }
                } catch (IOException e) {
                    Logger.logErrorInfo(new Date(), e.getMessage(), e);
                }
            }
        }
        return totalAmount;
    }
}
