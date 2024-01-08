package com.teachmeskills.mycourse.controller.pars_document;

import com.teachmeskills.mycourse.exception.CheckFinancialFileException;
import com.teachmeskills.mycourse.exception.FileFormatException;
import com.teachmeskills.mycourse.exception.FileYearException;
import com.teachmeskills.mycourse.logger.Logger;
import com.teachmeskills.mycourse.validation.FileValidation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileParser {
    private static final String INVALID_DOCUMENT = "C:\\Users\\Savva\\IdeaProjects\\TeachMeSkills_C26_CourseWork\\log\\invalid_documents\\invalid_document.txt";
    private static final String INVALID_DOCUMENT_PATH = "D:\\orders\\invalidOrders";
    public static List<File> filePars(File file) {
        File[] files = file.listFiles();
        List<File> fileList = new ArrayList<>();
        for (File file1 : files) {
            try {
                FileValidation.fileValidate(file1);
                fileList.add(file1);
            } catch (FileYearException | CheckFinancialFileException | FileFormatException e) {
                Logger.LogErrorInfo(new Date(),e.getMessage(),e);
                String mes = file1.getName() + "\n";
                try {
                    Files.write(Path.of(INVALID_DOCUMENT),mes.getBytes(), StandardOpenOption.APPEND);
                   // написать запись невалидных данных в файл
                } catch (IOException ex) {
                    Logger.LogErrorInfo(new Date(), ex.getMessage(), ex);
                }
            }
        }
        return fileList;
    }
}
