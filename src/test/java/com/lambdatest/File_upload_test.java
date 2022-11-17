package com.lambdatest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

@Test
public class File_upload_test extends File_Upload {

    private RemoteWebDriver driver;
    private String Status = "failed";
    public static String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
    public static String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
    ;
    public static String hub = "@hub.lambdatest.com/wd/hub";

    public void basicTest() throws IOException, InterruptedException {
        upload_files();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "108.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("platformName", "Windows 10");

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
        ltOptions.put("tags", Tags);


        String[] file_to_use = new String[] {"test.csv"};
        ltOptions.put("lambda:userFiles", file_to_use);

        capabilities.setCapability("LT:Options", ltOptions);

//        caps.setCapability("lambda:userFiles", [
//                "654_Test.side",
//                ]);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), capabilities);

          driver.get("https://the-internet.herokuapp.com/upload");


        WebElement addFile = driver.findElement(By.xpath("//*[@id=\"file-upload\"]"));
//        addFile.sendKeys("/Users/ltuser/Downloads/654_Test.side");
        addFile.sendKeys("C:\\Users\\ltuser\\Downloads\\test.csv");
        driver.findElement(By.id("file-submit")).click();

        Status = "passed";
        Thread.sleep(150);

        System.out.println("TestFinished");

    }
    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }
}
