 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pf.api.service;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pf.api.model.Source;
import pf.api.model.Product;
import pf.api.model.SourceProduct;
import pf.api.model.SourceProductId;
import pf.api.util.HibernateUtil;

/**
 *
 * @author MSI
 */
public class WebScraping {
    
    private String searchValue;
    
    private FirefoxDriver driver;
    
    private static WebScraping instance = null;
    
    private static SessionFactory factory;
    
    private Transaction tx = null;
    
    private Session session = null;
    
    public synchronized static WebScraping getInstance() {
        if (instance == null) {
            instance = new WebScraping();
        }
        return instance;
    }
    
    public WebScraping(){
        this.searchValue="tivi";
    }

    public void start() {
        storeLazadaProducts();
        
    }
    
    private void storeLazadaProducts(){
        String browser_driver_exe = "E:\\ThucTapJava\\TestBackEnd\\lib\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", browser_driver_exe);
        this.driver = new FirefoxDriver();
        navigateDriverToProductsPage();
        storeProductsToDatabase();
        this.driver.close();
    }

    private void navigateDriverToProductsPage() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        this.driver.navigate().to("https://tiki.vn/");
        String searchButtonXpath = "//button[@class='FormSearch__Button-hwmlek-3 dVzStw']";
        String searchInputXpath = "//input[@class='FormSearch__Input-hwmlek-2 eiGtjR']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchButtonXpath)));
        this.driver.findElement(By.xpath(searchInputXpath)).sendKeys(this.searchValue);
        this.driver.findElement(By.xpath((searchButtonXpath))).click();
        String rootXpath = "//div[@id='ants-product-list']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rootXpath)));
    }

    private void storeProductsToDatabase() {
//        factory = new HibernateUtil().getSessionFactory();
//        session = factory.openSession();
//        tx = session.beginTransaction();
//        Source sTiki = new Source();
//        sTiki.setName("tiki");
//        sTiki.setIcon("https://salt.tikicdn.com/ts/banner/33/ba/89/54c02d2475983f93a024c0cd13f238e4.png");
//        session.save(sTiki);
        String productClass = "content ";
        List<WebElement> productList = this.driver.findElements(By.className(productClass));
        int count = 0;
        for (WebElement product : productList) {
//            Product nProduct = new Product();
//            String codeRegex = "[A-Z0-9]{4,14}";
//            Pattern p = Pattern.compile(codeRegex);
            System.out.println(product.findElement(By.xpath("//a[@class='search-a-product-item']")).getAttribute("title"));
//            Matcher m = p.matcher(product.findElement(By.xpath("//div[@class='product-item']")).getAttribute("data-title").trim());
//            if (m.find()) {
//                nProduct.setCode(m.group());
//            }
//            
//            session.save(nProduct);
//            SourceProductId spId = new SourceProductId();
//            System.out.println(nProduct.getId());
//            System.out.println(sTiki.getId());
//            System.out.println(nProduct.getCode());
//            spId.setProductId(nProduct.getId());
//            spId.setSourceId(sTiki.getId());
//            SourceProduct sp = new SourceProduct();
//            sp.setId(spId);
//            sp.setPrice(product.findElement(By.xpath("//span[@class='final-price']")).getText());
//            System.out.println("price: " + product.findElement(By.xpath("//span[@class='final-price']")).getText());
//            sp.setLink(product.findElement(By.xpath("//a[@class='search-a-product-item']")).getAttribute("href"));
//            sp.setImage(product.findElement(By.xpath("//img[@class='product-image img-responsive']")).getAttribute("src"));
//            session.save(sp);
//            count += 1;
//            if (count % 20 == 0) {
//                session.flush();
//                session.clear();
//            }
        }
//        tx.commit();
//        session.close();    
    }
    
}
