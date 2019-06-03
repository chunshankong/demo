package rpa.compensate;

import java.io.*;
import java.util.Properties;

//关于Properties类常用的操作
public class PropertiesUtil {

    public static final String filePath ;
    static {
        filePath = System.getProperty("user.dir")+File.separator+"config.properties";
    }

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        System.out.println(path);
        String cron = getValueByKey("cron");
        System.out.println(cron);

     String   driverPath =
        System.getProperty("user.dir")+File.separator+"driver"+File.separator+"IEDriverServer_Win32_3.14.0"+File.separator+"IEDriverServer.exe";
        System.out.println(driverPath);

    }
    public static String getValueByKey(String key){
        return getProperties(filePath).getProperty(key);
    }

    private static Properties getProperties(String fileName) {
        try {
            System.out.println(fileName);
            Properties properties = new Properties();
            InputStream in = new FileInputStream(new File(fileName));
            properties.load(in);
            return properties;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            try {
                Properties properties = new Properties();
                InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);//默认加载classpath的
                properties.load(in);
                return properties;
            } catch (IOException es) {
                System.out.println(es.getMessage());
                return null;
            }
        }
    }



}
