package com.gas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class ManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(ManagementApplication.class, args);
        System.err.println(
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t启动成功！！！\n" +
                        "oooooooo8   o8                           o8                                                                                      o888o            o888  \n" +
                        "888        o888oo  ooooooo   oo oooooo  o888oo   oooooooo8 oooo  oooo   ooooooo     ooooooo    ooooooooo8  oooooooo8  oooooooo8 o888oo oooo  oooo   888  \n" +
                        " 888oooooo  888    ooooo888   888    888 888    888ooooooo  888   888 888     888 888     888 888oooooo8  888ooooooo 888ooooooo  888    888   888   888  \n" +
                        "        888 888  888    888   888        888            888 888   888 888         888         888                 888        888 888    888   888   888  \n" +
                        "o88oooo888   888o 88ooo88 8o o888o        888o  88oooooo88   888o88 8o  88ooo888    88ooo888    88oooo888 88oooooo88 88oooooo88 o888o    888o88 8o o888o "
        );

    }

}
