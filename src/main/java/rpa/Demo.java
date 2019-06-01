package rpa;

import rpa.compensate.PropertiesUtil;

import java.io.File;
import java.io.InputStream;

public class Demo {


    public static void main(String[] args) {

        String path = PropertiesUtil.class.getClassLoader().getResource("bailAccountIdModifyPage.html").getPath();
        System.out.println(path);


    }
}
