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
   
    public static Elements elementPage; //Elementlerin bulundu�u paket dosyas�
    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tugce\\OneDrive\\Documents\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.gittigidiyor.com/";
        baseUrl2 = "https://www.gittigidiyor.com/arama/?k=Bilgisayar&sf=2";

      //Driver�n elementlere eri�imi i�in 10 sn beklenir
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
      //Sayfalar�n beklenmesi i�in s�re tan�n�r
        driver.manage().timeouts().pageLoadTimeout(200, SECONDS);
        //ekran b�y�t�l�r
        driver.manage().window().maximize();
         
        elementPage = new Elements(driver);
    }
 
    @Test
    public void logIn() throws Exception {
    	
        Log4j.startLog("Test  is Starting");
         //gittigidiyor a��l�yor
        driver.get(baseUrl);
        // ana sayfan�n a��ld��� kontrol ediliyor
        expectedUrl= driver.getCurrentUrl(); 
        if(expectedUrl.equalsIgnoreCase(baseUrl)){
              Log4j.info("gittigidiyor a��ld�");
        }
        else {
              Log4j.error("gittigidiyor a��lamad�!");
        }
        
        
        // giri� b�l�m�ne t�klan�yor
        elementPage.giris().click();
        
        Thread.sleep(3000); 
        elementPage.girisYap().click();
        // �yelik bilgileri giriliyor
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
            Log4j.info("Giri� Ba�ar�l�");
        }
        else {
            Log4j.error("Giri� Yap�lamad�!");
        }
        
        // bilgisayar kelimesi aran�yor
        elementPage.searchBox().clear();
        elementPage.searchBox().sendKeys(araKelime);
        elementPage.searchBox().submit();
        Log4j.info("Aranan Kelime :" + araKelime);
        
        // 2. arama sayfas�na  ge�mek i�in y koordinat� kullan�l�yor
        WebElement elementClick=elementPage.sayfa2();
       
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+elementClick.getLocation().y+")");
         
        elementClick.click();
         //2. arama sayfas�na ge�ilmi� mi kontrol ediliyor
        expectedUrl3= driver.getCurrentUrl(); 
        
        if(expectedUrl3.equalsIgnoreCase(baseUrl2)){
          	Log4j.info("2.Sayfaya ge�i� ba�ar�l�");
        }
        else {
        	Log4j.error("2.Sayfaya ge�i� ba�ar�s�z!");
        }
        
        //random �r�n se�iliyor
        List<WebElement> allProducts = driver.findElements(By.xpath("//*[@id=\"best-match-right\"]/div[3]/div[2]/ul//img"));
        Random rand = new Random();
        int randomProduct = rand.nextInt(allProducts.size());
        allProducts.get(randomProduct).click();
        
        //sepete ekleniyor
        WebElement elementClick2=elementPage.addToBasket();
       
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+elementClick2.getLocation().y+")");
         
        elementClick2.click();
        Thread.sleep(3000);
        
        //�r�n�n sayfas�ndaki ve sepetteki fiyat� kar��la�t�r�l�yor
        WebElement price=elementPage.pagetPrice();
        String a= price.getText(); 
        Thread.sleep(3000);
        elementPage.sepeteGit().click();
        WebElement price2=elementPage.basketPrice();
        String b= price2.getText(); 
  
         if(a.equalsIgnoreCase(b)) {
        	Log4j.info("�r�n�n fiyatlar� ayn�");
         }
         else {
         	Log4j.error("�r�n�n fiyatlar� farkl�!");
         }
         
        Thread.sleep(3000);
        
        
        //�r�n adeti 1 artt�r�l�yor
        WebElement staticDropdown =elementPage.urunAdet();
        Select dropdown=  new Select(staticDropdown);
         dropdown.selectByIndex(1);
         Thread.sleep(3000); 
         
         //�r�n iki adet mi kontrol ediliyor
         WebElement adet=elementPage.ikiAdet();
         String n= adet.getText();
         String m="�r�n Toplam� (2 Adet)";
         if(n.equalsIgnoreCase(m)) {
        	 Log4j.info("�r�n adeti 2");
         }
         else {
        	 Log4j.info("�r�n adetinde bir yanl��l�k var!");
         }
         
         //sepetteki �r�n siliniyor
         elementPage.sil().click();
         Thread.sleep(3000);
         //sepet bo� mu kontrol ediliyor
         WebElement x=elementPage.sepetBosMu();
         String z= x.getText();
         String t="Sipari� �zeti";
         if(z.equalsIgnoreCase(t)) {
        	 Log4j.info("Sepet Dolu");
         }
         else {
        	 Log4j.info("Sepet Bo�");
         }
         Thread.sleep(4000); 
         
    }
 
    @After
    public void endDown(){
        Log4j.endLog("Test is Ending");
        driver.quit();
    }
}