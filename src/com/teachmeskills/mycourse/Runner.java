package com.teachmeskills.mycourse;


import com.teachmeskills.mycourse.controller.pars_document.FileParser;
import com.teachmeskills.mycourse.controller.pars_document.Statistic;
import com.teachmeskills.mycourse.exception.CheckNullSession;
import com.teachmeskills.mycourse.exception.NullFileException;
import com.teachmeskills.mycourse.logger.Logger;
import com.teachmeskills.mycourse.service.SigInService;
import com.teachmeskills.mycourse.session.Session;
import com.teachmeskills.mycourse.util.Const;

import java.io.File;
import java.util.Date;


public class Runner {
    public static void main(String[] args) throws CheckNullSession {
        if (SigInService.sigIn("ivan_arthur", Const.password)){
            String path = "D:\\order";
            try {
                Statistic.stats(FileParser.filePars(new File(path)),new Session());
            } catch (NullFileException e) {
                Logger.logExecutionInfo(new Date(), e.getMessage());
            }
        }
    }
}
