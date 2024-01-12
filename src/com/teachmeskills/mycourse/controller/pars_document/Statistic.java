package com.teachmeskills.mycourse.controller.pars_document;

import com.teachmeskills.mycourse.exception.CheckNullSession;
import com.teachmeskills.mycourse.exception.NullFileException;
import com.teachmeskills.mycourse.logger.Logger;
import com.teachmeskills.mycourse.document.CheckDocument;
import com.teachmeskills.mycourse.document.InvoiceDocument;
import com.teachmeskills.mycourse.document.OrderDocument;
import com.teachmeskills.mycourse.session.Session;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.List;

public class Statistic {
    private static final String PATH_STATISTIC = "C:\\Users\\Savva\\IdeaProjects\\TeachMeSkills_C26_CourseWork\\log\\statistic\\statistic.txt";

    public static void stats(List<File> files, Session session) throws CheckNullSession, NullFileException {
        if (session != null) {
            Logger.logSession(new Date(), "[INFO] session start");
            if (session.isSessionAlive()) {
                if (files != null) {
                    double total;

                    total = InvoiceDocument.statsInvoice(files)
                            + CheckDocument.statsCheck(files)
                            + OrderDocument.statsOrder(files);

                    printStatistic(new Date(), "total turnover: ", total);
                    printStatistic(new Date(), "total turnover by invoice: ", InvoiceDocument.statsInvoice(files));
                    printStatistic(new Date(), "total turnover by order: ", OrderDocument.statsOrder(files));
                    printStatistic(new Date(), "total turnover by check: ", CheckDocument.statsCheck(files));

                    Logger.logExecutionInfo(new Date(), "[INFO Statistic accesses]");

                }else {
                    throw new NullFileException("[ERROR] File not found");
                }
            } else {
                Logger.logSession(new Date(), "[INFO] Session end");
            }
        } else {
            throw new CheckNullSession("[ERROR] session is null");
        }
    }

    public static void printStatistic(Date date, String message, double sum) {
        try {
            String mes = date + " -> " + message + " -> " + sum + "\n";
            Files.write(Paths.get(PATH_STATISTIC), mes.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            Logger.logErrorInfo(new Date(), "[ERROR] Print statistic fail", e);
        }
    }

}


