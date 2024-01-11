package com.teachmeskills.mycourse.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Logger {
    private static final String Log_EXECUTION_Info = "C:\\Users\\Savva\\IdeaProjects\\TeachMeSkills_C26_CourseWork\\log\\log_execution\\execution_log.txt";
    private static final String LOG_ERROR_INFO = "C:\\Users\\Savva\\IdeaProjects\\TeachMeSkills_C26_CourseWork\\log\\log_error\\error_log.txt";
    private static final String LOG_INVALID_DOCUMENT = "C:\\Users\\Savva\\IdeaProjects\\TeachMeSkills_C26_CourseWork\\log\\invalid_documents\\invalid_document.txt";

    public static void logExecutionInfo(Date date, String message) {
        try {
            String mes = date + " -> " + message + "\n";
            Files.write(Paths.get(Log_EXECUTION_Info), mes.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {

        }
    }

    public static void logErrorInfo(Date date, String errorMessage, Exception e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date + " -> " + errorMessage);
        stringBuilder.append("\n");
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        for (StackTraceElement element : stackTraceElements) {
            stringBuilder.append(element.toString());
            stringBuilder.append("\n");
        }
        try {
            Files.write(Paths.get(LOG_ERROR_INFO), stringBuilder.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {

        }
    }
    public static void logInvalidDocument(Date date,String message,String fileName) {
        try {
            String mes = date + " -> " + message + " -> " + fileName +"\n";
            Files.write(Paths.get(LOG_INVALID_DOCUMENT), mes.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {

        }
    }
}
