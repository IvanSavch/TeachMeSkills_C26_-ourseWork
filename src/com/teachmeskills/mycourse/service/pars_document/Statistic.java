package com.teachmeskills.mycourse.service.pars_document;

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

    private void createStats(List<File> files) throws NullFileException {

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

        } else {
            throw new NullFileException("[ERROR] File not found");
        }

    }

    private void printStatistic(Date date, String message, double sum) {
        try {
            String mes = date + " -> " + message + " -> " + sum + "\n";
            Files.write(Paths.get(PATH_STATISTIC), mes.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            Logger.logErrorInfo(new Date(), "[ERROR] Print statistic fail", e);
        }
    }

    public static void startStatistic(Session session, List<File> files) throws CheckNullSession {
        if (session != null) {
            Logger.logSession(new Date(), "[INFO] session start");
            if (session.isSessionAlive()) {
                try {
                    Statistic statistic = new Statistic();
                    statistic.createStats(files);
                } catch (NullFileException e) {
                    Logger.logExecutionInfo(new Date(), e.getMessage());
                }
            } else {
                Logger.logSession(new Date(), "[INFO] Session end");
            }
        } else {
            throw new CheckNullSession("[ERROR] session is null");
        }
    }
}


