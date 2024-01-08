package com.teachmeskills.mycourse.validation;

import com.teachmeskills.mycourse.exception.CheckFinancialFileException;
import com.teachmeskills.mycourse.exception.FileFormatException;
import com.teachmeskills.mycourse.exception.FileYearException;

import java.io.File;

public class FileValidation {
    public static void fileValidate(File file) throws FileYearException, FileFormatException,CheckFinancialFileException {
        if (!file.getName().contains("2023")) {
            throw new FileYearException("invalid year");
        }
        if (!file.getName().endsWith(".txt")){
            throw new FileFormatException("invalid document format");
        }
        if (!(file.getName().toLowerCase().contains("order") || file.getName().toLowerCase().contains("invoice") || file.getName().toLowerCase().contains("bill"))) {
            throw new CheckFinancialFileException("invalid financial document name");
        }
    }
}
