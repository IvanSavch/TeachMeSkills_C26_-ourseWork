package com.teachmeskills.mycourse;

import com.teachmeskills.mycourse.coder.Coder;
import com.teachmeskills.mycourse.controller.pars_document.FileParser;
import com.teachmeskills.mycourse.controller.pars_document.Statistic;
import com.teachmeskills.mycourse.exception.CheckNullSession;
import com.teachmeskills.mycourse.service.SigInService;
import com.teachmeskills.mycourse.session.Session;
import com.teachmeskills.mycourse.util.Const;

import java.io.File;

import static com.teachmeskills.mycourse.util.Const.login;

public class Runner {
    public static void main(String[] args) throws CheckNullSession {
        Session session = SigInService.sigIn("ivan_arthur", "root1");
        String path = "D:\\order";
        Statistic.stats(FileParser.filePars(new File(path), session));


    }
}
