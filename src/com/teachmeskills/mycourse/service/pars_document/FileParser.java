package com.teachmeskills.mycourse.service.pars_document;

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
    private static final String INVALID_DOCUMENT_PATH = "D:\\TeachMeSkills\\invalidDoc";

    public static List<File> fileParse(File file) throws NullPointerException, CheckNullSession {
        File[] files = file.listFiles();
        if (files != null) {

            List<File> fileList = new ArrayList<>();

            File logFolder = new File(INVALID_DOCUMENT_PATH);

            for (File fileDoc : files) {
                try {
                    Logger.logExecutionInfo(new Date(), "[INFO] Check file on validate: " + fileDoc.getName());

                    FileValidation.fileValidate(fileDoc);
                    fileList.add(fileDoc);
                    Logger.logExecutionInfo(new Date(), "[INFO] File validate success: " + fileDoc.getName());
                } catch (FileYearException | CheckKeyWordException | FileFormatException e) {
                    Logger.logInvalidDocument(new Date(), fileDoc.getName(), e.getMessage());

                    if (!logFolder.exists()) {
                        logFolder.mkdirs();
                    }
                    Logger.logExecutionInfo(new Date(), "[INFO] File move on invalid file:" + fileDoc.getName());

                    File newLocation = new File(logFolder, fileDoc.getName());

                    Path sourcePath = fileDoc.toPath();
                    Path destinationPath = newLocation.toPath();
                    try {
                        Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                    } catch (IOException ex) {
                        Logger.logErrorInfo(new Date(), ex.getMessage(), ex);
                    }
                }
            }
            return fileList;
        } else {
            throw new NullPointerException("[ERROR] Invalid path");
        }
    }
}
