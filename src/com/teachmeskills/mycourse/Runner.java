package com.teachmeskills.mycourse;

import com.teachmeskills.mycourse.service.SigInService;

public class Runner {
    public static void main(String[] args) {
        SigInService.sigIn("ivan_artur","root1");
        SigInService.pathFile("D:\\orders\\orders");
    }
}
