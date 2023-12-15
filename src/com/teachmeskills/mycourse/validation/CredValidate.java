package com.teachmeskills.mycourse.validation;

import com.teachmeskills.mycourse.exception.CheckLoginException;
import com.teachmeskills.mycourse.exception.CheckPasswordException;
import com.teachmeskills.mycourse.util.Const;

public class CredValidate {
    public static void passwordValidation(String password) throws CheckPasswordException {
        if (!password.matches(".*\\d{1,}.*")) {
            throw new CheckPasswordException("wrong password or login");
        }
        if (password.contains(" ")) {
            throw new CheckPasswordException("wrong password or login");
        }
        if (!password.equals(Const.password)){
            throw new CheckPasswordException("wrong password or login");
        }
    }
    public static void sigInValidation(String login)throws CheckLoginException {
        if (login.contains(" ")) {
            throw new CheckLoginException("wrong password or login");
        }
        if (!login.equals(Const.login)) {
            throw new CheckLoginException("wrong password or login");
        }
    }
}
