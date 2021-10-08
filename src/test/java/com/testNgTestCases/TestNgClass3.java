package com.testNgTestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.utility.library;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgClass3 extends library{
	WebDriver driver;
	
  @Test(priority=-1)
  public void validateGMOonlineLoadedSuccessfully() {
	  System.out.println("inside validateGMOonlineLoadedSuccessfully");
	  String Actualtitle=driver.getTitle();
	  String expectedtitle ="Welcome to Green Mountain Outpost";
	  System.out.println(Actualtitle);
	  Assert.assertEquals(Actualtitle, expectedtitle);
  }
  
  @Parameters
  @Test(priority=0,dependsOnMethods={"validateGMOonlineLoadedSuccessfully"})
  public void ValidateOnLineCatalogLoadedSuccessfully(){
	  System.out.println("inside ValidateOnLineCatalogLoadedSuccessfully");
	  driver.findElement(By.name("bSubmit")).click();
	  driver.findElement(By.xpath("//input[@type='text' and @name='QTY_BACKPACKS']")).sendKeys("4");
	  //table/tbody/tr[3]/td[3]
	  String qtyPriceFrameBackpack = driver.findElement(By.xpath("//*[contains(text(),'$ 179.95')]")).getText();
	  System.out.println(qtyPriceFrameBackpack);
	  driver.findElement(By.xpath("//input[@value='Place An Order']")).click();
	  

  }
  
  @Test(priority=1,dependsOnMethods={"ValidateOnLineCatalogLoadedSuccessfully"})
  public void ValidatePriceCalculationInPlaceorderPage(){
	  System.out.println("inside ValidatePriceCalculationInPlaceorderPage");
	  String ActualTitle=driver.findElement(By.xpath("//h1[contains(text(),'Place Order')]")).getText();
	  String ExpectedTile = "Place Orde";
	  //Assert.assertEquals(ActualTitle, ExpectedTile);
	  SoftAssert sAssert = new SoftAssert();
	  sAssert.assertEquals(ActualTitle, ExpectedTile);
	  String UnitPriceBackPack = driver.findElement(By.xpath("//table/tbody/tr[2]/td[4]")).getText();
	  String PriceQtyBackPack = UnitPriceBackPack.substring(2).trim();
	  System.out.println(PriceQtyBackPack);
	  Float ExpectedTotalPriceBackPack = Float.parseFloat(PriceQtyBackPack)*4;
	  System.out.println("ExpectedTotalPriceBackPack:"+ExpectedTotalPriceBackPack);
	  String AcutalTotalPriceBackPack  = driver.findElement(By.xpath("//table/tbody/tr[2]/td[5]")).getText().substring(2).trim();
	  Float ActualPrice=Float.parseFloat(AcutalTotalPriceBackPack);
	  System.out.println("ActualPrice:"+ActualPrice);
	  Assert.assertEquals(ActualPrice, ExpectedTotalPriceBackPack);
	  sAssert.assertAll();// this should be declared at the last line of the testcase
  }
  
  @Test(priority=2)
  public void ValidateAllorders(){
	  driver.get("http://demo.borland.com/gmopost/online-catalog.htm");
	  
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("inside beforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("inside afterMethod");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("inside beforeClass");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("inside afterClass");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("inside beforeTest");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("inside afterTest");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("inside beforeSuite");
	  try {
		library.ReadFeatureFile();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("inside afterSuite");
  }

}
