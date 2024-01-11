package com.teachmeskills.mycourse.controller.pars_document;

import com.teachmeskills.mycourse.exception.CheckKeyWordException;
import com.teachmeskills.mycourse.exception.CheckNullSession;
import com.teachmeskills.mycourse.exception.FileFormatException;
import com.teachmeskills.mycourse.exception.FileYearException;
import com.teachmeskills.mycourse.logger.Logger;
import com.teachmeskills.mycourse.session.Session;
import com.teachmeskills.mycourse.validation.FileValidation;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileParser {

    private static String INPUT_FOLDER = "D:\\orders\\orders";
    private static String OUTPUT_FOLDER = "D:\\orders\\invalidOrders";

    public static List<File> filePars(File file) throws CheckNullSession {

                File[] files = file.listFiles();
                List<File> fileList = new ArrayList<>();
                for (File fileDoc : files) {
                    try {
                        FileValidation.fileValidate(fileDoc);
                        fileList.add(fileDoc);
                    } catch (FileYearException | CheckKeyWordException | FileFormatException e) {
                        Logger.logInvalidDocument(new Date(), fileDoc.getName(),e.getMessage());
                        // написать запись невалидных данных в файл
                    }
                }
                return fileList;
            }
    }

