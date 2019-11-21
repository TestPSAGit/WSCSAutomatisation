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
        driver.manage().window().maximize();
        // driver.findElement(By.className("psac_noselect")).click();
        ElementMethodes.CloseCokies(driver);
        driver.findElement(By.name("model")).click();
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
        driver.findElement(By.xpath("//button[@class='btn btn-large btn-full btn-primary btn-double submit bound']")).click();
        System.err.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx");
        System.err.println(driver.findElement(By.xpath("//button[@class='btn btn-large btn-full btn-primary btn-double submit bound']")).getText());
        System.err.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx");

        WaitS.waitForLoad(driver);

        System.err.println(driver.getCurrentUrl());
        ElementMethodes.SwitchToCurrentUrl(driver);
        System.err.println("curent url >>>>>>>>>>>>>");
        System.err.println(driver.getWindowHandle());

        WaitS.WaitForElementToBeClickable(driver,
                driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[2]/p[1]/a[1]/span[1]/strong[1]")));
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[2]/p[1]/a[1]/span[1]/strong[1]")).click();
        WaitS.Wait(3000);
        WaitS.WaitForElementToBeVisible(driver, driver.findElement(By.id("BtContacterPDV2")));
        driver.findElement(By.id("BtContacterPDV2")).click();
        WaitS.waitForLoad(driver);
        LeadsAP.FillContactFormFrance(driver, TestData, 2);
        LeadsAP.ClickSubmitButton(driver);
        assert (LeadsAP.GetTheConfirmationMessage(driver) == true);
        driver.close();

    }

}
