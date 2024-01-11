package com.teachmeskills.mycourse.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceDocument {
    public static double statsInvoice(List<File> files) {
        double amount = 0;
        String strAmount = "";
        for (File file : files) {
            if (file.getName().toLowerCase().contains("invoice")) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.toLowerCase().contains("total")) {
                            Pattern pattern = Pattern.compile("([1-9][0-9]*)+(.[0-9]+})?");
                            Matcher matcher = pattern.matcher(line);
                            while (matcher.find()) {
                                strAmount = line.substring(matcher.start(), matcher.end());
                            }
                            if (strAmount.contains(",")) {
                                strAmount = strAmount.replace(",", "");
                            }
                        }
                    }
                    //добавить ислюченик если сумма не указана
                    amount = amount + Double.parseDouble(strAmount);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return amount;
    }

}
