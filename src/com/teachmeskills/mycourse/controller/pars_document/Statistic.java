package com.teachmeskills.mycourse.controller.pars_document;

import com.teachmeskills.mycourse.logger.Logger;
import com.teachmeskills.mycourse.model.CheckDocument;
import com.teachmeskills.mycourse.model.InvoiceDocument;
import com.teachmeskills.mycourse.model.OrderDocument;


import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Statistic {
    FileReader fileReader;

    public static void stats(List<File> files) {
        // проверить лист на null
        if (files != null) {
            double total = InvoiceDocument.statsInvoice(files)
                    + CheckDocument.statsCheck(files)
                    + OrderDocument.statsOrder(files);
            System.out.println("total turnover: " + total);
            System.out.println("total turnover by invoice: " + InvoiceDocument.statsInvoice(files));
            System.out.println("total turnover by order: " + OrderDocument.statsOrder(files));
            System.out.println("total turnover by check: " + CheckDocument.statsCheck(files));
            Logger.logExecutionInfo(new Date(), "[INFO Statistic accesses]");
        }

    }


}
