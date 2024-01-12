package com.teachmeskills.mycourse.service;

import com.teachmeskills.mycourse.coder.Coder;
import com.teachmeskills.mycourse.exception.CheckLoginException;
import com.teachmeskills.mycourse.exception.CheckPasswordException;
import com.teachmeskills.mycourse.logger.Logger;
import com.teachmeskills.mycourse.session.Session;
import com.teachmeskills.mycourse.validation.CredValidate;

import java.util.Date;

public class SigInService {
    public static boolean sigIn (String login, String password) {
        Logger.logExecutionInfo(new Date(),"start sig in");
        boolean isSigIn = false;
        try {
            CredValidate.loginValidation(login);
            CredValidate.passwordValidation(password);

            Logger.logExecutionInfo(new Date(),"success sig in" );
            isSigIn = true;
        } catch (CheckLoginException | CheckPasswordException e) {
            Logger.logExecutionInfo(new Date(),e.getMessage());
        }

        Logger.logExecutionInfo(new Date(),"end sig in");

        return isSigIn;
    }

}
