package Test_Prop_Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

public class Chrome_Prop_Product {
    private WebDriver driver;
    private WebDriverWait wait;
    public void ColorandFontCheck (WebElement Element)
    {
        String ColorpriceCurr = Element.findElement(By.cssSelector("s[class = regular-price]")).getCssValue("color");
        Color l1 = Color.fromString(ColorpriceCurr);
        Color Sale = Color.fromString(Element.findElement(By.cssSelector("strong[class = campaign-price]")).getCssValue("color"));
        if ((l1.getColor().getRed() == l1.getColor().getBlue()) && (l1.getColor().getRed() == l1.getColor().getGreen()))
        {
            System.out.println("Обычная цена серая и перечеркнутая");
        }
        if ((Sale.getColor().getBlue() == 0) && (Sale.getColor().getGreen() == 0))
        {
            System.out.println("Новая цена красная и жирная");
        }
        String FontSizepriceCurr = Element.findElement(By.cssSelector("s[class = regular-price]")).getCssValue("font-size");
        String FontSizepriseSale = Element.findElement(By.cssSelector("strong[class = campaign-price]")).getCssValue("font-size");
        if(Float.parseFloat(FontSizepriceCurr.substring(0, FontSizepriceCurr.length()-2)) < Float.parseFloat(FontSizepriseSale.substring(0, FontSizepriseSale.length()-2)))
        {
            System.out.println("Размер новой цены больше старой");
        }
    }
    @Before
    public void start(){
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver,1000);
    }
    @Test
    public void myFirstTest() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");

        Thread.sleep(500);
        WebElement Element = driver.findElement(By.id("box-campaigns"));
        Element = Element.findElement(By.cssSelector("[class ^= product]"));
        String NameProduct = Element.findElement(By.className("name")).getAttribute("textContent");
        String priceCurr = Element.findElement(By.cssSelector("s[class = regular-price]")).getAttribute("textContent");
        String priceSale = Element.findElement(By.cssSelector("strong[class = campaign-price]")).getAttribute("textContent");
        System.out.println("Главная страница:");
        ColorandFontCheck(Element);
        Element.click();
        System.out.println("Страница товара:");
        if (NameProduct.equals(driver.findElement(By.cssSelector("h1[class = 'title']")).getAttribute("textContent")))
        {
            System.out.println("Наименования совпадают");
        }
        if (priceCurr.equals(driver.findElement(By.cssSelector("s[class = 'regular-price']")).getAttribute("textContent")) && priceSale.equals(driver.findElement(By.cssSelector("strong[class = campaign-price]")).getAttribute("textContent")))
        {
            System.out.println("Цены сопадают");
        }
        Element = driver.findElement(By.className("price-wrapper"));
        ColorandFontCheck(Element);
    }
    @After
    public void stop(){

        driver.quit();
        driver = null;
    }
}
