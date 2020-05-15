package com.practice.methods;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.customer.CustomerwebappApplication;

@SpringBootTest(classes = CustomerwebappApplication.class)
public class TestNgBasicTest extends AbstractTestNGSpringContextTests {

	@BeforeClass
	public void setup() {
		System.out.println("I am in Setup");
	}
	
	@BeforeTest
	public void beforeTest() {
	      System.out.println("I am in beforeTest");
	}
	
	@BeforeMethod
	public void callingBeforeMethod() {
		System.out.println("I am callingBeforeMethod");
	}

	/*@Test
	public void test() {
		System.out.println("I am in Test method");
	}*/
	
	@Test
	public void simpleTest() {
		Assert.assertEquals(2 * 2, 4, "2x2 should be 4");
	}
	
	
	/*@Test( dataProvider = "numberGenerator")
	public void testParameter(int num1, int num2, int output) {
		Assert.assertEquals(num1 * num2, output, "Out put should be:" + output);
	}*/

	

	/*@DataProvider(name = "numberGenerator")
	public Object[][] getValues() {
		return new Object[][] { { 2, 2, 4 }, { 5, 10, 50 }, { 7, 8, 56 } };
	}*/
	
	@AfterMethod
	public void callingAfterMethod() {
		System.out.println("I am callingAfterMethod");
	}
	
	@AfterClass(alwaysRun = true)
	public void callingAfterClass() {
		System.out.println("I am callingAfterClass");
	}
	
	@AfterTest
	public void afterTest() {
	      System.out.println("I am in afterTest");
	}
}