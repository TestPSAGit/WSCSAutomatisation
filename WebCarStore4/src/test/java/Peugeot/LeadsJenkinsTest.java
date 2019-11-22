/*
 * Creation : 1 avr. 2019
 */
package Peugeot;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utilities.ElementMethodes;
import Utilities.InitialiseDrivers;
import Utilities.WaitS;

public class LeadsJenkinsTest {
    static WebDriver driver;
    public String[][] TestData;

    @BeforeMethod

    public void BeforeTest() throws IOException {
        driver = InitialiseDrivers.InitialiseChromDriver();
        TestData = Utilities.ReadExcelFile.GetTestData("C:\\Users\\E562418\\Desktop\\WSCS Selenium\\WebCarStore4\\testData.xlsx", 0);
    }

    @Test(priority = 1)
    public void Litetest() {

        driver.get("http://fr.store.peugeot.preprod.inetpsa.com");

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize(); // driver.findElement(By.className("psac_noselect")).click();
        ElementMethodes.CloseCokies(driver);
        driver.findElement(By.name("model")).click();
        ElementMethodes.CloseCokies(driver);
        WaitS.Wait(2000);
        new Select(driver.findElement(By.name("model"))).selectByIndex(4);
        WaitS.Wait(2000);
        driver.findElement(By.id("region")).sendKeys("Paris");
        WaitS.Wait(2000);
        driver.findElement(By.id("region")).sendKeys(Keys.ENTER);
        WaitS.Wait(2000);
        System.err.println("curent url >>>>>>>>>>>>>");
        System.err.println(driver.getCurrentUrl());
        WaitS.waitForLoad(driver);
        driver.findElement(By.id("model-choice-counter")).click();
        ElementMethodes.CloseCokies(driver);
        System.err.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx");
        System.err.println(driver.findElement(By.xpath("//strong[contains(text(),'Voir cette voiture')]")).getText());
        System.err.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx");

        WaitS.waitForLoad(driver);

        System.err.println(driver.getCurrentUrl());
        ElementMethodes.SwitchToCurrentUrl(driver);
        System.err.println("curent url >>>>>>>>>>>>>");
        System.err.println(driver.getWindowHandle());

        // driver.get(
        // "http://fr.store.peugeot.preprod.inetpsa.com/Recherche-par-critere?lat=48.856614&lng=2.3522219000000177&LocationL=Paris%2C%20France&etd=0&mbd=1PT9zzzzzzzz;1PT9S0000032;1PT9S0000030;");

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        // driver.findElement(By.className("psac_noselect")).click();
        ElementMethodes.CloseCokies(driver);
        WaitS.WaitForElementToBeClickable(driver,
                driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[2]/p[1]/a[1]/span[1]/strong[1]")));
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[2]/p[1]/a[1]/span[1]/strong[1]")).click();
        ElementMethodes.CloseCokies(driver);
        WaitS.Wait(3000);
        System.out.println("this is the Product page<<<>>>>>>>    " + driver.getCurrentUrl());
        WaitS.WaitForElementToBeVisible(driver, driver.findElement(By.id("BtContacterPDV2")));
        driver.findElement(By.id("BtContacterPDV2")).click();
        ElementMethodes.CloseCokies(driver);
        System.out.println("this is the contact page<<<>>>>>>>    " + driver.getCurrentUrl());
        WaitS.waitForLoad(driver);
        LeadsAP.FillContactFormFrance(driver, TestData, 2);
        LeadsAP.ClickSubmitButton(driver);
        assert (LeadsAP.GetTheConfirmationMessage(driver) == true);
        driver.close();

    }

}
