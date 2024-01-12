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
    public static List<File> fileParse(File file, Session session) throws NullPointerException, CheckNullSession {
        if (session != null) {
            Logger.logSession(new Date(), "[INFO] session start");
            if (session.isSessionAlive()) {
                // Получаем список файлов из заданной директории
                File[] files = file.listFiles();
                if (files != null) {
                    // Создаем список для хранения валидных файлов
                    List<File> fileList = new ArrayList<>();
                    // Создаем объект для хранения пути к папке для невалидных документов
                    File logFolder = new File("D:\\TeachMeSkills\\invalidDoc");
                    // Проходим по каждому файлу в списке
                    for (File fileDoc : files) {
                        try {
                            // Проверяем валидность файла
                            FileValidation.fileValidate(fileDoc);
                            // Если файл валиден, добавляем его в список валидных файлов
                            fileList.add(fileDoc);
                        } catch (FileYearException | CheckKeyWordException | FileFormatException e) {
                            // Если файл невалиден, логируем информацию о нем
                            Logger.logInvalidDocument(new Date(), fileDoc.getName(), e.getMessage());

                            // Создаем папку для невалидных документов, если она не существует
                            if (!logFolder.exists()) {
                                logFolder.mkdirs();
                            }

                            // Создаем новое местоположение для файла в папке невалидных документов
                            File newLocation = new File(logFolder, fileDoc.getName());

                            // Перемещение невалидного файла в созданную папку
                            Path sourcePath = fileDoc.toPath();
                            Path destinationPath = newLocation.toPath();
                            try {
                                Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                                // Если перемещилось, тогда добавляем имя файла в файл с именами невалидных документов
                            } catch (IOException ex) {
                                Logger.logErrorInfo(new Date(), ex.getMessage(), ex);
                            }
                        }
                    }
                    return fileList;
                } else {
                    throw new NullPointerException("[ERROR] Invalid path");
                }
            } else {
                Logger.logSession(new Date(), "[WRING] Session end");
            }
        } else {
            throw new CheckNullSession("[ERROR] session is null");
        }
        return null;
    }
}
