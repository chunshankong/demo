package rpa.compensate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangsiguo
 * @date 2019/5/31
 * @desc TODO add description in here
 */
public class IE {

    private final static String compensatePassword ;
    static {
        compensatePassword = PropertiesUtil.GetValueByKey("config.properties","compensate-password");
    }

    public static void main(String[]args) {

//        executeClickJob();

        //110
        // 19030114057996410001
        // 200100
        // DT4lDQM2Qz8IbJE6yJeumKlOluGV++heGEgVpijH/q4esNI6n0je+RWlWVNfaa2TVKFioa+Wpf74joM5q2vZfh6E1QbTA5+dFHk7u1edObabhSdib5eKppyDpvkxtJ+F3D+W9cRoANuoJT6PPE04JS2W3miEKMXTDy3QDF6MErGb7W/zak/Gy6NCYTEfUfQwYO0TgLhZhCZBdunnJKqFAaoGkFWLICHTr8024bTGXrREzY94rlwAOPcKP127U9q8FNF6KT4ugG7P3YBlooEr0vc1EcE8aqE0BCk4aEAbm5Xw3u0J3pO63hvXx/8/YKIygxFry/voL0qOlViELM2FPg==
        // 164225
        Map<String, String> map = new HashMap<>();
        map.put("id","89");
        map.put("product_id","19030114052550510001");
        map.put("seq_no","200079");
        map.put("sign","UhsNRPBeCkVIyrhiwUJqHw/BvRBFsiMzNBh1Wgv6nYrFC+gTS1HfI2M7BhoLV8jjNoV9DFLJahjRotcVwDwhFvaYHH5VNXJOEy7gD5qEntJynqziiIrKDqGHjfshekXDpPy5aadV2KOBL+Ka2J1HZIPux4Nhz1W3zJuywDEGNlQe+mJQpdluce0PQHcOX2fJ6uCyP2S8DJ6Ndk9dHQnZwXtKHMIG7R3ocXVNAQaOKbTS5idilFj7i2ojEHNu5IDY8/oWnDWAtZHBecgZ/L71zMpo7AECyZCJar2I533SWNMb3ry71j0hETgspeSogQAXlqVqjAz7XanqBow179TNeA==");
        map.put("tx_time","164225");
        modifyPage(map);

    }

    public static boolean modifyPage(Map<String,String> map){

        System.out.println("start selenium");
        WebDriver driver = null;
        boolean success = false;
        try {
            // ie启动成功,files是启动ie驱动
            System.setProperty("webdriver.ie.driver", "C:\\Users\\yangsiguo\\Desktop\\IEDriverServer_Win32_3.8.0\\IEDriverServer.exe");
//        代码关闭IE一些配置
            DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
            dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            dc.setCapability("ignoreProtectedModeSettings", true);
//        把加载关闭配置加载到IE浏览器
            driver = new InternetExplorerDriver(dc);

            success = modifyPage(driver,map);

        }catch (Throwable e){

            e.printStackTrace();
            success = false;
        }finally {
            driver.close();
            driver.quit();
        }

        //update DB
        if (success){
            DBService.updateStateById(State.SUCCESS.getValue(),map.get("id"));
        }else {
            DBService.updateStateById(State.FAILED.getValue(),map.get("id"));
        }

        return success;
    }

    private static boolean modifyPage(WebDriver driver,Map<String,String> map){

        String path = PropertiesUtil.class.getClassLoader().getResource("bailAccountIdModifyPage.html").getPath();
        path = "file://"+path;
        System.out.println(path);

        driver.get(path);

        driver.findElement(By.id("dBtn")).click();

        driver.findElement(By.id("seqNo")).sendKeys(map.get("seq_no"));
        driver.findElement(By.id("productId")).sendKeys(map.get("product_id"));
        driver.findElement(By.id("sign")).sendKeys(map.get("sign"));
        driver.findElement(By.id("txTime")).sendKeys(map.get("tx_time"));

        driver.findElement(By.id("transBtn")).click();

        driver.findElement(By.id("pass")).sendKeys(compensatePassword);
        driver.findElement(By.id("sub")).click();

        return true;
    }
    public static void executeClickJob(){
        System.out.println("start selenium");

        WebDriver driver = null;
        try {
            // ie启动成功,files是启动ie驱动
            System.setProperty("webdriver.ie.driver", "C:\\Users\\yangsiguo\\Desktop\\IEDriverServer_Win32_3.8.0\\IEDriverServer.exe");
//        代码关闭IE一些配置
            DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
            dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            dc.setCapability("ignoreProtectedModeSettings", true);
//        把加载关闭配置加载到IE浏览器
            driver = new InternetExplorerDriver(dc);

            executeClickJob(driver);

        }catch (Throwable e){

            e.printStackTrace();
        }finally {
            driver.close();
            driver.quit();
        }

    }
    private static void executeClickJob(WebDriver driver){


//        driver.get("https://www.baidu.com");

//    WebElement webElementsok =    driver.findElement(By.xpath("//*[@class='bg s_btn']"));

//        List<WebElement> webElements2 =    driver.findElements(By.xpath("//*[@class='bg s_btn']"));

        //
//        WebElement webElement = ((InternetExplorerDriver) driver).findElementByClassName("fa fa-circle-o text-yellow");

//        全屏
        driver.manage().window().maximize();

        driver.get("http://192.168.0.87:18080/xxl-job-admin/");


        List<WebElement> webElements1 =  driver.findElements(By.xpath("//*[@class='fa fa-circle-o text-yellow']"));

        if (webElements1.size() <= 0){
            login(driver);
            webElements1 =    driver.findElements(By.xpath("//*[@class='fa fa-circle-o text-yellow']"));
        }

        webElements1.get(0).click();



        //jobGroup
        WebElement d = driver.findElement(By.id("jobGroup"));
        Select s = new Select(d);
        s.selectByValue("2");


        driver.findElement(By.id("executorHandler")).sendKeys("compensatoryHandler");

        driver.findElement(By.id("searchBtn")).click();

        String compensatoryHandlerSelector = PropertiesUtil.GetValueByKey("config.properties","compensatoryHandler-selector");
        //#\31 3 > button.btn.btn-primary.btn-xs.job_trigger
        WebElement exe = driver.findElement(By.cssSelector(compensatoryHandlerSelector));
        exe.click();


        WebElement ok = driver.findElement(By.xpath("//*[@class='btn btn-primary ok']"));
        ok.click();


        List<WebElement> select =    driver.findElements(By.xpath("//*[@class='form-control']"));


        List<WebElement> webElements2 =    driver.findElements(By.xpath("//*[@class='btn btn-primary btn-xs job_trigger']"));


        List<WebElement> webElements  = ((InternetExplorerDriver) driver).findElementsByClassName("fa fa-circle-o text-yellow");





//        Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");.
    }
    private static void login(WebDriver driver){

        List<WebElement> webElements1 =   driver.findElements(By.xpath("//*[@class='btn btn-primary btn-block btn-flat']"));
        webElements1.get(0).click();

    }


}
