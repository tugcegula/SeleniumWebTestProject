package Test;

import model.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.List;
import java.util.Random;

 
public class WriteLogTestAutomation {
    public static WebDriver driver;
    private static String baseUrl,baseUrl2,baseUrl3;
    private static String expectedUrl,expectedUrl2,expectedUrl3;
    public static String email = "agrcimaesr@gmail.com";
    public static String sifre = "5629256";
    public static String araKelime = "Bilgisayar";
   
    public static Elements elementPage; //Elementlerin bulunduðu paket dosyasý
    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tugce\\OneDrive\\Documents\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.gittigidiyor.com/";
        baseUrl2 = "https://www.gittigidiyor.com/arama/?k=Bilgisayar&sf=2";

      //Driverýn elementlere eriþimi için 10 sn beklenir
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
      //Sayfalarýn beklenmesi için süre tanýnýr
        driver.manage().timeouts().pageLoadTimeout(200, SECONDS);
        //ekran büyütülür
        driver.manage().window().maximize();
         
        elementPage = new Elements(driver);
    }
 
    @Test
    public void logIn() throws Exception {
    	
        Log4j.startLog("Test  is Starting");
         //gittigidiyor açýlýyor
        driver.get(baseUrl);
        // ana sayfanýn açýldýðý kontrol ediliyor
        expectedUrl= driver.getCurrentUrl(); 
        if(expectedUrl.equalsIgnoreCase(baseUrl)){
              Log4j.info("gittigidiyor açýldý");
        }
        else {
              Log4j.error("gittigidiyor açýlamadý!");
        }
        
        
        // giriþ bölümüne týklanýyor
        elementPage.giris().click();
        
        Thread.sleep(3000); 
        elementPage.girisYap().click();
        // üyelik bilgileri giriliyor
        elementPage.userName();
        elementPage.userName().sendKeys(email);
        Thread.sleep(3000);
        elementPage.userPassword();
        elementPage.userPassword().sendKeys(sifre);    
        elementPage.logIn().click();
        //login olundu mu konrol ediliyor
        expectedUrl2= driver.getCurrentUrl(); 
       
         
      //  Assert.assertEquals(expectedUrl,baseUrl);
        
        if(expectedUrl2.equalsIgnoreCase(baseUrl)){
            Log4j.info("Giriþ Baþarýlý");
        }
        else {
            Log4j.error("Giriþ Yapýlamadý!");
        }
        
        // bilgisayar kelimesi aranýyor
        elementPage.searchBox().clear();
        elementPage.searchBox().sendKeys(araKelime);
        elementPage.searchBox().submit();
        Log4j.info("Aranan Kelime :" + araKelime);
        
        // 2. arama sayfasýna  geçmek için y koordinatý kullanýlýyor
        WebElement elementClick=elementPage.sayfa2();
       
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+elementClick.getLocation().y+")");
         
        elementClick.click();
         //2. arama sayfasýna geçilmiþ mi kontrol ediliyor
        expectedUrl3= driver.getCurrentUrl(); 
        
        if(expectedUrl3.equalsIgnoreCase(baseUrl2)){
          	Log4j.info("2.Sayfaya geçiþ baþarýlý");
        }
        else {
        	Log4j.error("2.Sayfaya geçiþ baþarýsýz!");
        }
        
        //random ürün seçiliyor
        List<WebElement> allProducts = driver.findElements(By.xpath("//*[@id=\"best-match-right\"]/div[3]/div[2]/ul//img"));
        Random rand = new Random();
        int randomProduct = rand.nextInt(allProducts.size());
        allProducts.get(randomProduct).click();
        
        //sepete ekleniyor
        WebElement elementClick2=elementPage.addToBasket();
       
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+elementClick2.getLocation().y+")");
         
        elementClick2.click();
        Thread.sleep(3000);
        
        //ürünün sayfasýndaki ve sepetteki fiyatý karþýlaþtýrýlýyor
        WebElement price=elementPage.pagetPrice();
        String a= price.getText(); 
        Thread.sleep(3000);
        elementPage.sepeteGit().click();
        WebElement price2=elementPage.basketPrice();
        String b= price2.getText(); 
  
         if(a.equalsIgnoreCase(b)) {
        	Log4j.info("Ürünün fiyatlarý ayný");
         }
         else {
         	Log4j.error("Ürünün fiyatlarý farklý!");
         }
         
        Thread.sleep(3000);
        
        
        //ürün adeti 1 arttýrýlýyor
        WebElement staticDropdown =elementPage.urunAdet();
        Select dropdown=  new Select(staticDropdown);
         dropdown.selectByIndex(1);
         Thread.sleep(3000); 
         
         //ürün iki adet mi kontrol ediliyor
         WebElement adet=elementPage.ikiAdet();
         String n= adet.getText();
         String m="Ürün Toplamý (2 Adet)";
         if(n.equalsIgnoreCase(m)) {
        	 Log4j.info("Ürün adeti 2");
         }
         else {
        	 Log4j.info("Ürün adetinde bir yanlýþlýk var!");
         }
         
         //sepetteki ürün siliniyor
         elementPage.sil().click();
         Thread.sleep(3000);
         //sepet boþ mu kontrol ediliyor
         WebElement x=elementPage.sepetBosMu();
         String z= x.getText();
         String t="Sipariþ Özeti";
         if(z.equalsIgnoreCase(t)) {
        	 Log4j.info("Sepet Dolu");
         }
         else {
        	 Log4j.info("Sepet Boþ");
         }
         Thread.sleep(4000); 
         
    }
 
    @After
    public void endDown(){
        Log4j.endLog("Test is Ending");
        driver.quit();
    }
}