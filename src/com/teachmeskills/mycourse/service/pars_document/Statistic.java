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
    private static final String PATH_STATISTIC = "D:\\C26\\TeachMeSkills_C26_CourseWork-main\\TeachMeSkills_C26_CourseWork-main\\log\\statistic\\statistic.txt";

/**
This method takes a list of files as input and writes statistics to a separate file.
 **/
    private static void createStats(List<File> files) throws NullFileException {
        Logger.logExecutionInfo(new Date(),"[INFO] Start create statistic");
        if (files != null) {
            double total;

            total = InvoiceDocument.statsInvoice(files)
                    + CheckDocument.statsCheck(files)
                    + OrderDocument.statsOrder(files);


            printStatistic(new Date(), "total turnover by invoice: ", InvoiceDocument.statsInvoice(files));
            Logger.logExecutionInfo(new Date(),"[INFO] Create statistic invoice success");
            printStatistic(new Date(), "total turnover by order: ", OrderDocument.statsOrder(files));
            Logger.logExecutionInfo(new Date(),"[INFO] Create statistic order success");
            printStatistic(new Date(), "total turnover by bill: ", CheckDocument.statsCheck(files));
            Logger.logExecutionInfo(new Date(),"[INFO] Create statistic bill success");
            printStatistic(new Date(), "total turnover: ", total);
            Logger.logExecutionInfo(new Date(),"[INFO] Create statistic turnover success");

            Logger.logExecutionInfo(new Date(), "[INFO Statistic accesses]");

        } else {
            throw new NullFileException("[ERROR] File not found");
        }

    }

    /**
     *Method for writing statistics to a file.
     */
    private static void printStatistic(Date date, String message, double sum) {
        try {
            String mes = date + "->" + message + "->" + sum + "$" +"\n";
            Files.write(Paths.get(PATH_STATISTIC), mes.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            Logger.logErrorInfo(new Date(), "[ERROR] Print statistic fail", e);
        }
    }

    /**
     * Method for starting statistics recording
     */
    public static void startStatistic(Session session, List<File> files) throws CheckNullSession, NullFileException {
        if (session != null) {
            Logger.logSession(new Date(), "[INFO] session start");
            if (session.isSessionAlive()) {
                if (files == null) {
                    throw new NullFileException("[ERROR] Missing file");
                }
                try {
                    createStats(files);
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


