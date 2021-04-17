
package model;
 
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
public class Elements {
    public static WebDriver driver;
 
    public Elements(WebDriver webDriver) {
        driver = webDriver;
 
    }
 
    public WebElement giris() {
        return driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div[1]/div[3]/div/div[1]/div"));
    }
 
    public WebElement girisYap() {
        return driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[3]/div/div[1]/div[2]/div/div/div/a"));
    }
 
    public WebElement userName() {
        return driver.findElement(By.id("L-UserNameField"));
    }
 
    public WebElement userPassword() {
        return driver.findElement(By.id("L-PasswordField"));
    }
 
    public WebElement logIn() {
        return driver.findElement(By.id("gg-login-enter"));
    }
 
 
    public WebElement searchBox() {
        return driver.findElement(By.xpath("//*[@id='main-header']/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input"));
    }
    
    public WebElement sayfa2() {
    	return driver.findElement(By.xpath("//*[@id=\"best-match-right\"]/div[5]/ul/li[2]"));
    }
    
    public WebElement allProducts() {
        return driver.findElement(By.xpath("//*[@id=\"best-match-right\"]/div[3]/div[2]/ul//img"));
    }
    
    public WebElement addToBasket() {
        return driver.findElement(By.xpath("//*[@id=\"add-to-basket\"]"));
    }
    
    public WebElement sepeteGit() {
        return driver.findElement(By.xpath("//*[@id=\"header_wrapper\"]/div[4]/div[3]/a"));
    }
    
    public WebElement adet() {
        return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div[1]/div/div[6]/div[2]/div[2]/div[1]/div[4]/div/div[2]//select"));
    }
    
    public WebElement sil() {
    	return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div[1]/div/div[6]/div[2]/div[2]/div[1]/div[3]/div/div[2]/div/a[1]/i"));   	
    }
    
    public WebElement basketPrice() {
        return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[3]/div/div[1]/div/div[5]/div[2]/div[3]/p"));
    }
    
    public WebElement pagetPrice() {
        return driver.findElement(By.xpath("//*[@id=\"sp-price-discountPrice\"]"));
    }
    
    public WebElement urunAdet() {
        return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div[1]/div/div[6]/div[2]/div[2]/div[1]/div[4]/div/div[2]//select"));
    }
    
    public WebElement sepetBosMu() {
        return  driver.findElement(By.xpath("//*[@id=\"submit-cart\"]/div/div[2]/div[3]/div/div[1]/div/div[1]/h2"));
    }
    
    public WebElement ikiAdet() {
        return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[3]/div/div[1]/div/div[5]/div[1]/div/ul/li[1]/div[1]"));
    }
   
}