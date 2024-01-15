package com.teachmeskills.mycourse;


import com.teachmeskills.mycourse.service.pars_document.FileParser;
import com.teachmeskills.mycourse.service.pars_document.Statistic;
import com.teachmeskills.mycourse.exception.CheckNullSession;
import com.teachmeskills.mycourse.exception.NullFileException;
import com.teachmeskills.mycourse.logger.Logger;
import com.teachmeskills.mycourse.service.SigInService;
import com.teachmeskills.mycourse.session.Session;


import java.io.File;
import java.util.Date;
import java.util.Scanner;

//D:\TeachMeSkills\documents
public class Runner {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login");
        String login = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();

        Session session = SigInService.sigIn(login, password);
        System.out.println("Enter path");
        String path = scanner.nextLine();
        try {
            Statistic.startStatistic(session,FileParser.fileParse(new File(path)));
        } catch (CheckNullSession e) {
            Logger.logSession(new Date(), e.getMessage());
        } catch (NullPointerException e) {
            Logger.logErrorInfo(new Date(), e.getMessage(), e);
        }
    }
}

