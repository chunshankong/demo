package rpa.compensate;

import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yangsiguo
 * @date 2019/5/31
 * @desc TODO add description in here
 */
public class IE {

    private final static String compensatePassword;
    private static String driverPath;

    static {
        compensatePassword = PropertiesUtil.getValueByKey("compensate-password");
        // ie启动成功,files是启动ie驱动
        driverPath = System.getProperty("user.dir")+ File.separator+"driver"+File.separator+"IEDriverServer_Win32_3.14.0"+File.separator+"IEDriverServer.exe";
        System.out.println(driverPath);
        System.setProperty("webdriver.ie.driver", driverPath);
    }

    public static void main(String[] args) {

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

    public static boolean modifyPage(Map<String, String> map) {

        System.out.println("start selenium modifyPage");
        WebDriver driver = null;
        boolean success = false;
        try {

//        代码关闭IE一些配置
            DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
            dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            dc.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
//        把加载关闭配置加载到IE浏览器
            driver = new InternetExplorerDriver(dc);
            driver.manage().timeouts().implicitlyWait(Integer.valueOf(PropertiesUtil.getValueByKey("driver-wait")), TimeUnit.SECONDS);


            success = modifyPage(driver, map);

        } catch (Throwable e) {

            e.printStackTrace();
            success = false;
        } finally {
            try {
                if (null != driver) {
                    driver.close();
                    driver.quit();
                }
//                Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        try {
            //update DB
            if (success) {
                DBService.updateStateById(BidChangeRecordStatus.SUCCESS.getValue(), map.get("id"));
            } else {
                DBService.updateStateById(BidChangeRecordStatus.FAILED.getValue(), map.get("id"));
            }
        }catch (Throwable e){
            e.printStackTrace();
            success = false;
        }

        return success;
    }

    private static boolean modifyPage(WebDriver driver, Map<String, String> map) {

        String page;
        if (Main.ROBOT_PROFILES_ENV.getValue().equals(ENV.PRO.getValue())) {
            page = "bailAccountIdModifyPage.html";
        } else {
            page = "bailAccountIdModifyPage-test.html";
        }

        String path = System.getProperty("user.dir") + File.separator + page;
        path = "file://" + path;
        System.out.println(path);

        driver.get(path);

        WebElement dBtn = driver.findElement(By.id("dBtn"));
//        dBtn.click();
        dBtn.sendKeys(Keys.ENTER);

//        driver.findElement(By.id("seqNo")).sendKeys(map.get("seq_no"));
//        driver.findElement(By.id("productId")).sendKeys(map.get("product_id"));
//        driver.findElement(By.id("sign")).sendKeys(map.get("sign"));
//        driver.findElement(By.id("txTime")).sendKeys(map.get("tx_time"));

        ((JavascriptExecutor)driver).executeScript("document.getElementById(\"seqNo\").value=\""+map.get("seq_no")+"\"");
        ((JavascriptExecutor)driver).executeScript("document.getElementById(\"productId\").value=\""+map.get("product_id")+"\"");
        ((JavascriptExecutor)driver).executeScript("document.getElementById(\"sign\").value=\""+map.get("sign")+"\"");
        ((JavascriptExecutor)driver).executeScript("document.getElementById(\"txTime\").value=\""+map.get("tx_time")+"\"");
        ((JavascriptExecutor)driver).executeScript("document.getElementById(\"oldBailAccountId\").value=\""+map.get("old_bail_account")+"\"");
        ((JavascriptExecutor)driver).executeScript("document.getElementById(\"newBailAccountId\").value=\""+map.get("new_bail_account")+"\"");
        ((JavascriptExecutor)driver).executeScript("document.getElementById(\"txDate\").value=\""+map.get("tx_date")+"\"");


        WebElement transBtn = driver.findElement(By.id("transBtn"));
//        transBtn.click();
        transBtn.sendKeys(Keys.ENTER);


       /* try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        driver.findElement(By.id("pass")).sendKeys(compensatePassword);
        ((JavascriptExecutor)driver).executeScript("document.getElementById(\"pass\").value=\""+compensatePassword+"\"");


        WebElement sub =driver.findElement(By.id("sub"));
//        sub.click();
        sub.sendKeys(Keys.ENTER);

        return true;
    }

    public static void executeClickJob() {
        System.out.println("start selenium executeClickJob");

        WebDriver driver = null;

        try {
//        代码关闭IE一些配置
            DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
            dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            dc.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
//        把加载关闭配置加载到IE浏览器
            driver = new InternetExplorerDriver(dc);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            executeClickJob(driver);

        } catch (Throwable e) {

            e.printStackTrace();
        } finally {
            try {
                if (null != driver) {
                    driver.close();
                    driver.quit();
                }
//                Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

    }

    private static WebElement findElement(WebDriver driver, By by) {
        WebElement element = null;
        try {
            element = driver.findElement(by);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }

    private static void executeClickJob(WebDriver driver) {


//        driver.get("https://www.baidu.com");

//    WebElement webElementsok =    driver.findElement(By.xpath("//*[@class='bg s_btn']"));

//        List<WebElement> webElements2 =    driver.findElements(By.xpath("//*[@class='bg s_btn']"));

        //
//        WebElement webElement = ((InternetExplorerDriver) driver).findElementByClassName("fa fa-circle-o text-yellow");

//        全屏
        driver.manage().window().maximize();

        driver.get(PropertiesUtil.getValueByKey("job-url"));

        WebElement webElements1 = findElement(driver, By.cssSelector("body > div.wrapper > aside > section > ul > li:nth-child(3) > a"));
        if (webElements1 == null) {
            login(driver);
            webElements1 = findElement(driver, By.cssSelector("body > div.wrapper > aside > section > ul > li:nth-child(3) > a"));
        }
//        webElements1.click();
        webElements1.sendKeys(Keys.ENTER);

        //jobGroup
        WebElement d = driver.findElement(By.id("jobGroup"));
        Select s = new Select(d);
        s.selectByValue("2");

        driver.findElement(By.id("executorHandler")).sendKeys("compensatoryHandler");

        WebElement search = driver.findElement(By.id("searchBtn"));
        search.click();
        search.sendKeys(Keys.ENTER);

        String compensatoryHandlerSelector = PropertiesUtil.getValueByKey("compensatoryHandler-selector");
        //#\31 3 > button.btn.btn-primary.btn-xs.job_trigger
        WebElement exe = driver.findElement(By.cssSelector(compensatoryHandlerSelector));
        exe.click();
        exe.sendKeys(Keys.ENTER);

        WebElement ok = driver.findElement(By.cssSelector("#jobTriggerModal > div > div > div.modal-body > form > div:nth-child(3) > div > button.btn.btn-primary.ok"));
        ok.click();
        ok.sendKeys(Keys.ENTER);


//        Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");.
    }

    private static void login(WebDriver driver) {

        WebElement user = driver.findElement(By.cssSelector("#loginForm > div > div:nth-child(2) > input"));
        user.clear();
        String job_user = PropertiesUtil.getValueByKey("job-user");
        user.sendKeys(job_user);

        WebElement psw = driver.findElement(By.cssSelector("#loginForm > div > div:nth-child(3) > input"));
        psw.clear();
        String job_psw = PropertiesUtil.getValueByKey("job-psw");
        psw.sendKeys(job_psw);
        WebElement login = driver.findElement(By.cssSelector("#loginForm > div > div.row > div.col-xs-4 > button"));
//        login.click();
        login.sendKeys(Keys.ENTER);

//        List<WebElement> webElements1 =   driver.findElements(By.xpath("//*[@class='btn btn-primary btn-block btn-flat']"));
//        webElements1.get(0).click();


    }


}
