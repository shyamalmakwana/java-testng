package com.lambdatest;

import okhttp3.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.lambdatest.File_Upload;

public class TestNGTodo1 extends File_Upload {

    private RemoteWebDriver driver;
    private String Status = "failed";
    public static String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
    public static String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
    ;
    public static String hub = "@hub.lambdatest.com/wd/hub";

//    @BeforeMethod
//    public void setup(Method m, ITestContext ctx) throws IOException {

//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("text/plain");
//        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("files","/files_prj/test.csv",
//                        RequestBody.create(MediaType.parse("application/octet-stream"),
//                                new File("/files_prj/test.csv")))
//                .build();
//        Request request = new Request.Builder()
//                .url("https://api.lambdatest.com/automation/api/v1/user-files")
//                .method("POST", body)
//                .addHeader("accept", "application/json")
//                .addHeader("Authorization", "Basic "+ authkey)
//                .build();

//        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
//
//        assert response.body() != null;
//        System.out.println(response.body().string());

//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("platform", "Windows 10");
//        caps.setCapability("browserName", "Chrome");
//        caps.setCapability("version", "latest");
//        caps.setCapability("build", "TestNG With Java");
//        caps.setCapability("name", m.getName() + " - " + this.getClass().getName());
//        caps.setCapability("plugin", "git-testng");
//
//        String[] file_to_use = new String[] {"654_Test.side"};
//        caps.setCapability("lambda:userFiles", file_to_use);
//    }

    @Test
    public void basicTest() throws InterruptedException, IOException {
        upload_files();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "108.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("platformName", "Windows 10");
//        ltOptions.put("seCdp", true);
//        ltOptions.put("selenium_version", "4.6.0");

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
        ltOptions.put("tags", Tags);


        String[] file_to_use = new String[] {"test.csv"};
        ltOptions.put("lambda:userFiles", file_to_use);

        capabilities.setCapability("LT:Options", ltOptions);

//        caps.setCapability("lambda:userFiles", [
//                "654_Test.side",
//                ]);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), capabilities);


        String spanText;
        System.out.println("Loading Url");

//        driver.get("https://lambdatest.github.io/sample-todo-app/");
        driver.get("https://the-internet.herokuapp.com/upload");


//        System.out.println("Checking Box");
//        driver.findElement(By.name("li1")).click();
//
//        System.out.println("Checking Another Box");
//        driver.findElement(By.name("li2")).click();
//
//        System.out.println("Checking Box");
//        driver.findElement(By.name("li3")).click();
//
//        System.out.println("Checking Another Box");
//        driver.findElement(By.name("li4")).click();
//
//        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 6");
//        driver.findElement(By.id("addbutton")).click();
//
//        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 7");
//        driver.findElement(By.id("addbutton")).click();
//
//        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 8");
//        driver.findElement(By.id("addbutton")).click();
//
//        System.out.println("Checking Another Box");
//        driver.findElement(By.name("li1")).click();
//
//        System.out.println("Checking Another Box");
//        driver.findElement(By.name("li3")).click();
//
//        System.out.println("Checking Another Box");
//        driver.findElement(By.name("li7")).click();
//
//        System.out.println("Checking Another Box");
//        driver.findElement(By.name("li8")).click();
//        Thread.sleep(300);
//
//        System.out.println("Entering Text");
//        driver.findElement(By.id("sampletodotext")).sendKeys("Get Taste of Lambda and Stick to It");
//
//        driver.findElement(By.id("addbutton")).click();
//
//        System.out.println("Checking Another Box");
//        driver.findElement(By.name("li9")).click();
//
//        // Let's also assert that the todo we added is present in the list.
//
//        spanText = driver.findElementByXPath("/html/body/div/div/div/ul/li[9]/span").getText();
//        Assert.assertEquals("Get Taste of Lambda and Stick to It", spanText);

//        <input id="file-upload" type="file" name="file">

        WebElement addFile = driver.findElement(By.xpath("//*[@id=\"file-upload\"]"));
//        addFile.sendKeys("/Users/ltuser/Downloads/654_Test.side");
        addFile.sendKeys("C:\\Users\\ltuser\\Downloads\\test.csv");
        driver.findElement(By.id("file-submit")).click();

        //*[@id="file-upload"]

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