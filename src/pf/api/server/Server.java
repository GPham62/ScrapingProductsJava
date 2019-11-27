/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pf.api.server;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pf.api.service.WebScraping;

/**
 *
 * @author MSI
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Firo.getInstance().init(Config.getParamString("service", "host", "127.0.0.1"), Config.getParamInt("service", "port", 3003));
//        Firo.getInstance().initializeControllerFromPackage("pf.api.controller", ServiceDaemon.class);
        WebScraping.getInstance().start();
//        WebScrape();
    }
    
    
    
    public static void WebScrape(){
        System.setProperty("webdriver.gecko.driver", "E:\\ThucTapJava\\TestBackEnd\\lib\\geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.navigate().to("https://www.lazada.vn/");
        String searchButtonXpath = "//button[@class='search-box__button--1oH7']";
        String searchInputName = "q";
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchButtonXpath)));
        driver.findElement(By.name(searchInputName)).sendKeys("tivi");
        driver.findElement(By.xpath(searchButtonXpath)).click();
        String root = "//div[@id='root']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(root)));
        String productClass = "c2prKC";
        List<WebElement> productList = driver.findElements(By.className(productClass));
//        List<WebElement> imgs = driver.findElements(By.xpath("//div[@class='c16H9d']"));
//        for (WebElement img : imgs) {
//            System.out.println("img: " + img.getAttribute("innerHTML"));
//        }
        for (WebElement product : productList) {
            System.out.println(product.getAttribute("innerHTML"));
        }
//        ArrayList<String> matchIds = new ArrayList<String>();
//        for (WebElement product : productList){
//            String productParsed[] = product.getText().split("\n");
//            if (productParsed.length == 4){
//                System.out.println('4');
//            }
//            else if (productParsed.length == 5){
//                System.out.println('5');
//            }
//            String pattern = "[A-Z0-9]{4,14}";
//            Pattern r = Pattern.compile(pattern);
//            Matcher m = r.matcher(productParsed[0].trim());
//            System.out.println(productParsed[0]);
//            while(m.find()){
//                matchIds.add(m.group());
//            }
//            for (String id : matchIds){
//                r = Pattern.compile("(?=.*\\d)(?=.*[A-Z])");
//                Matcher matcher = r.matcher(id.trim());
//                if (matcher.find()){
//                    System.out.println("id: " + id);
//                    break;
//                }
//            }
//            matchIds.clear();
//        }
        driver.close();
    }
}
