package com.demo.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;



public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();


    public static WebDriver getDriver() {
		return tlDriver.get();
	}
    

    public WebDriver initDriver(Properties prop, String browserName) {

//        String browserName = prop.getProperty("browser");
//        
//        highlight = prop.getProperty("highlight");// "true"

        optionsManager = new OptionsManager(prop);
        

        switch (browserName.toLowerCase()) {
        
            case "chrome":
    			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
    			break;

            case "edge":
            	tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                break;

            default:
                System.out.println("Plz pass the right browser...." + browserName);
                break;
        }

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().get(prop.getProperty("url"));
        
        return getDriver();

    }


// Initializing the properties file

    public Properties initProp() {
        FileInputStream filePath = null;
        
        prop = new Properties();

        String envname = System.getProperty("env");
        System.out.println("env name is: " + envname);

        try {
            if(envname == null) {
                System.out.println("no env is given...hence running it on QA env..by default");
				filePath = new FileInputStream("./src/test/resources/config/qa.config.properties");
            } else {
                switch(envname.toLowerCase().trim()) {
                    case "qa":
                        filePath = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;

                    case "dev":
                        filePath = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;    

                    case "stage":
                        filePath = new FileInputStream("./src/test/resources/config/stage.config.properties");
                        break;  

                    case "uat":
                        filePath = new FileInputStream("./src/test/resources/config/uat.config.properties");
                        break;   
                        
                    case "prod":
                        filePath = new FileInputStream("./src/test/resources/config/prod.config.properties");
                        break;  

                    default:
                        System.out.println("please pass the right env name...." + envname);
                        break;
                }                
            }             
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            prop.load(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }



    // Function to capture screenshot
    
    public static String getScreenshot(String methodName) {

        TakesScreenshot getScreenshot = (TakesScreenshot) getDriver();
        File srcFile = getScreenshot.getScreenshotAs(OutputType.FILE);

        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png";

        File destFile = new File(screenshotPath);
        try {
            FileUtils.copyFile(srcFile, destFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }

}
